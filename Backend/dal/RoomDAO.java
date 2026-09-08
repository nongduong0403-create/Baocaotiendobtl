package dal;

import model.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    // Hàm lấy danh sách tất cả các phòng chiếu
    public List<Room> getAllRooms() {
        List<Room> list = new ArrayList<>();
        String query = "SELECT * FROM Room"; // Lệnh SQL

        try {
            Connection conn = DBContext.getConnection(); // Mở kết nối
            PreparedStatement ps = conn.prepareStatement(query); // Ném câu lệnh SQL vào
            ResultSet rs = ps.executeQuery(); // Chạy lệnh và nhận kết quả

            // Vòng lặp để nhặt từng dòng dữ liệu trong MySQL cho vào danh sách
            while (rs.next()) {
                list.add(new Room(rs.getInt("id"), rs.getString("name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Hàm main để test thử xem có lấy được dữ liệu không
    public static void main(String[] args) {
        RoomDAO dao = new RoomDAO();
        List<Room> list = dao.getAllRooms();

        System.out.println("--- DANH SÁCH RẠP PHIM ĐANG CÓ ---");
        for (Room r : list) {
            System.out.println(r.toString());
        }
    }
    // HÀM MỚI: Tự động sinh 120 ghế khi tạo rạp
    public void insertRoom(String name) {
        String queryRoom = "INSERT INTO Room (name) VALUES (?)";
        String querySeat = "INSERT INTO Seat (room_id, row_name, seat_number, type, status) VALUES (?, ?, ?, ?, 'AVAILABLE')";

        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement psRoom = conn.prepareStatement(queryRoom, java.sql.Statement.RETURN_GENERATED_KEYS);
            psRoom.setString(1, name);
            psRoom.executeUpdate();

            java.sql.ResultSet rs = psRoom.getGeneratedKeys();
            if (rs.next()) {
                int newRoomId = rs.getInt(1);

                PreparedStatement psSeat = conn.prepareStatement(querySeat);
                // 8 Hàng ghế từ A đến H
                String[] rows = {"A", "B", "C", "D", "E", "F", "G", "H"};

                for (String row : rows) {
                    for (int i = 1; i <= 15; i++) { // 15 ghế mỗi hàng
                        psSeat.setInt(1, newRoomId);
                        psSeat.setString(2, row);
                        psSeat.setInt(3, i);

                        // Phân loại: Hàng A, B, C, D là ghế Thường, E, F, G, H là VIP
                        if (row.equals("A") || row.equals("B") || row.equals("C") || row.equals("D")) {
                            psSeat.setString(4, "Thường");
                        } else {
                            psSeat.setString(4, "VIP");
                        }

                        psSeat.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // HÀM MỚI 2: Xóa rạp chiếu phim (Xóa ghế trước rồi mới xóa rạp)
    public void deleteRoom(int id) {
        String queryDeleteSeats = "DELETE FROM Seat WHERE room_id = ?";
        String queryDeleteRoom = "DELETE FROM Room WHERE id = ?";
        try {
            Connection conn = DBContext.getConnection();
            // 1. Xóa hết ghế của rạp này
            PreparedStatement ps1 = conn.prepareStatement(queryDeleteSeats);
            ps1.setInt(1, id);
            ps1.executeUpdate();

            // 2. Xóa rạp
            PreparedStatement ps2 = conn.prepareStatement(queryDeleteRoom);
            ps2.setInt(1, id);
            ps2.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}