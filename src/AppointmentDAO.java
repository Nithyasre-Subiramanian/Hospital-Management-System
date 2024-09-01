import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

   
    public static void addAppointment(Appointment appointment) throws SQLException {
        String query = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, status, reason) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, appointment.getPatientId());
            statement.setInt(2, appointment.getDoctorId());
            statement.setDate(3, appointment.getAppointmentDate());
            statement.setTime(4, appointment.getAppointmentTime()); 
            statement.setString(5, appointment.getStatus());
            statement.setString(6, appointment.getReason());
            statement.executeUpdate();
        }
    }

    
    public static Appointment getAppointmentById(int appointmentId) throws SQLException {
        String query = "SELECT * FROM appointments WHERE appointment_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, appointmentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Appointment(
                        resultSet.getInt("appointment_id"),
                        resultSet.getInt("patient_id"),
                        resultSet.getInt("doctor_id"),
                        resultSet.getDate("appointment_date"),
                        resultSet.getTime("appointment_time"), 
                        resultSet.getString("status"),
                        resultSet.getString("reason")
                );
            }
        }
        return null;
    }

    
    public static void updateAppointment(Appointment appointment) throws SQLException {
        String query = "UPDATE appointments SET patient_id = ?, doctor_id = ?, appointment_date = ?, appointment_time = ?, status = ?, reason = ? WHERE appointment_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, appointment.getPatientId());
            statement.setInt(2, appointment.getDoctorId());
            statement.setDate(3, appointment.getAppointmentDate());
            statement.setTime(4, appointment.getAppointmentTime()); 
            statement.setString(5, appointment.getStatus());
            statement.setString(6, appointment.getReason());
            statement.setInt(7, appointment.getAppointmentId());
            statement.executeUpdate();
        }
    }

   
    public static void deleteAppointment(int appointmentId) throws SQLException {
        String query = "DELETE FROM appointments WHERE appointment_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, appointmentId);
            statement.executeUpdate();
        }
    }

    public static List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                appointments.add(new Appointment(
                        resultSet.getInt("appointment_id"),
                        resultSet.getInt("patient_id"),
                        resultSet.getInt("doctor_id"),
                        resultSet.getDate("appointment_date"),
                        resultSet.getTime("appointment_time"), 
                        resultSet.getString("status"),
                        resultSet.getString("reason")
                ));
            }
        }
        return appointments;
    }
    
    public static Admission getAdmissionByBedId(int bedId) throws SQLException {
        String query = "SELECT * FROM admissions WHERE bed_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bedId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Admission(
                        resultSet.getInt("admission_id"),
                        resultSet.getInt("bed_id"),
                        resultSet.getInt("patient_id"),
                        resultSet.getDate("admission_date"), null
                      
                );
            }
            return null;
        }
    }
}
