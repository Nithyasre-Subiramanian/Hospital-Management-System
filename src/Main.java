import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class Main {
	
	public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter username: ");
			String username = scanner.nextLine();

			System.out.print("Enter password: ");
			String password = scanner.nextLine();

			try (Connection connection = DatabaseConnection.getConnection()) {
			    String query = "SELECT userId, role FROM users WHERE username = ? AND password = ?";
			    try (PreparedStatement statement = connection.prepareStatement(query)) {
			        statement.setString(1, username);
			        statement.setString(2, password);
			        
			        ResultSet resultSet = statement.executeQuery();
			        
			        if (resultSet.next()) {
			            int userId = resultSet.getInt("userId");
			            String role = resultSet.getString("role");

			            if ("Admin".equalsIgnoreCase(role)) {
			                System.out.println("Welcome Admin!");
			                AdminMenu.showMenu();
			            } else if ("User".equalsIgnoreCase(role)) {
			                System.out.println("Welcome User!");
			                UserMenu.showMenu();
			            } else {
			                System.out.println("Invalid role.");
			            }
			        } else {
			            System.out.println("Invalid username or password.");
			        }
			    }
			} catch (SQLException e) {
			    e.printStackTrace();
			}
		}
    }

}

