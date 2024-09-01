
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultationFeesDao {
    public void insertConsultationFee(ConsultationFee consultationFee) {
        String sql = "INSERT INTO consultation_fees (consultation_type, fee_amount) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, consultationFee.getConsultationType());
            stmt.setBigDecimal(2, consultationFee.getFeeAmount());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ConsultationFee getConsultationFeeById(int consultationId) {
        String sql = "SELECT * FROM consultation_fees WHERE consultation_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, consultationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ConsultationFee(
                        rs.getInt("consultation_id"),
                        rs.getString("consultation_type"),
                        rs.getBigDecimal("fee_amount")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
