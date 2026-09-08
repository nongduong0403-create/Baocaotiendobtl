package controller;

import dal.MovieDAO;
import model.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Gọi DAO để lấy danh sách phim
        MovieDAO movieDAO = new MovieDAO();
        List<Movie> movieList = movieDAO.getAllMovies();

        // 2. Gắn danh sách phim vào request để truyền sang JSP
        request.setAttribute("movieList", movieList);

        // 3. Chuyển hướng sang file home.jsp
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}