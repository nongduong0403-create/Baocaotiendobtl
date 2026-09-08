package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy session hiện tại mà không tạo mới
        HttpSession session = request.getSession(false);

        // Nếu session tồn tại, tiến hành hủy bỏ
        if (session != null) {
            session.invalidate();
        }

        // Chuyển hướng người dùng về trang chủ (hoặc trang login tùy ý)
        response.sendRedirect("home");
    }
}