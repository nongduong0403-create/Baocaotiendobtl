package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Khai báo driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Thông tin kết nối
            String url = "jdbc:mysql://localhost:3306/cinema_management?useUnicode=true&characterEncoding=UTF-8";
            String user = "root";

            // CHÚ Ý: Đổi "123456" thành mật khẩu root thực tế của cậu
            String password = "admin";

            // Mở kết nối
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Kết nối MySQL thành công!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Kết nối thất bại: " + e.getMessage());
        }
        return conn;
    }

    // Hàm main dùng để chạy test thử kết nối
    public static void main(String[] args) {
        System.out.println("Đang thử kết nối...");
        getConnection();
    }
}