import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OthersDAO {
    private Connection connection;

    public OthersDAO(Connection connection) {
        this.connection = connection;
    }

    public void addOther(Others other) throws SQLException {
        String sql = "INSERT INTO Others (employeeId, departmentId, roleDescription) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, other.getEmployeeId());
            statement.setInt(2, other.getDepartmentId());
            statement.setString(3, other.getRoleDescription());
            statement.executeUpdate();
        }
    }

    public Others getOtherById(int otherId) throws SQLException {
        String sql = "SELECT * FROM Others WHERE otherId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, otherId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Others(
                    resultSet.getInt("otherId"),
                    resultSet.getInt("employeeId"),
                    resultSet.getInt("departmentId"),
                    resultSet.getString("roleDescription")
                );
            }
        }
        return null;
    }

    public List<Others> getAllOthers() throws SQLException {
        List<Others> others = new ArrayList<>();
        String sql = "SELECT * FROM Others";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                others.add(new Others(
                    resultSet.getInt("otherId"),
                    resultSet.getInt("employeeId"),
                    resultSet.getInt("departmentId"),
                    resultSet.getString("roleDescription")
                ));
            }
        }
        return others;
    }
}
