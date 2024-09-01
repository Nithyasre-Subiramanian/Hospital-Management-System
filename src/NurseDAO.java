import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NurseDAO {
    private Connection connection;

    public NurseDAO(Connection connection) {
        this.connection = connection;
    }

    public void addNurse(Nurse nurse) throws SQLException {
        String sql = "INSERT INTO Nurse (employeeId, departmentId) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, nurse.getEmployeeId());
            statement.setInt(2, nurse.getDepartmentId());
            statement.executeUpdate();
        }
    }

    public Nurse getNurseById(int nurseId) throws SQLException {
        String sql = "SELECT * FROM Nurse WHERE nurseId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, nurseId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Nurse(
                    resultSet.getInt("nurseId"),
                    resultSet.getInt("employeeId"),
                    resultSet.getInt("departmentId")
                );
            }
        }
        return null;
    }

    public List<Nurse> getAllNurses() throws SQLException {
        List<Nurse> nurses = new ArrayList<>();
        String sql = "SELECT * FROM Nurse";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                nurses.add(new Nurse(
                    resultSet.getInt("nurseId"),
                    resultSet.getInt("employeeId"),
                    resultSet.getInt("departmentId")
                ));
            }
        }
        return nurses;
    }
}
