
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeDAO {

    public static void addEmployee() {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter first name: ");
   
			String firstName = scanner.nextLine();
			System.out.print("Enter last name: ");
			String lastName = scanner.nextLine();
			System.out.print("Enter contact number: ");
			String contactNumber = scanner.nextLine();
			System.out.print("Enter email: ");
			String email = scanner.nextLine();
			System.out.print("Enter base salary: ");
			double baseSalary = scanner.nextDouble();
			System.out.print("Enter years of experience: ");
			int yearsOfExperience = scanner.nextInt();
			scanner.nextLine(); 
			System.out.print("Enter role: ");
			String role = scanner.nextLine();
			System.out.print("Enter specialization: ");
			String specialization = scanner.nextLine();
			System.out.print("Enter department: ");
			String department = scanner.nextLine();
			System.out.print("Enter certification: ");
			String certification = scanner.nextLine();
			System.out.print("Enter gender: ");
			String gender = scanner.nextLine();
			System.out.print("Enter age: ");
			int age = scanner.nextInt();
			scanner.nextLine(); 
			System.out.print("Enter blood group: ");
			String bloodGroup = scanner.nextLine();

			try (Connection connection = DatabaseConnection.getConnection()) {
			    String query = "INSERT INTO Employee (firstName, lastName, contactNumber, email, baseSalary, yearsOfExperience, role, specialization, department, certification, gender, age, bloodGroup) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			    try (PreparedStatement statement = connection.prepareStatement(query)) {
			        statement.setString(1, firstName);
			        statement.setString(2, lastName);
			        statement.setString(3, contactNumber);
			        statement.setString(4, email);
			        statement.setDouble(5, baseSalary);
			        statement.setInt(6, yearsOfExperience);
			        statement.setString(7, role);
			        statement.setString(8, specialization);
			        statement.setString(9, department);
			        statement.setString(10, certification);
			        statement.setString(11, gender);
			        statement.setInt(12, age);
			        statement.setString(13, bloodGroup);
			        statement.executeUpdate();
			        System.out.println("Employee added successfully.");
			    }
			} catch (SQLException e) {
			    e.printStackTrace();
			}
		}
    }

    public static void viewEmployeeDetails() {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter employee ID to view details: ");
			int employeeId = scanner.nextInt();

			try (Connection connection = DatabaseConnection.getConnection()) {
			    String query = "SELECT * FROM Employee WHERE employeeId = ?";
			    try (PreparedStatement statement = connection.prepareStatement(query)) {
			        statement.setInt(1, employeeId);
			        ResultSet resultSet = statement.executeQuery();
			        if (resultSet.next()) {
			            System.out.println("Employee Details:");
			            System.out.println("ID: " + resultSet.getInt("employeeId"));
			            System.out.println("First Name: " + resultSet.getString("firstName"));
			            System.out.println("Last Name: " + resultSet.getString("lastName"));
			            System.out.println("Contact Number: " + resultSet.getString("contactNumber"));
			            System.out.println("Email: " + resultSet.getString("email"));
			            System.out.println("Base Salary: " + resultSet.getDouble("baseSalary"));
			            System.out.println("Years of Experience: " + resultSet.getInt("yearsOfExperience"));
			            System.out.println("Role: " + resultSet.getString("role"));
			            System.out.println("Specialization: " + resultSet.getString("specialization"));
			            System.out.println("Department: " + resultSet.getString("department"));
			            System.out.println("Certification: " + resultSet.getString("certification"));
			            System.out.println("Gender: " + resultSet.getString("gender"));
			            System.out.println("Age: " + resultSet.getInt("age"));
			            System.out.println("Blood Group: " + resultSet.getString("bloodGroup"));
			        } else {
			            System.out.println("No employee found with the given ID.");
			        }
			    }
			} catch (SQLException e) {
			    e.printStackTrace();
			}
		}
    }
}
