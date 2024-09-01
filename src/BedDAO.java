import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BedDAO {
    public static void addBed() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add Bed:");
        System.out.print("Enter Department ID: ");
        int departmentId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Bed Number: ");
        String bedNumber = scanner.nextLine();

        System.out.print("Enter Bed Type (Standard/ICU/VIP): ");
        String bedType = scanner.nextLine();

        System.out.print("Enter Status (Available/Oc cupied/Under Maintenance): ");
        String status = scanner.nextLine();

        try {
            BedDAO.addBed(new Bed(departmentId, bedNumber, bedType, status));
            System.out.println("Bed added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add bed.");
        }
    }

    public static void viewBedDetails() {
        try {
            List<Bed> beds = BedDAO.getAllBeds();
            System.out.println("Bed Details:");
            for (Bed bed : beds) {
                System.out.printf("ID: %d, Department ID: %d, Bed Number: %s, Bed Type: %s, Status: %s%n",
                        bed.getBedId(), bed.getDepartmentId(), bed.getBedNumber(), bed.getBedType(), bed.getStatus());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to retrieve bed details.");
        }
    }

    public static void countAvailableBeds() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Department ID: ");
        int departmentId = scanner.nextInt();
        scanner.nextLine();

        try {
            int count = BedDAO.countAvailableBeds(departmentId);
            System.out.println("Number of available beds in department " + departmentId + ": " + count);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to count available beds.");
        }
    }

    public static void addBed(Bed bed) throws SQLException {
        String query = "INSERT INTO beds (department_id, bed_number, bed_type, status) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bed.getDepartmentId());
            statement.setString(2, bed.getBedNumber());
            statement.setString(3, bed.getBedType());
            statement.setString(4, bed.getStatus());
            statement.executeUpdate();
        }
    }

    public static List<Bed> getAllBeds() throws SQLException {
        List<Bed> beds = new ArrayList<>();
        String query = "SELECT * FROM beds";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                beds.add(new Bed(
                        resultSet.getInt("bed_id"),
                        resultSet.getString("bed_number"),
                        resultSet.getString("bed_type"),
                        resultSet.getString("status")
                ));
            }
        }
        return beds;
    }

    public static int countAvailableBeds(int departmentId) throws SQLException {
        String query = "SELECT COUNT(*) FROM beds WHERE department_id = ? AND status = 'Available'";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, departmentId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        }
    }
}
