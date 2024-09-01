import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicinePricesDao {
    public void insertMedicinePrice(MedicinePrice medicinePrice) {
        String sql = "INSERT INTO medicine_prices (medicine_name, price) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medicinePrice.getMedicineName());
            stmt.setBigDecimal(2, medicinePrice.getPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MedicinePrice getMedicinePriceById(int medicineId) {
        String sql = "SELECT * FROM medicine_prices WHERE medicine_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, medicineId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new MedicinePrice(
                        rs.getInt("medicine_id"),
                        rs.getString("medicine_name"),
                        rs.getBigDecimal("price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

