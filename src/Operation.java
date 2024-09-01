import java.sql.Date;
import java.sql.Time;

public class Operation {
    private int operationId;
    private int patientId;
    private int doctorId;
    private int nurseId;
    private Date operationDate;
    private Time operationTime;
    private String operationType;
    private String operationRoom;
    private Time duration;
    private String status; 
    private String notes;

    public Operation(int operationId, int patientId, int doctorId, int nurseId, Date operationDate,
                     Time operationTime, String operationType, String operationRoom, Time duration,
                     String status, String notes) {
        this.operationId = operationId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.nurseId = nurseId;
        this.operationDate = operationDate;
        this.operationTime = operationTime;
        this.operationType = operationType;
        this.operationRoom = operationRoom;
        this.duration = duration;
        this.status = status;
        this.notes = notes;
    }

    public Operation(int patientId, int doctorId, int nurseId, Date operationDate, Time operationTime,
                     String operationType, String operationRoom, Time duration, String status, String notes) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.nurseId = nurseId;
        this.operationDate = operationDate;
        this.operationTime = operationTime;
        this.operationType = operationType;
        this.operationRoom = operationRoom;
        this.duration = duration;
        this.status = status;
        this.notes = notes;
    }

    // Getters and setters
    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public Time getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Time operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationRoom() {
        return operationRoom;
    }

    public void setOperationRoom(String operationRoom) {
        this.operationRoom = operationRoom;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
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
        return "Operation [ID=" + operationId + ", PatientID=" + patientId + ", DoctorID=" + doctorId +
               ", NurseID=" + nurseId + ", Date=" + operationDate + ", Time=" + operationTime +
               ", Type=" + operationType + ", Room=" + operationRoom + ", Duration=" + duration +
               ", Status=" + status + ", Notes=" + notes + "]";
    }
}
