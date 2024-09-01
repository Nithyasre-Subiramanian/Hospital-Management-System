import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdmissionDAO {
   
    public AdmissionDAO(Connection connection) {
    }


    public static  void addAdmission(Admission admission) throws SQLException {
        String sql = "INSERT INTO admissions (bed_id, patient_id, admission_date, discharge_date) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
        		PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, admission.getBedId());
            statement.setInt(2, admission.getPatientId());
            statement.setDate(3, admission.getAdmissionDate());
            statement.setDate(4, admission.getDischargeDate());
            statement.executeUpdate();
        }
    }

   
    public Admission getAdmissionById(int admissionId) throws SQLException {
        String sql = "SELECT * FROM admissions WHERE admission_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
        		PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, admissionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Admission(
                        resultSet.getInt("admission_id"),
                        resultSet.getInt("bed_id"),
                        resultSet.getInt("patient_id"),
                        resultSet.getDate("admission_date"),
                        resultSet.getDate("discharge_date")
                    );
                }
            }
        }
        return null;
    }

  
    public static List<Admission> getAllAdmissions() throws SQLException {
        List<Admission> admissions = new ArrayList<>();
        String sql = "SELECT * FROM admissions";
        try (Connection connection = DatabaseConnection.getConnection();
        		Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                admissions.add(new Admission(
                    resultSet.getInt("admission_id"),
                    resultSet.getInt("bed_id"),
                    resultSet.getInt("patient_id"),
                    resultSet.getDate("admission_date"),
                    resultSet.getDate("discharge_date")
                ));
            }
        }
        return admissions;
    }

  
    public static void updateAdmission(Admission admission) throws SQLException {
        String sql = "UPDATE admissions SET bed_id = ?, patient_id = ?, admission_date = ?, discharge_date = ? WHERE admission_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
        		PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, admission.getBedId());
            statement.setInt(2, admission.getPatientId());
            statement.setDate(3, admission.getAdmissionDate());
            statement.setDate(4, admission.getDischargeDate());
            statement.setInt(5, admission.getAdmissionId());
            statement.executeUpdate();
        }
    }

    
    public static void deleteAdmission(int admissionId) throws SQLException {
        String sql = "DELETE FROM admissions WHERE admission_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
        		PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, admissionId);
            statement.executeUpdate();
        }
    }
}
