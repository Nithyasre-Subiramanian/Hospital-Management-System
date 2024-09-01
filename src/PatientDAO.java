import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PatientDAO {

    public static void addPatient() {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter first name: ");
			String firstName = scanner.nextLine();
			System.out.print("Enter last name: ");
			String lastName = scanner.nextLine();
			System.out.print("Enter date of birth (YYYY-MM-DD): ");
			String dateOfBirth = scanner.nextLine();
			System.out.print("Enter gender (Male/Female/Other): ");
			String gender = scanner.nextLine();
			System.out.print("Enter contact number: ");
			String contactNumber = scanner.nextLine();
			System.out.print("Enter email: ");
			String email = scanner.nextLine();
			System.out.print("Enter address: ");
			String address = scanner.nextLine();
			System.out.print("Enter emergency contact name: ");
			String emergencyContactName = scanner.nextLine();
			System.out.print("Enter emergency contact number: ");
			String emergencyContactNumber = scanner.nextLine();
			System.out.print("Enter insurance number: ");
			String insuranceNumber = scanner.nextLine();
			System.out.print("Enter insurance provider: ");
			String insuranceProvider = scanner.nextLine();
			System.out.print("Enter blood group (A+/A-/B+/B-/AB+/AB-/O+/O-): ");
			String bloodGroup = scanner.nextLine();

      

			try (Connection connection = DatabaseConnection.getConnection()) {
			    String query = "INSERT INTO patient (firstName, lastName, dateOfBirth, gender, contactNumber, email, address, emergencyContactName, emergencyContactNumber, insuranceNumber, insuranceProvider, bloodGroup) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			    try (PreparedStatement statement = connection.prepareStatement(query)) {
			        statement.setString(1, firstName);
			        statement.setString(2, lastName);
			        statement.setString(3, dateOfBirth);
			        statement.setString(4, gender);
			        statement.setString(5, contactNumber);
			        statement.setString(6, email);
			        statement.setString(7, address);
			        statement.setString(8, emergencyContactName);
			        statement.setString(9, emergencyContactNumber);
			        statement.setString(10, insuranceNumber);
			        statement.setString(11, insuranceProvider);
			        statement.setString(12, bloodGroup);
			        statement.executeUpdate();
			        System.out.println("Patient added successfully.");
			    }
			} catch (SQLException e) {
			    e.printStackTrace();
			}
		}
    }

    public static void viewPatientDetails() {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter patient ID to view details: ");
			int patientId = scanner.nextInt();

			try (Connection connection = DatabaseConnection.getConnection()) {
			    String query = "SELECT * FROM  patient WHERE patientId = ?";
			    try (PreparedStatement statement = connection.prepareStatement(query)) {
			        statement.setInt(1, patientId);
			        ResultSet resultSet = statement.executeQuery();
			        if (resultSet.next()) {
			            System.out.println("Patient Details:");
			            System.out.println("ID: " + resultSet.getInt("patientId"));
			            System.out.println("First Name: " + resultSet.getString("firstName"));
			            System.out.println("Last Name: " + resultSet.getString("lastName"));
			            System.out.println("Date of Birth: " + resultSet.getDate("dateOfBirth"));
			            System.out.println("Gender: " + resultSet.getString("gender"));
			            System.out.println("Contact Number: " + resultSet.getString("contactNumber"));
			            System.out.println("Email: " + resultSet.getString("email"));
			            System.out.println("Address: " + resultSet.getString("address"));
			            System.out.println("Emergency Contact Name: " + resultSet.getString("emergencyContactName"));
			            System.out.println("Emergency Contact Number: " + resultSet.getString("emergencyContactNumber"));
			            System.out.println("Insurance Number: " + resultSet.getString("insuranceNumber"));
			            System.out.println("Insurance Provider: " + resultSet.getString("insuranceProvider"));
			            System.out.println("Blood Group: " + resultSet.getString("bloodGroup"));
			             } else {
			            System.out.println("No patient found with the given ID.");
			        }
			    }
			} catch (SQLException e) {
			    e.printStackTrace();
			}
		}
    }
}
