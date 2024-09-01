import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationDAO {

   
    public static void addOperation(Operation operation) throws SQLException {
        String query = "INSERT INTO operation (patient_id, doctor_id, nurse_id, operation_date, operation_time, operation_type, operation_room, duration, status, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, operation.getPatientId());
            statement.setInt(2, operation.getDoctorId());
            statement.setInt(3, operation.getNurseId());
            statement.setDate(4, operation.getOperationDate());
            statement.setTime(5, operation.getOperationTime());
            statement.setString(6, operation.getOperationType());
            statement.setString(7, operation.getOperationRoom());
            statement.setTime(8, operation.getDuration());
            statement.setString(9, operation.getStatus());
            statement.setString(10, operation.getNotes());
            statement.executeUpdate();
        }
    }

    
    public static Operation getOperationById(int operationId) throws SQLException {
        String query = "SELECT * FROM operation WHERE operationId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, operationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Operation(
                        resultSet.getInt("operationId"),
                        resultSet.getInt("patientId"),
                        resultSet.getInt("doctorId"),
                        resultSet.getInt("nurseId"),
                        resultSet.getDate("operationDate"),
                        resultSet.getTime("operationTime"),
                        resultSet.getString("operationType"),
                        resultSet.getString("operationRoom"),
                        resultSet.getTime("duration"),
                        resultSet.getString("status"),
                        resultSet.getString("notes")
                );
            }
            return null;
        }
    }

  
    public static void updateOperation(Operation operation) throws SQLException {
        String query = "UPDATE operation SET patient_id = ?, doctor_id = ?, nurse_id = ?, operation_date = ?, operation_time = ?, operation_type = ?, operation_room = ?, duration = ?, status = ?, notes = ? WHERE operationId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, operation.getPatientId());
            statement.setInt(2, operation.getDoctorId());
            statement.setInt(3, operation.getNurseId());
            statement.setDate(4, operation.getOperationDate());
            statement.setTime(5, operation.getOperationTime());
            statement.setString(6, operation.getOperationType());
            statement.setString(7, operation.getOperationRoom());
            statement.setTime(8, operation.getDuration());
            statement.setString(9, operation.getStatus());
            statement.setString(10, operation.getNotes());
            statement.setInt(11, operation.getOperationId());
            statement.executeUpdate();
        }
    }

    
    public static void deleteOperation(int operationId) throws SQLException {
        String query = "DELETE FROM operation WHERE operationId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, operationId);
            statement.executeUpdate();
        }
    }


    public static List<Operation> getAllOperations() throws SQLException {
        List<Operation> operations = new ArrayList<>();
        String query = "SELECT * FROM operation";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                operations.add(new Operation(
                        resultSet.getInt("operationId"),
                        resultSet.getInt("patientId"),
                        resultSet.getInt("doctorId"),
                        resultSet.getInt("nurseId"),
                        resultSet.getDate("operationDate"),
                        resultSet.getTime("operationTime"),
                        resultSet.getString("operationType"),
                        resultSet.getString("operationRoom"),
                        resultSet.getTime("duration"),
                        resultSet.getString("status"),
                        resultSet.getString("notes")
                ));
            }
        }
        return operations;
    }
}
