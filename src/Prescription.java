import java.sql.Date;

public class Prescription {
    private int prescriptionId;
    private int patientId;
    private int doctorId;
    private Date prescriptionDate;
    private String instructions;

    
    public Prescription(int prescriptionId, int patientId, int doctorId, Date prescriptionDate, String instructions) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.prescriptionDate = prescriptionDate;
        this.instructions = instructions;
    }

   
    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
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

    public Date getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Date prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }


    public String toString() {
        return "Prescription ID: " + prescriptionId + ", Patient ID: " + patientId + ", Doctor ID: " + doctorId
                + ", Prescription Date: " + prescriptionDate + ", Instructions: " + instructions;
    }
}
