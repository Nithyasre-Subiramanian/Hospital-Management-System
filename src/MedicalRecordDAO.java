import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MedicalRecordDAO {

    
	public static void addMedicalRecord() {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("Add Medical Record:");
	        System.out.print("Enter Patient ID: ");
	        int patientId = scanner.nextInt();
	        scanner.nextLine(); 

	        System.out.print("Enter Record Date (YYYY-MM-DD): ");
	        String recordDateString = scanner.nextLine();
	        Date recordDate = Date.valueOf(recordDateString);

	        System.out.print("Enter Diagnosis: ");
	        String diagnosis = scanner.nextLine();

	        System.out.print("Enter Treatment: ");
	        String treatment = scanner.nextLine();

	        System.out.print("Enter Notes: ");
	        String notes = scanner.nextLine();

	        System.out.print("Enter Scan ID: ");
	        int scanId = scanner.nextInt();

	        System.out.print("Enter Treatment ID: ");
	        int treatmentId = scanner.nextInt();

	        System.out.print("Enter Consultation ID: ");
	        int consultationId = scanner.nextInt();

	        System.out.print("Enter Bed ID: ");
	        int bedId = scanner.nextInt();

	        System.out.print("Enter Prescription ID: ");
	        int prescriptionId = scanner.nextInt();

	        MedicalRecord record = new MedicalRecord(patientId, recordDate, diagnosis, treatment, notes,
	                                                scanId, treatmentId, consultationId, bedId, prescriptionId);
	        try {
	            MedicalRecordDAO.addMedicalRecord(record);
	            System.out.println("Medical Record added successfully.");
	        } catch (SQLException e) {
	            System.out.println("Error adding Medical Record: " + e.getMessage());
	        }
	    }

	  public static void viewMedicalRecords() {
	        try {
	            List<MedicalRecord> records = MedicalRecordDAO.getAllMedicalRecords();
	            for (MedicalRecord record : records) {
	                System.out.println(record);
	            }
	        } catch (SQLException e) {
	            System.out.println("Error retrieving Medical Records: " + e.getMessage());
	        }
	    }

	    public static void updateMedicalRecord() {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter Medical Record ID to update: ");
	        int recordId = scanner.nextInt();
	        scanner.nextLine(); 

	        try {
	            MedicalRecord existingRecord = MedicalRecordDAO.getMedicalRecordById(recordId);
	            if (existingRecord == null) {
	                System.out.println("Medical Record not found.");
	                return;
	            }

	            System.out.print("Enter new Patient ID (or leave blank to keep current): ");
	            String input = scanner.nextLine();
	            int patientId = input.isEmpty() ? existingRecord.getPatientId() : Integer.parseInt(input);

	            System.out.print("Enter new Record Date (YYYY-MM-DD) (or leave blank to keep current): ");
	            input = scanner.nextLine();
	            Date recordDate = input.isEmpty() ? existingRecord.getRecordDate() : Date.valueOf(input);

	            System.out.print("Enter new Diagnosis (or leave blank to keep current): ");
	            String diagnosis = scanner.nextLine();
	            diagnosis = diagnosis.isEmpty() ? existingRecord.getDiagnosis() : diagnosis;

	            System.out.print("Enter new Treatment (or leave blank to keep current): ");
	            String treatment = scanner.nextLine();
	            treatment = treatment.isEmpty() ? existingRecord.getTreatment() : treatment;

	            System.out.print("Enter new Notes (or leave blank to keep current): ");
	            String notes = scanner.nextLine();
	            notes = notes.isEmpty() ? existingRecord.getNotes() : notes;

	            System.out.print("Enter new Scan ID (or leave blank to keep current): ");
	            input = scanner.nextLine();
	            int scanId = input.isEmpty() ? existingRecord.getScanId() : Integer.parseInt(input);

	            System.out.print("Enter new Treatment ID (or leave blank to keep current): ");
	            input = scanner.nextLine();
	            int treatmentId = input.isEmpty() ? existingRecord.getTreatmentId() : Integer.parseInt(input);

	            System.out.print("Enter new Consultation ID (or leave blank to keep current): ");
	            input = scanner.nextLine();
	            int consultationId = input.isEmpty() ? existingRecord.getConsultationId() : Integer.parseInt(input);

	            System.out.print("Enter new Bed ID (or leave blank to keep current): ");
	            input = scanner.nextLine();
	            int bedId = input.isEmpty() ? existingRecord.getBedId() : Integer.parseInt(input);

	            System.out.print("Enter new Prescription ID (or leave blank to keep current): ");
	            input = scanner.nextLine();
	            int prescriptionId = input.isEmpty() ? existingRecord.getPrescriptionId() : Integer.parseInt(input);

	            MedicalRecord updatedRecord = new MedicalRecord(patientId, recordDate, diagnosis, treatment, notes,
	                                                           scanId, treatmentId, consultationId, bedId, prescriptionId);
	            updatedRecord.setRecordId(recordId);
	            MedicalRecordDAO.updateMedicalRecord(updatedRecord);
	            System.out.println("Medical Record updated successfully.");
	        } catch (SQLException e) {
	            System.out.println("Error updating Medical Record: " + e.getMessage());
	        }
	    }

	    public static void deleteMedicalRecord() {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter Medical Record ID to delete: ");
	        int recordId = scanner.nextInt();

	        try {
	            MedicalRecordDAO.deleteMedicalRecord(recordId);
	            System.out.println("Medical Record deleted successfully.");
	        } catch (SQLException e) {
	            System.out.println("Error deleting Medical Record: " + e.getMessage());
	        }
	    }
    public static void addMedicalRecord(MedicalRecord record) throws SQLException {
        String query = "INSERT INTO medical_records (patient_id, record_date, diagnosis, treatment, notes, " +
                       "scan_id, treatment_id, consultation_id, bed_id, prescription_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, record.getPatientId());
            statement.setDate(2, record.getRecordDate());
            statement.setString(3, record.getDiagnosis());
            statement.setString(4, record.getTreatment());
            statement.setString(5, record.getNotes());
            statement.setInt(6, record.getScanId());
            statement.setInt(7, record.getTreatmentId());
            statement.setInt(8, record.getConsultationId());
            statement.setInt(9, record.getBedId());
            statement.setInt(10, record.getPrescriptionId());
            statement.executeUpdate();
        }
    }

    // Get medical record by ID
    public static MedicalRecord getMedicalRecordById(int recordId) throws SQLException {
        String query = "SELECT * FROM medical_records WHERE record_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, recordId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new MedicalRecord(
                        resultSet.getInt("patient_id"),
                        resultSet.getDate("record_date"),
                        resultSet.getString("diagnosis"),
                        resultSet.getString("treatment"),
                        resultSet.getString("notes"),
                        resultSet.getInt("scan_id"),
                        resultSet.getInt("treatment_id"),
                        resultSet.getInt("consultation_id"),
                        resultSet.getInt("bed_id"),
                        resultSet.getInt("prescription_id")
                );
            }
            return null;
        }
    }

    // Update a medical record
    public static void updateMedicalRecord(MedicalRecord record) throws SQLException {
        String query = "UPDATE medical_records SET patient_id = ?, record_date = ?, diagnosis = ?, treatment = ?, " +
                       "notes = ?, scan_id = ?, treatment_id = ?, consultation_id = ?, bed_id = ?, prescription_id = ? " +
                       "WHERE record_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, record.getPatientId());
            statement.setDate(2, record.getRecordDate());
            statement.setString(3, record.getDiagnosis());
            statement.setString(4, record.getTreatment());
            statement.setString(5, record.getNotes());
            statement.setInt(6, record.getScanId());
            statement.setInt(7, record.getTreatmentId());
            statement.setInt(8, record.getConsultationId());
            statement.setInt(9, record.getBedId());
            statement.setInt(10, record.getPrescriptionId());
            statement.setInt(11, record.getRecordId());
            statement.executeUpdate();
        }
    }

    // Delete a medical record
    public static void deleteMedicalRecord(int recordId) throws SQLException {
        String query = "DELETE FROM medical_records WHERE record_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, recordId);
            statement.executeUpdate();
        }
    }

    // Get all medical records
    public static List<MedicalRecord> getAllMedicalRecords() throws SQLException {
        List<MedicalRecord> records = new ArrayList<>();
        String query = "SELECT * FROM medical_records";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                records.add(new MedicalRecord(
                        resultSet.getInt("patient_id"),
                        resultSet.getDate("record_date"),
                        resultSet.getString("diagnosis"),
                        resultSet.getString("treatment"),
                        resultSet.getString("notes"),
                        resultSet.getInt("scan_id"),
                        resultSet.getInt("treatment_id"),
                        resultSet.getInt("consultation_id"),
                        resultSet.getInt("bed_id"),
                        resultSet.getInt("prescription_id")
                ));
            }
        }
        return records;
    }
}
