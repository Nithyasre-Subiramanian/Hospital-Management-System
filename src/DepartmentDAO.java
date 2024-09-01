import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepartmentDAO {
	public static void addDepartment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter department details:");
        System.out.print("Department Name: ");
        String departmentName = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Head ID: ");
        int headId = scanner.nextInt();
        System.out.print("Bed Count: ");
        int bedCount = scanner.nextInt();

        Department department = new Department(0, departmentName, description, headId, bedCount);
        try {
            addDepartment(department);
            System.out.println("Department added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewDepartmentDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter department ID to view: ");
        int departmentId = scanner.nextInt();
        
        try {
            Department department = getDepartmentById(departmentId);
            if (department != null) {
                System.out.println("Department ID: " + department.getDepartmentId());
                System.out.println("Name: " + department.getDepartmentName());
                System.out.println("Description: " + department.getDescription());
                System.out.println("Head ID: " + department.getHeadId());
                System.out.println("Bed Count: " + department.getBedCount());
            } else {
                System.out.println("Department not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateDepartment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter department ID to update: ");
        int departmentId = scanner.nextInt();
        scanner.nextLine(); 
        try {
            Department department = getDepartmentById(departmentId);
            if (department != null) {
                System.out.println("Current details:");
                System.out.println("Name: " + department.getDepartmentName());
                System.out.println("Description: " + department.getDescription());
                System.out.println("Head ID: " + department.getHeadId());
                System.out.println("Bed Count: " + department.getBedCount());

                System.out.print("New Department Name: ");
                String departmentName = scanner.nextLine();
                System.out.print("New Description: ");
                String description = scanner.nextLine();
                System.out.print("New Head ID: ");
                int headId = scanner.nextInt();
                System.out.print("New Bed Count: ");
                int bedCount = scanner.nextInt();

                department.setDepartmentName(departmentName);
                department.setDescription(description);
                department.setHeadId(headId);
                department.setBedCount(bedCount);

                updateDepartment(department);
                System.out.println("Department updated successfully.");
            } else {
                System.out.println("Department not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDepartment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter department ID to delete: ");
        int departmentId = scanner.nextInt();
        
        try {
           deleteDepartment(departmentId);
            System.out.println("Department deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



   //  Add a department
    public static void addDepartment(Department department) throws SQLException {
        String query = "INSERT INTO department (departmentName, description, headId, bedCount) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, department.getDepartmentName());
            statement.setString(2, department.getDescription());
            statement.setInt(3, department.getHeadId());
            statement.setInt(4, department.getBedCount());
            statement.executeUpdate();
        }
    }

    // Get department by ID
    public static Department getDepartmentById(int departmentId) throws SQLException {
        String query = "SELECT * FROM department WHERE departmentId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, departmentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Department(
                        resultSet.getInt("departmentId"),
                        resultSet.getString("departmentName"),
                        resultSet.getString("description"),
                        resultSet.getInt("headId"),
                        resultSet.getInt("bedCount")
                );
            }
        }
        return null;
    }

    // Update a department
    public static void updateDepartment(Department department) throws SQLException {
        String query = "UPDATE department SET departmentName = ?, description = ?, headId = ?, bedCount = ? WHERE departmentId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, department.getDepartmentName());
            statement.setString(2, department.getDescription());
            statement.setInt(3, department.getHeadId());
            statement.setInt(4, department.getBedCount());
            statement.setInt(5, department.getDepartmentId());
            statement.executeUpdate();
        }
    }

    // Delete a department
    public static void deleteDepartment(int departmentId) throws SQLException {
        String query = "DELETE FROM department WHERE departmentId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, departmentId);
            statement.executeUpdate();
        }
    }

    // Get all departments
    public static List<Department> getAllDepartments() throws SQLException {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM department";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                departments.add(new Department(
                        resultSet.getInt("departmentId"),
                        resultSet.getString("departmentName"),
                        resultSet.getString("description"),
                        resultSet.getInt("headId"),
                        resultSet.getInt("bedCount")
                ));
            }
        }
        return departments;
    }
}
