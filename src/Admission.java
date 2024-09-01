public class Admission {
    private int admissionId;
    private int bedId;
    private int patientId;
    private java.sql.Date admissionDate;
    private java.sql.Date dischargeDate;

    // Full constructor
    public Admission(int admissionId, int bedId, int patientId, java.sql.Date admissionDate, java.sql.Date dischargeDate) {
        this.admissionId = admissionId;
        this.bedId = bedId;
        this.patientId = patientId;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
    }

    // Getters and Setters
    public int getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(int admissionId) {
        this.admissionId = admissionId;
    }

    public int getBedId() {
        return bedId;
    }

    public void setBedId(int bedId) {
        this.bedId = bedId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public java.sql.Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(java.sql.Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public java.sql.Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(java.sql.Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }
}
