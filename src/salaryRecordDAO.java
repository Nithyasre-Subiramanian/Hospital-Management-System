import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class salaryRecordDAO {
    public salaryRecordDAO(Connection connection) {
    }


    public static void addSalaryRecord(SalaryRecord salaryRecord) throws SQLException {
        String sql = "INSERT INTO salaryrecords (employeeId, baseSalary, specializationBonus, experienceBonus, " +
                     "totalSalary, salaryMonth, salaryDate, status, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
        		PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, salaryRecord.getEmployeeId());
            statement.setDouble(2, salaryRecord.getBaseSalary());
            statement.setDouble(3, salaryRecord.getSpecializationBonus());
            statement.setDouble(4, salaryRecord.getExperienceBonus());
            statement.setDouble(5, salaryRecord.getTotalSalary());
            statement.setString(6, salaryRecord.getSalaryMonth());
            statement.setDate(7, salaryRecord.getSalaryDate());
            statement.setString(8, salaryRecord.getStatus());
            statement.setString(9, salaryRecord.getNotes());
            statement.executeUpdate();
        }
    }

    
    public static SalaryRecord getSalaryRecordById(int salaryId) throws SQLException {
        String sql = "SELECT * FROM salaryrecords WHERE salaryId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
        		PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, salaryId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new SalaryRecord(
                        resultSet.getInt("salaryId"),
                        resultSet.getInt("employeeId"),
                        resultSet.getDouble("baseSalary"),
                        resultSet.getDouble("specializationBonus"),
                        resultSet.getDouble("experienceBonus"),
                        resultSet.getDouble("totalSalary"),
                        resultSet.getString("salaryMonth"),
                        resultSet.getDate("salaryDate"),
                        resultSet.getString("status"),
                        resultSet.getString("notes")
                    );
                }
            }
        }
        return null;
    }

  
    public static List<SalaryRecord> getAllSalaryRecords() throws SQLException {
        List<SalaryRecord> salaryRecords = new ArrayList<>();
        String sql = "SELECT * FROM salaryrecords";
        try (Connection connection = DatabaseConnection.getConnection();
        		Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                salaryRecords.add(new SalaryRecord(
                    resultSet.getInt("salaryId"),
                    resultSet.getInt("employeeId"),
                    resultSet.getDouble("baseSalary"),
                    resultSet.getDouble("specializationBonus"),
                    resultSet.getDouble("experienceBonus"),
                    resultSet.getDouble("totalSalary"),
                    resultSet.getString("salaryMonth"),
                    resultSet.getDate("salaryDate"),
                    resultSet.getString("status"),
                    resultSet.getString("notes")
                ));
            }
        }
        return salaryRecords;
    }

  
    public static void updateSalaryRecord(SalaryRecord salaryRecord) throws SQLException {
        String sql = "UPDATE salaryrecords SET employeeId = ?, baseSalary = ?, specializationBonus = ?, " +
                     "experienceBonus = ?, totalSalary = ?, salaryMonth = ?, salaryDate = ?, status = ?, notes = ? " +
                     "WHERE salaryId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
        		PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, salaryRecord.getEmployeeId());
            statement.setDouble(2, salaryRecord.getBaseSalary());
            statement.setDouble(3, salaryRecord.getSpecializationBonus());
            statement.setDouble(4, salaryRecord.getExperienceBonus());
            statement.setDouble(5, salaryRecord.getTotalSalary());
            statement.setString(6, salaryRecord.getSalaryMonth());
            statement.setDate(7, salaryRecord.getSalaryDate());
            statement.setString(8, salaryRecord.getStatus());
            statement.setString(9, salaryRecord.getNotes());
            statement.setInt(10, salaryRecord.getSalaryId());
            statement.executeUpdate();
        }
    }

  
    public static void deleteSalaryRecord(int salaryId) throws SQLException {
        String sql = "DELETE FROM salaryrecords WHERE salaryId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
        		PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, salaryId);
            statement.executeUpdate();
        }
    }
}
