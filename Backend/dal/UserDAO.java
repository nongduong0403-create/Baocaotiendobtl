package dal;

import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    // Hàm kiểm tra đăng nhập
    public User checkLogin(String username, String password) {
        String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));

                // ---> 3 DÒNG LẤY DỮ LIỆU CÁ NHÂN MỚI TỪ DATABASE <---
                u.setFullName(rs.getString("full_name"));
                u.setPoints(rs.getInt("points"));
                u.setMembershipTier(rs.getString("membership_tier"));

                return u;
            }
        } catch (Exception e) {
            System.out.println("Lỗi đăng nhập: " + e.getMessage());
        }
        return null;
    }
    // Hàm main để test thử thuật toán
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();

        // Thử đăng nhập bằng tài khoản mẫu trong DB
        User u = dao.checkLogin("admin", "123456");

        if (u != null) {
            System.out.println("Đăng nhập thành công! Quyền của bạn là: " + u.getRole());
        } else {
            System.out.println("Sai tài khoản hoặc mật khẩu!");
        }
    }
    // Hàm đăng ký tài khoản khách hàng mới
    public boolean register(String username, String password) {
        String sql = "INSERT INTO User (username, password, role) VALUES (?, ?, 'CUSTOMER')";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi đăng ký: " + e.getMessage());
        }
        return false;
    }
}