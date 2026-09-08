package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TicketDAO {

    // Hàm lưu lịch sử đặt vé vào Database
    public void insertTicket(int userId, int seatId, int price) {
        String sql = "INSERT INTO Ticket (user_id, seat_id, price) VALUES (?, ?, ?)";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, seatId);
            ps.setInt(3, price);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi lưu lịch sử vé: " + e.getMessage());
        }
    }

    // Hàm đếm tổng số vé khách đã mua
    public int countTicketsByUser(int userId) {
        String sql = "SELECT COUNT(*) FROM Ticket WHERE user_id = ?";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Lỗi đếm vé: " + e.getMessage());
        }
        return 0;
    }
}