import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class DutyRecordDAO {

    // Add a duty record
    public static void addDutyRecord(DutyRecord dutyRecord) throws SQLException {
        String query = "INSERT INTO dutyrecords (employeeId, departmentId, dutyDate, shift, inTime, outTime, status, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, dutyRecord.getEmployeeId());
            statement.setInt(2, dutyRecord.getDepartmentId());
            statement.setDate(3, dutyRecord.getDutyDate());
            statement.setString(4, dutyRecord.getShift());
            statement.setTime(5, dutyRecord.getInTime());
            statement.setTime(6, dutyRecord.getOutTime());
            statement.setString(7, dutyRecord.getStatus());
            statement.setString(8, dutyRecord.getNotes());
            statement.executeUpdate();
        }
    }

    // Get duty record by ID
    public static DutyRecord getDutyRecordById(int dutyId) throws SQLException {
        String query = "SELECT * FROM dutyrecords WHERE dutyId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, dutyId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new DutyRecord(
                        resultSet.getInt("dutyId"),
                        resultSet.getInt("employeeId"),
                        resultSet.getInt("departmentId"),
                        resultSet.getDate("dutyDate"),
                        resultSet.getString("shift"),
                        resultSet.getTime("inTime"),
                        resultSet.getTime("outTime"),
                        resultSet.getString("status"),
                        resultSet.getString("notes")
                );
            }
            return null;
        }
    }

    // Update a duty record
    public static void updateDutyRecord(DutyRecord dutyRecord) throws SQLException {
        String query = "UPDATE dutyrecords SET employeeId = ?, departmentId = ?, dutyDate = ?, shift = ?, inTime = ?, outTime = ?, status = ?, notes = ? WHERE dutyId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, dutyRecord.getEmployeeId());
            statement.setInt(2, dutyRecord.getDepartmentId());
            statement.setDate(3, dutyRecord.getDutyDate());
            statement.setString(4, dutyRecord.getShift());
            statement.setTime(5, dutyRecord.getInTime());
            statement.setTime(6, dutyRecord.getOutTime());
            statement.setString(7, dutyRecord.getStatus());
            statement.setString(8, dutyRecord.getNotes());
            statement.setInt(9, dutyRecord.getDutyId());
            statement.executeUpdate();
        }
    }

    // Delete a duty record
    public static void deleteDutyRecord(int dutyId) throws SQLException {
        String query = "DELETE FROM dutyrecords WHERE dutyId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, dutyId);
            statement.executeUpdate();
        }
    }

    // Get all duty records
    public static List<DutyRecord> getAllDutyRecords() throws SQLException {
        List<DutyRecord> dutyRecords = new ArrayList<>();
        String query = "SELECT * FROM dutyrecords";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                dutyRecords.add(new DutyRecord(
                        resultSet.getInt("dutyId"),
                        resultSet.getInt("employeeId"),
                        resultSet.getInt("departmentId"),
                        resultSet.getDate("dutyDate"),
                        resultSet.getString("shift"),
                        resultSet.getTime("inTime"),
                        resultSet.getTime("outTime"),
                        resultSet.getString("status"),
                        resultSet.getString("notes")
                ));
            }
        }
        return dutyRecords;
    }
}
