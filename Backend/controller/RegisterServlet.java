package controller;

import dal.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String rePass = request.getParameter("confirmPassword");

        if (!pass.equals(rePass)) {
            request.setAttribute("error", "Mật khẩu xác nhận không khớp!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        UserDAO userDAO = new UserDAO();
        boolean success = userDAO.register(user, pass);

        if (success) {
            response.sendRedirect("login.jsp"); // Đăng ký thành công thì chuyển về trang đăng nhập
        } else {
            request.setAttribute("error", "Tên đăng nhập đã tồn tại hoặc có lỗi xảy ra!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}