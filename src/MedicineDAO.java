import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO {

   
    public static void addMedicine(Medicine medicine) throws SQLException {
        String query = "INSERT INTO medicine (medicine_name, price) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, medicine.getMedicineName());
            statement.setDouble(2, medicine.getPrice());
            statement.executeUpdate();
        }
    }

    
    public static Medicine getMedicineById(int medicineId) throws SQLException {
        String query = "SELECT * FROM medicine WHERE medicine_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, medicineId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Medicine(
                        resultSet.getInt("medicine_id"),
                        resultSet.getString("medicine_name"),
                        resultSet.getDouble("price")
                );
            }
            return null;
        }
    }

   
    public static void updateMedicine(Medicine medicine) throws SQLException {
        String query = "UPDATE medicine SET medicine_name = ?, price = ? WHERE medicine_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, medicine.getMedicineName());
            statement.setDouble(2, medicine.getPrice());
            statement.setInt(3, medicine.getMedicineId());
            statement.executeUpdate();
        }
    }

    // Delete a medicine
    public static void deleteMedicine(int medicineId) throws SQLException {
        String query = "DELETE FROM medicine WHERE medicine_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, medicineId);
            statement.executeUpdate();
        }
    }

   
    public static List<Medicine> getAllMedicines() throws SQLException {
        List<Medicine> medicines = new ArrayList<>();
        String query = "SELECT * FROM medicine";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                medicines.add(new Medicine(
                        resultSet.getInt("medicine_id"),
                        resultSet.getString("medicine_name"),
                        resultSet.getDouble("price")
                ));
            }
        }
        return medicines;
    }
}
