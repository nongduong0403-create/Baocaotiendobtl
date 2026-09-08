package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PromotionServlet", urlPatterns = {"/promotions"})
public class PromotionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Điều hướng sang trang giao diện Khuyến mãi
        request.getRequestDispatcher("promotion.jsp").forward(request, response);
    }
}