import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TechnicianDAO {
    private Connection connection;

    public TechnicianDAO(Connection connection) {
        this.connection = connection;
    }

    public void addTechnician(Technician technician) throws SQLException {
        String sql = "INSERT INTO Technician (employeeId, certification, departmentId) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, technician.getEmployeeId());
            statement.setString(2, technician.getCertification());
            statement.setInt(3, technician.getDepartmentId());
            statement.executeUpdate();
        }
    }

    public Technician getTechnicianById(int technicianId) throws SQLException {
        String sql = "SELECT * FROM Technician WHERE technicianId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, technicianId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Technician(
                    resultSet.getInt("technicianId"),
                    resultSet.getInt("employeeId"),
                    resultSet.getString("certification"),
                    resultSet.getInt("departmentId")
                );
            }
        }
        return null;
    }

    public List<Technician> getAllTechnicians() throws SQLException {
        List<Technician> technicians = new ArrayList<>();
        String sql = "SELECT * FROM Technician";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                technicians.add(new Technician(
                    resultSet.getInt("technicianId"),
                    resultSet.getInt("employeeId"),
                    resultSet.getString("certification"),
                    resultSet.getInt("departmentId")
                ));
            }
        }
        return technicians;
    }
}
