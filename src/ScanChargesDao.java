import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScanChargesDao {
    public void insertScanCharge(ScanCharge scanCharge) {
        String sql = "INSERT INTO scan_charges (scan_type, charge) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, scanCharge.getScanType());
            stmt.setBigDecimal(2, scanCharge.getCharge());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ScanCharge getScanChargeById(int scanId) {
        String sql = "SELECT * FROM scan_charges WHERE scan_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, scanId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ScanCharge(
                        rs.getInt("scan_id"),
                        rs.getString("scan_type"),
                        rs.getBigDecimal("charge")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
