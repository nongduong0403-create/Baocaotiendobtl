package controller;

import dal.SeatDAO;
import dal.TicketDAO;
import model.Seat;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookTicketServlet", urlPatterns = {"/bookTicket"})
public class BookTicketServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        String seatIdsStr = request.getParameter("seatIds");
        String roomIdStr = request.getParameter("roomId");

        // Nếu CHƯA ĐĂNG NHẬP -> Lưu tạm dữ liệu ghế vào Session rồi chuyển sang trang login
        if (currentUser == null) {
            session.setAttribute("pendingSeatIds", seatIdsStr);
            session.setAttribute("pendingRoomId", roomIdStr);
            response.sendRedirect("login.jsp");
            return;
        }

        // ĐÃ ĐĂNG NHẬP -> Tiến hành chốt vé bình thường
        processBooking(request, response, currentUser, seatIdsStr, roomIdStr);
    }

    // Tách logic chốt đơn ra hàm riêng cho sạch sẽ
    public static void processBooking(HttpServletRequest request, HttpServletResponse response, User currentUser, String seatIdsStr, String roomIdStr) throws ServletException, IOException {
        if (seatIdsStr != null && !seatIdsStr.isEmpty() && roomIdStr != null) {
            SeatDAO seatDAO = new SeatDAO();
            TicketDAO ticketDAO = new TicketDAO();

            List<Seat> bookedSeats = new ArrayList<>();
            int totalAmount = 0;

            String[] seatIdArray = seatIdsStr.split(",");

            for (String idStr : seatIdArray) {
                int seatId = Integer.parseInt(idStr.trim());
                seatDAO.updateSeatStatus(seatId, "BOOKED");

                Seat s = seatDAO.getSeatById(seatId);
                bookedSeats.add(s);

                int price = "VIP".equalsIgnoreCase(s.getType()) ? 85000 : 65000;
                totalAmount += price;

                ticketDAO.insertTicket(currentUser.getId(), seatId, price);
            }

            request.setAttribute("bookedSeats", bookedSeats);
            request.setAttribute("totalAmount", totalAmount);
            request.setAttribute("roomId", roomIdStr);

            request.getRequestDispatcher("ticket.jsp").forward(request, response);
        } else {
            response.sendRedirect("home");
        }
    }
}