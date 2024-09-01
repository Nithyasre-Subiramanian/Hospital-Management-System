
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/hospitalmanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "nithu654"; 

    public static Connection getConnection() {
        Connection connection = null;
        try {
        	
            Class.forName("com.mysql.cj.jdbc.Driver");
           
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //System.out.println("Connection established successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to establish a connection.");
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Failed to close the connection.");
            }
        }
    }
}

