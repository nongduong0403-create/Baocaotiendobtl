package controller;

import dal.VoucherDAO;
import model.Voucher;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ApplyVoucherServlet", urlPatterns = {"/apply-voucher"})
public class ApplyVoucherServlet extends HttpServlet {
    // Các phần bên dưới giữ nguyên...

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String code = request.getParameter("code");
        String subTotalStr = request.getParameter("subTotal");

        // Kiểm tra dữ liệu đầu vào
        if (code == null || code.trim().isEmpty() || subTotalStr == null) {
            out.write("{\"status\": 0, \"message\": \"Vui lòng nhập mã voucher!\"}");
            return;
        }

        double subTotal = Double.parseDouble(subTotalStr);
        VoucherDAO dao = new VoucherDAO();
        Voucher voucher = dao.getVoucherByCode(code.trim().toUpperCase());

        if (voucher == null) {
            out.write("{\"status\": 0, \"message\": \"Mã giảm giá không tồn tại hoặc đã hết hạn!\"}");
            return;
        }

        // Kiểm tra điều kiện đơn hàng tối thiểu
        if (subTotal < voucher.getMinOrderValue()) {
            out.write("{\"status\": 0, \"message\": \"Đơn hàng tối thiểu phải từ " + voucher.getMinOrderValue() + " ₫ để dùng mã này!\"}");
            return;
        }

        // Tính toán số tiền giảm
        double discountAmount = 0;
        if ("FIXED".equalsIgnoreCase(voucher.getDiscountType())) {
            discountAmount = voucher.getDiscountValue();
        } else if ("PERCENT".equalsIgnoreCase(voucher.getDiscountType())) {
            discountAmount = subTotal * voucher.getDiscountValue() / 100.0;
        }

        // Đảm bảo tiền giảm không vượt quá tổng tiền tạm tính
        if (discountAmount > subTotal) {
            discountAmount = subTotal;
        }

        double finalTotal = subTotal - discountAmount;

        // Trả kết quả JSON về cho Javascript xử lý hiển thị
        out.write(String.format(
                "{\"status\": 1, \"message\": \"Áp dụng mã thành công!\", \"discountAmount\": %.0f, \"finalTotal\": %.0f}",
                discountAmount, finalTotal
        ));
    }
}