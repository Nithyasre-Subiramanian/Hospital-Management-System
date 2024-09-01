import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PharmacistDAO {
    private Connection connection;

    public PharmacistDAO(Connection connection) {
        this.connection = connection;
    }

    public void addPharmacist(Pharmacist pharmacist) throws SQLException {
        String sql = "INSERT INTO Pharmacist (employeeId, certification, departmentId) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pharmacist.getEmployeeId());
            statement.setString(2, pharmacist.getCertification());
            statement.setInt(3, pharmacist.getDepartmentId());
            statement.executeUpdate();
        }
    }

    public Pharmacist getPharmacistById(int pharmacistId) throws SQLException {
        String sql = "SELECT * FROM Pharmacist WHERE pharmacistId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pharmacistId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Pharmacist(
                    resultSet.getInt("pharmacistId"),
                    resultSet.getInt("employeeId"),
                    resultSet.getString("certification"),
                    resultSet.getInt("departmentId")
                );
            }
        }
        return null;
    }

    public List<Pharmacist> getAllPharmacists() throws SQLException {
        List<Pharmacist> pharmacists = new ArrayList<>();
        String sql = "SELECT * FROM Pharmacist";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                pharmacists.add(new Pharmacist(
                    resultSet.getInt("pharmacistId"),
                    resultSet.getInt("employeeId"),
                    resultSet.getString("certification"),
                    resultSet.getInt("departmentId")
                ));
            }
        }
        return pharmacists;
    }
}
