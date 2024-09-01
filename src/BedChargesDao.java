import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BedChargesDao {
    public void insertBedCharge(BedCharge bedCharge) {
        String sql = "INSERT INTO bed_charges (bed_type, daily_rate) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bedCharge.getBedType());
            stmt.setBigDecimal(2, bedCharge.getDailyRate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BedCharge getBedChargeById(int bedId) {
        String sql = "SELECT * FROM bed_charges WHERE bed_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bedId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new BedCharge(
                        rs.getInt("bed_id"),
                        rs.getString("bed_type"),
                        rs.getBigDecimal("daily_rate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
