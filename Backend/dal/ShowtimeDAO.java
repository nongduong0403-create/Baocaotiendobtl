package dal;

import model.Showtime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShowtimeDAO {
    // Hàm lấy danh sách suất chiếu dựa vào Mã phim
    public List<Showtime> getShowtimesByMovieId(int movieId) {
        List<Showtime> list = new ArrayList<>();
        // Câu lệnh JOIN bảng Showtime với bảng Room để lấy được tên Rạp
        String sql = "SELECT s.id, s.movie_id, s.room_id, r.name AS room_name, s.show_time " +
                "FROM Showtime s " +
                "JOIN Room r ON s.room_id = r.id " +
                "WHERE s.movie_id = ?";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, movieId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Showtime(
                        rs.getInt("id"),
                        rs.getInt("movie_id"),
                        rs.getInt("room_id"),
                        rs.getString("room_name"),
                        rs.getString("show_time")
                ));
            }
        } catch (Exception e) {
            System.out.println("Lỗi lấy suất chiếu: " + e.getMessage());
        }
        return list;
    }
}