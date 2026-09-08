package controller;

import dal.SeatDAO;
import model.Seat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// Đường dẫn truy cập là /seats
@WebServlet(name = "SeatServlet", urlPatterns = {"/seats"})
public class SeatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Nhận ID của rạp chiếu phim từ trên URL xuống (Ví dụ: /seats?roomId=1)
        String roomIdRaw = request.getParameter("roomId");

        try {
            int roomId = Integer.parseInt(roomIdRaw);

            // 2. Gọi DAO lấy danh sách ghế của rạp đó
            SeatDAO dao = new SeatDAO();
            List<Seat> listSeats = dao.getSeatsByRoom(roomId);

            // 3. Đóng gói dữ liệu gửi sang trang JSP
            request.setAttribute("seatList", listSeats);
            request.setAttribute("roomId", roomId); // Gửi thêm ID rạp để in ra tiêu đề
            request.getRequestDispatcher("seat.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}