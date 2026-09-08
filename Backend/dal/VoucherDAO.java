package dal;

import model.Voucher;
import java.sql.*;

public class VoucherDAO {

    // Hàm tìm voucher theo mã code
    public Voucher getVoucherByCode(String code) {
        Voucher voucher = null;
        String query = "SELECT * FROM Voucher WHERE code = ? AND status = 1 AND expiration_date >= NOW()";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    voucher = new Voucher();
                    voucher.setId(rs.getInt("id"));
                    voucher.setCode(rs.getString("code"));
                    voucher.setDiscountType(rs.getString("discount_type"));
                    voucher.setDiscountValue(rs.getInt("discount_value"));
                    voucher.setMinOrderValue(rs.getInt("min_order_value"));
                    voucher.setExpirationDate(rs.getTimestamp("expiration_date"));
                    voucher.setUsageLimit(rs.getInt("usage_limit"));
                    voucher.setStatus(rs.getInt("status"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voucher;
    }
}