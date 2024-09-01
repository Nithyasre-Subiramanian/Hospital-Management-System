import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserMenu {

    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DatabaseConnection.getConnection()) {
            
            List<Doctor> doctors = getDoctors(connection);
            System.out.println("Available Doctors:");
            for (Doctor doctor : doctors) {
                System.out.printf("ID: %d, Specialization: %s, Department ID: %d%n",
                        doctor.getDoctorId(), doctor.getSpecialization(), doctor.getDepartmentId());
            }

            // Select doctor
            System.out.print("Enter doctor ID to select: ");
            int selectedDoctorId = scanner.nextInt();

            //  display available slots for selected doctor
            List<DoctorSchedule> availableSlots = getAvailableSlots(connection, selectedDoctorId);
            if (availableSlots.isEmpty()) {
                System.out.println("No available slots for the selected doctor. Please try another doctor.");
                return;
            }

            System.out.println("Available Slots:");
            for (DoctorSchedule schedule : availableSlots) {
                System.out.printf("Slot ID: %d, Session: %s, Time: %s%n",
                        schedule.getSlotId(), schedule.getSession(), schedule.getSlotTime());
            }

            // Select slot
            System.out.print("Select slot ID: ");
            int selectedSlotId = scanner.nextInt();

            // Get patient details
            int patientId = getPatientDetails(connection);

            // Insert appointment
            insertAppointment(connection, patientId, selectedDoctorId, selectedSlotId);

            System.out.println("Appointment scheduled successfully. We will take care of your health.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<Doctor> getDoctors(Connection connection) throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT doctorId, employeeId, specialization, departmentId FROM Doctor";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int doctorId = resultSet.getInt("doctorId");
                int employeeId = resultSet.getInt("employeeId");
                String specialization = resultSet.getString("specialization");
                int departmentId = resultSet.getInt("departmentId");

                Doctor doctor = new Doctor(doctorId, employeeId, specialization, departmentId);
                doctors.add(doctor);
            }
        }
        return doctors;
    }

    private static List<DoctorSchedule> getAvailableSlots(Connection connection, int doctorId) throws SQLException {
        List<DoctorSchedule> schedules = new ArrayList<>();
        String query = "SELECT slot_id, session, slot_time, available FROM doctor_schedule WHERE doctor_id = ? AND available = TRUE";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, doctorId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int slotId = resultSet.getInt("slot_id");
                String session = resultSet.getString("session");
                String slotTime = resultSet.getString("slot_time");
                boolean available = resultSet.getBoolean("available");

                DoctorSchedule schedule = new DoctorSchedule(slotId, session, slotTime, doctorId, available);
                schedules.add(schedule);
            }
        }
        return schedules;
    }

    private static int getPatientDetails(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        // Collect patient details
        System.out.print("Enter patient's first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter patient's last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter patient's date of birth (YYYY-MM-DD): ");
        String dateOfBirth = scanner.nextLine();

        System.out.print("Enter patient's gender (Male/Female/Other): ");
        String gender = scanner.nextLine();

        System.out.print("Enter patient's contact number: ");
        String contactNumber = scanner.nextLine();

        System.out.print("Enter patient's email: ");
        String email = scanner.nextLine();

        System.out.print("Enter patient's address: ");
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

        // Insert patient record
        String query = "INSERT INTO patient (firstName, lastName, dateOfBirth, gender, contactNumber, email, address, " +
                       "emergencyContactName, emergencyContactNumber, insuranceNumber, insuranceProvider, bloodGroup) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setDate(3, java.sql.Date.valueOf(dateOfBirth));
            statement.setString(4, gender);
            statement.setString(5, contactNumber);
            statement.setString(6, email);
            statement.setString(7, address);
            statement.setString(8, emergencyContactName);
            statement.setString(9, emergencyContactNumber);
            statement.setString(10, insuranceNumber);
            statement.setString(11, insuranceProvider);
            statement.setString(12, bloodGroup);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Inserting patient failed, no rows affected.");
            }

           
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Inserting patient failed, no ID obtained.");
                }
            }
        }
    }


    private static void insertAppointment(Connection connection, int patientId, int doctorId, int slotId) throws SQLException {
        String query = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, status) VALUES (?, ?, NOW(), ?, 'Scheduled')";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, patientId);
            statement.setInt(2, doctorId);
            statement.setInt(3, slotId);
            statement.executeUpdate();
        }
    }
}
