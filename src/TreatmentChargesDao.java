import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TreatmentChargesDao {
    public void insertTreatmentCharge(TreatmentCharge treatmentCharge) {
        String sql = "INSERT INTO treatment_charges (treatment_name, charge) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, treatmentCharge.getTreatmentName());
            stmt.setBigDecimal(2, treatmentCharge.getCharge());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TreatmentCharge getTreatmentChargeById(int treatmentId) {
        String sql = "SELECT * FROM treatment_charges WHERE treatment_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, treatmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new TreatmentCharge(
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
