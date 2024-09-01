import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public User authenticateUser(String username, String password) throws SQLException {
        String query = "SELECT userId, username, password, email, role, isActive FROM users WHERE username = ? AND password = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                String userRole = resultSet.getString("role");
                boolean isActive = resultSet.getBoolean("isActive");

                if (isActive) {
                    return new User(userId, username, password, resultSet.getString("email"), userRole);
                } else {
                    System.out.println("User is not active.");
                }
            } else {
                System.out.println("Invalid username or password.");
            }
        }
        return null;
    }
}
