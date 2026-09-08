package controller;

import dal.RoomDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteRoomServlet", urlPatterns = {"/deleteRoom"})
public class DeleteRoomServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idRaw = request.getParameter("id");
        try {
            int id = Integer.parseInt(idRaw);
            RoomDAO dao = new RoomDAO();
            dao.deleteRoom(id); // Gọi lệnh xóa
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("admin"); // Xóa xong thì tải lại trang admin
    }
}