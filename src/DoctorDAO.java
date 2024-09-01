import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    private Connection connection;

    public DoctorDAO(Connection connection) {
        this.connection = connection;
    }

    public void addDoctor(Doctor doctor) throws SQLException {
        String sql = "INSERT INTO Doctor (employeeId, specialization, departmentId) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, doctor.getEmployeeId());
            statement.setString(2, doctor.getSpecialization());
            statement.setInt(3, doctor.getDepartmentId());
            statement.executeUpdate();
        }
    }

    public Doctor getDoctorById(int doctorId) throws SQLException {
        String sql = "SELECT * FROM Doctor WHERE doctorId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, doctorId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Doctor(
                    resultSet.getInt("doctorId"),
                    resultSet.getInt("employeeId"),
                    resultSet.getString("specialization"),
                    resultSet.getInt("departmentId")
                );
            }
        }
        return null;
    }

    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM Doctor";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                doctors.add(new Doctor(
                    resultSet.getInt("doctorId"),
                    resultSet.getInt("employeeId"),
                    resultSet.getString("specialization"),
                    resultSet.getInt("departmentId")
                ));
            }
        }
        return doctors;
    }
}
