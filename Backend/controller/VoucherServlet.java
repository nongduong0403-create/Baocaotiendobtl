package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VoucherServlet", urlPatterns = {"/vouchers"})
public class VoucherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Tạm thời điều hướng sang giao diện.
        // Sau này cậu có thể gọi VoucherDAO ở đây để lấy danh sách mã của user đang đăng nhập.
        request.getRequestDispatcher("voucher.jsp").forward(request, response);
    }
}