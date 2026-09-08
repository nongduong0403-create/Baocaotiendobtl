package controller;

import dal.RoomDAO;
import model.Room;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

    // Hiển thị danh sách rạp
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoomDAO dao = new RoomDAO();
        List<Room> listRooms = dao.getAllRooms();
        request.setAttribute("roomList", listRooms);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    // Xử lý khi bấm nút "Thêm Rạp"
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8"); // Hỗ trợ gõ tiếng Việt có dấu
        String roomName = request.getParameter("roomName"); // Nhận tên rạp

        RoomDAO dao = new RoomDAO();
        dao.insertRoom(roomName); // Thêm vào Database

        response.sendRedirect("admin"); // Load lại trang để thấy kết quả
    }
}