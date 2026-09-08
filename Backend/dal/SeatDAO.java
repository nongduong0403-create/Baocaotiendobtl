package dal;

import model.Seat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SeatDAO {

    // 1. Hàm lấy danh sách ghế (Đã cập nhật thêm cột status)
    public List<Seat> getSeatsByRoom(int roomId) {
        List<Seat> list = new ArrayList<>();
        String query = "SELECT * FROM Seat WHERE room_id = ?";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, roomId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Seat(
                        rs.getInt("id"),
                        rs.getInt("room_id"),
                        rs.getString("row_name"),
                        rs.getInt("seat_number"),
                        rs.getString("type"),
                        rs.getString("status") // Kéo thêm status từ MySQL
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. HÀM MỚI: Cập nhật trạng thái ghế thành "BOOKED" khi có người đặt
    public void bookSeat(int seatId) {
        String query = "UPDATE Seat SET status = 'BOOKED' WHERE id = ?";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, seatId);
            ps.executeUpdate(); // Chú ý: Lệnh UPDATE dùng executeUpdate()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Hàm 1: Cập nhật trạng thái ghế khi khách đặt
    public void updateSeatStatus(int seatId, String status) {
        String sql = "UPDATE Seat SET status = ? WHERE id = ?";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, seatId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật ghế: " + e.getMessage());
        }
    }

    // Hàm 2: Lấy thông tin 1 ghế cụ thể để in ra vé
    public Seat getSeatById(int seatId) {
        String sql = "SELECT * FROM Seat WHERE id = ?";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, seatId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Seat(
                        rs.getInt("id"),
                        rs.getInt("room_id"),
                        rs.getString("row_name"),
                        rs.getInt("seat_number"),
                        rs.getString("type"),
                        rs.getString("status")
                );
            }
        } catch (Exception e) {
            System.out.println("Lỗi lấy thông tin ghế: " + e.getMessage());
        }
        return null;
    }
}