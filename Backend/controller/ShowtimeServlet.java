package controller;

import dal.MovieDAO;
import dal.ShowtimeDAO;
import model.Movie;
import model.Showtime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowtimeServlet", urlPatterns = {"/showtimes"})
public class ShowtimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Lấy ID của bộ phim mà người dùng vừa click vào
        String movieIdStr = request.getParameter("movieId");
        if (movieIdStr != null) {
            int movieId = Integer.parseInt(movieIdStr);

            // 2. Gọi DAO để lấy Thông tin phim & Danh sách suất chiếu
            MovieDAO movieDAO = new MovieDAO();
            Movie movie = movieDAO.getMovieById(movieId);

            ShowtimeDAO showtimeDAO = new ShowtimeDAO();
            List<Showtime> showtimeList = showtimeDAO.getShowtimesByMovieId(movieId);

            // 3. Đẩy dữ liệu sang trang showtime.jsp
            request.setAttribute("movie", movie);
            request.setAttribute("showtimeList", showtimeList);
            request.getRequestDispatcher("showtime.jsp").forward(request, response);
        } else {
            response.sendRedirect("home"); // Nếu không có ID phim thì đá về trang chủ
        }
    }
}