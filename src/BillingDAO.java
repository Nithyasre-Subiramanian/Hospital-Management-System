import java.sql.*;

public class BillingDAO {
    private Connection connection;

    public BillingDAO(Connection connection) {
        this.connection = connection;
    }

   
    public double calculateTotalAmount(int patientId, int consultationId, int bedId, int scanId, int treatmentId) throws SQLException {
        double totalAmount = 0;

        
        double medicineCost = getTotalMedicineCost(patientId);
        totalAmount += medicineCost;

        double bedCharges = getBedCharges(bedId, patientId);
        totalAmount += bedCharges;

        
        totalAmount += getConsultationCharge(consultationId);
         totalAmount += getScanCharge(scanId);
        totalAmount += getTreatmentCharge(treatmentId);

        return totalAmount;
    }

    
    private double getTotalMedicineCost(int prescriptionId) throws SQLException {
        double totalCost = 0;
        String sql = "SELECT m.price, pm.dosage, pm.duration FROM prescription_medicines pm JOIN medicine m ON pm.medicine_id = m.medicine_id WHERE pm.prescription_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, prescriptionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    totalCost += resultSet.getDouble("price");
                }
            }
        }
        return totalCost;
    }

   
    private double getBedCharges(int bed_id, int patientId) throws SQLException {
        double bedCharge = 0;
        String sql = "SELECT daily_rate FROM bed_charges WHERE bed_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bed_id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    bedCharge = resultSet.getDouble("daily_rate");
                }
            }
        

        
        String sql2 = "SELECT admission_date, discharge_date FROM admissions WHERE patient_id = ? AND bed_id = ?";
        try (PreparedStatement statement1 = connection.prepareStatement(sql2)) {
            statement1.setInt(1, patientId);
            statement1.setInt(2, bed_id);
            try (ResultSet resultSet = statement1.executeQuery()) {
                if (resultSet.next()) {
                    Date admissionDate = resultSet.getDate("admission_date");
                    Date dischargeDate = resultSet.getDate("discharge_date");
                    long durationInMillis = dischargeDate.getTime() - admissionDate.getTime();
                    long days = durationInMillis / (1000 * 60 * 60 * 24);
                    return bedCharge * days;
                }
            }
        }
        return 0;
    }
    
    private double getConsultationCharge(int consultationId) throws SQLException {
        double consultationCharge = 0.0;
        String query = "SELECT fee_amount FROM consultation_fees WHERE consultation_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, consultationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                consultationCharge = resultSet.getDouble("fee_amount");
            }
        }
        return consultationCharge;
    }

    private double getScanCharge(int scanId) throws SQLException {
        double scanCharge = 0.0;
        String query = "SELECT charge FROM scan_charges WHERE scan_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, scanId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                scanCharge = resultSet.getDouble("charge");
            }
        }
        return scanCharge;
    }

    private double getTreatmentCharge(int treatmentId) throws SQLException {
        double treatmentCharge = 0.0;
        String query = "SELECT charge FROM treatment_charges WHERE treatment_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, treatmentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                treatmentCharge = resultSet.getDouble("charge");
            }
        }
        return treatmentCharge;
    }
    public MedicalRecord getMedicalRecordByPrescriptionId(int prescriptionId) throws SQLException {
        String query = "SELECT record_id, consultation_id, bed_id, scan_id, treatment_id FROM medical_records WHERE prescription_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, prescriptionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int recordId = resultSet.getInt("record_id");
                    System.out.println("Retrieved Record ID: " + recordId);  // Debugging output
                    return new MedicalRecord(
                        resultSet.getInt("consultation_id"),  // Retrieve consultation_id
                        resultSet.getInt("bed_id"),           // Retrieve bed_id
                        resultSet.getInt("scan_id"),          // Retrieve scan_id
                        resultSet.getInt("treatment_id"),     // Retrieve treatment_id
                        prescriptionId                        // Use prescriptionId passed to the method
                    );
                } else {
                    System.out.println("No record found for Prescription ID: " + prescriptionId);
                }
            }
        }
        return null; 
    }



}
