package dal;

import model.Movie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    // Hàm lấy toàn bộ danh sách phim đang có
    public List<Movie> getAllMovies() {
        List<Movie> list = new ArrayList<>();
        String sql = "SELECT * FROM Movie";

        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // 1. Khởi tạo đối tượng phim với các thông tin cơ bản ban đầu
                Movie m = new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("poster_url"),
                        rs.getInt("duration"),
                        rs.getString("description")
                );

                // 2. Bổ sung 3 thuộc tính mới lấy từ Database
                m.setStatus(rs.getInt("status"));
                m.setAgeRating(rs.getString("age_rating"));
                m.setHot(rs.getBoolean("is_hot"));

                list.add(m);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách phim: " + e.getMessage());
        }
        return list;
    }

    // Hàm lấy thông tin 1 bộ phim cụ thể dựa vào ID
    public Movie getMovieById(int id) {
        String sql = "SELECT * FROM Movie WHERE id = ?";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // 1. Khởi tạo đối tượng phim với các thông tin cơ bản ban đầu
                Movie m = new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("poster_url"),
                        rs.getInt("duration"),
                        rs.getString("description")
                );

                // 2. Bổ sung 3 thuộc tính mới lấy từ Database
                m.setStatus(rs.getInt("status"));
                m.setAgeRating(rs.getString("age_rating"));
                m.setHot(rs.getBoolean("is_hot"));

                return m;
            }
        } catch (Exception e) {
            System.out.println("Lỗi tìm phim: " + e.getMessage());
        }
        return null;
    }
}