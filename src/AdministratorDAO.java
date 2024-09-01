import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDAO {
    private Connection connection;

    public AdministratorDAO(Connection connection) {
        this.connection = connection;
    }

    public void addAdministrator(Administrator administrator) throws SQLException {
        String sql = "INSERT INTO Administrator (employeeId, departmentId) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, administrator.getEmployeeId());
            statement.setInt(2, administrator.getDepartmentId());
            statement.executeUpdate();
        }
    }

    public Administrator getAdministratorById(int administratorId) throws SQLException {
        String sql = "SELECT * FROM Administrator WHERE administratorId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, administratorId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Administrator(
                    resultSet.getInt("administratorId"),
                    resultSet.getInt("employeeId"),
                    resultSet.getInt("departmentId")
                );
            }
        }
        return null;
    }

    public List<Administrator> getAllAdministrators() throws SQLException {
        List<Administrator> administrators = new ArrayList<>();
        String sql = "SELECT * FROM Administrator";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                administrators.add(new Administrator(
                    resultSet.getInt("administratorId"),
                    resultSet.getInt("employeeId"),
                    resultSet.getInt("departmentId")
                ));
            }
        }
        return administrators;
    }
}
