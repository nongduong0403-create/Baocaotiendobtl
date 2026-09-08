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

// Khai báo đường dẫn để truy cập trang web này là /rooms
@WebServlet(name = "RoomServlet", urlPatterns = {"/rooms"})
public class RoomServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Gọi DAO đi lấy dữ liệu từ MySQL
        RoomDAO dao = new RoomDAO();
        List<Room> listRooms = dao.getAllRooms();

        // 2. Đóng gói danh sách dữ liệu vào một cái hộp tên là "roomList"
        request.setAttribute("roomList", listRooms);

        // 3. Gửi cái hộp đó sang trang giao diện (JSP) để hiển thị
        request.getRequestDispatcher("room.jsp").forward(request, response);
    }
}