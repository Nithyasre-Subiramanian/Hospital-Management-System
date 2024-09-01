import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorScheduleDao {

    
    public void insertDoctorSchedule(DoctorSchedule schedule) {
        String query = "INSERT INTO doctor_schedule (session, slot_time, doctor_id, available) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, schedule.getSession());
            statement.setString(2, schedule.getSlotTime());
            statement.setInt(3, schedule.getDoctorId());
            statement.setBoolean(4, schedule.isAvailable());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
    public void updateDoctorSchedule(DoctorSchedule schedule) {
        String query = "UPDATE doctor_schedule SET session = ?, slot_time = ?, doctor_id = ?, available = ? WHERE slot_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, schedule.getSession());
            statement.setString(2, schedule.getSlotTime());
            statement.setInt(3, schedule.getDoctorId());
            statement.setBoolean(4, schedule.isAvailable());
            statement.setInt(5, schedule.getSlotId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void deleteDoctorSchedule(int slotId) {
        String query = "DELETE FROM doctor_schedule WHERE slot_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, slotId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
    public DoctorSchedule getDoctorScheduleById(int slotId) {
        String query = "SELECT slot_id, session, slot_time, doctor_id, available FROM doctor_schedule WHERE slot_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, slotId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("slot_id");
                String session = resultSet.getString("session");
                String slotTime = resultSet.getString("slot_time");
                int doctorId = resultSet.getInt("doctor_id");
                boolean available = resultSet.getBoolean("available");

                return new DoctorSchedule(id, session, slotTime, doctorId, available);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<DoctorSchedule> getAllDoctorSchedules() {
        List<DoctorSchedule> schedules = new ArrayList<>();
        String query = "SELECT slot_id, session, slot_time, doctor_id, available FROM doctor_schedule";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int slotId = resultSet.getInt("slot_id");
                String session = resultSet.getString("session");
                String slotTime = resultSet.getString("slot_time");
                int doctorId = resultSet.getInt("doctor_id");
                boolean available = resultSet.getBoolean("available");

                DoctorSchedule schedule = new DoctorSchedule(slotId, session, slotTime, doctorId, available);
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }
}
