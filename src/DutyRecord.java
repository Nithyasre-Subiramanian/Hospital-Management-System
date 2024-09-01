import java.sql.Date;
import java.sql.Time;

public class DutyRecord {
    private int dutyId;
    private int employeeId;
    private int departmentId;
    private Date dutyDate;
    private String shift; 
    private Time inTime;
    private Time outTime;
    private String status; 
    private String notes;

    public DutyRecord(int dutyId, int employeeId, int departmentId, Date dutyDate, String shift,
                      Time inTime, Time outTime, String status, String notes) {
        this.dutyId = dutyId;
        this.employeeId = employeeId;
        this.departmentId = departmentId;
        this.dutyDate = dutyDate;
        this.shift = shift;
        this.inTime = inTime;
        this.outTime = outTime;
        this.status = status;
        this.notes = notes;
    }

    public DutyRecord(int employeeId, int departmentId, Date dutyDate, String shift, Time inTime,
                      Time outTime, String status, String notes) {
        this.employeeId = employeeId;
        this.departmentId = departmentId;
        this.dutyDate = dutyDate;
        this.shift = shift;
        this.inTime = inTime;
        this.outTime = outTime;
        this.status = status;
        this.notes = notes;
    }

    // Getters and setters
    public int getDutyId() {
        return dutyId;
    }

    public void setDutyId(int dutyId) {
        this.dutyId = dutyId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Date getDutyDate() {
        return dutyDate;
    }

    public void setDutyDate(Date dutyDate) {
        this.dutyDate = dutyDate;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Time getInTime() {
        return inTime;
    }

    public void setInTime(Time inTime) {
        this.inTime = inTime;
    }

    public Time getOutTime() {
        return outTime;
    }

    public void setOutTime(Time outTime) {
        this.outTime = outTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

   
    public String toString() {
        return "DutyRecord [ID=" + dutyId + ", EmployeeID=" + employeeId + ", DepartmentID=" + departmentId +
               ", Date=" + dutyDate + ", Shift=" + shift + ", InTime=" + inTime + ", OutTime=" + outTime +
               ", Status=" + status + ", Notes=" + notes + "]";
    }
}
