import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {
    public PrescriptionDAO(Connection connection) {
    }

    
    public static void addPrescription(Prescription prescription) throws SQLException {
        String sql = "INSERT INTO prescriptions (patient_id, doctor_id, prescription_date, instructions) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
        		PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, prescription.getPatientId());
            statement.setInt(2, prescription.getDoctorId());
            statement.setDate(3, prescription.getPrescriptionDate());
            statement.setString(4, prescription.getInstructions());
            statement.executeUpdate();
        }
    }

    public Prescription getPrescriptionById(int prescriptionId) throws SQLException {
        String sql = "SELECT * FROM prescriptions WHERE prescription_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
        		PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, prescriptionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Prescription(
                        resultSet.getInt("prescription_id"),
                        resultSet.getInt("patient_id"),
                        resultSet.getInt("doctor_id"),
                        resultSet.getDate("prescription_date"),
                        resultSet.getString("instructions")
                    );
                }
            }
        }
        return null;
    }

    
    public static List<Prescription> getAllPrescriptions() throws SQLException {
        List<Prescription> prescriptions = new ArrayList<>();
        String sql = "SELECT * FROM prescriptions";
        try (Connection connection = DatabaseConnection.getConnection();
        		Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                prescriptions.add(new Prescription(
                    resultSet.getInt("prescription_id"),
                    resultSet.getInt("patient_id"),
                    resultSet.getInt("doctor_id"),
                    resultSet.getDate("prescription_date"),
                    resultSet.getString("instructions")
                ));
            }
        }
        return prescriptions;
    }

   
    public static void updatePrescription(Prescription prescription) throws SQLException {
        String sql = "UPDATE prescriptions SET patient_id = ?, doctor_id = ?, prescription_date = ?, instructions = ? WHERE prescription_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
        		PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, prescription.getPatientId());
            statement.setInt(2, prescription.getDoctorId());
            statement.setDate(3, prescription.getPrescriptionDate());
            statement.setString(4, prescription.getInstructions());
            statement.setInt(5, prescription.getPrescriptionId());
            statement.executeUpdate();
        }
    }

    public static void deletePrescription(int prescriptionId) throws SQLException {
        String sql = "DELETE FROM prescriptions WHERE prescription_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
        		PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, prescriptionId);
            statement.executeUpdate();
        }
    }
}
