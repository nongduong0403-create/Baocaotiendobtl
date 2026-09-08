package controller;

import dal.UserDAO;
import dal.TicketDAO;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// Cấu hình đường dẫn truy cập là /login
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    // Hàm doGet: Khi gõ link URL thì sẽ hiển thị cái form đăng nhập (file login.jsp)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    // Hàm doPost: Hứng dữ liệu khi người dùng bấm nút "Đăng nhập" trên form
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Lấy tài khoản và mật khẩu từ ô input trên form
        String u = request.getParameter("txtUser");
        String p = request.getParameter("txtPass");

        // 2. Giao cho DAO xuống DB kiểm tra
        UserDAO dao = new UserDAO();
        User user = dao.checkLogin(u, p);

        // 3. Xử lý kết quả
        if (user != null) {
            // Lưu thông tin user vào Session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // ---> THÊM ĐOẠN NÀY ĐỂ ĐẾM SỐ VÉ:
            TicketDAO ticketDAO = new TicketDAO();
            int totalTickets = ticketDAO.countTicketsByUser(user.getId());
            session.setAttribute("totalTickets", totalTickets);
            // <--- KẾT THÚC ĐOẠN ĐẾM VÉ

            // Kiểm tra xem khách có đang đặt dang dở vé nào trước đó không
            String pendingSeatIds = (String) session.getAttribute("pendingSeatIds");
            String pendingRoomId = (String) session.getAttribute("pendingRoomId");

            if (pendingSeatIds != null && pendingRoomId != null) {
                // Xóa dữ liệu tạm trong session để tránh lặp
                session.removeAttribute("pendingSeatIds");
                session.removeAttribute("pendingRoomId");

                // Gọi thẳng sang hàm chốt vé của BookTicketServlet để xuất hóa đơn luôn!
                BookTicketServlet.processBooking(request, response, user, pendingSeatIds, pendingRoomId);
            } else {
                // Nếu đăng nhập bình thường từ đầu thì chuyển hướng sang trang chủ
                response.sendRedirect("home");
            }
        } else {
            // Nếu sai -> Nhét thông báo lỗi vào biến "error", ném trả lại trang đăng nhập
            request.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}