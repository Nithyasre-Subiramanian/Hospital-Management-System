import java.sql.Date;

public class MedicalRecord {
    private int recordId;
    private int patientId;
    private Date recordDate;
    private String diagnosis;
    private String treatment;
    private String notes;
    private int scanId;
    private int treatmentId;
    private int consultationId;
    private int bedId;
    private int prescriptionId;

    public MedicalRecord(int patientId, Date recordDate, String diagnosis, String treatment, String notes,
                         int scanId, int treatmentId, int consultationId, int bedId, int prescriptionId) {
        this.patientId = patientId;
        this.recordDate = recordDate;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.notes = notes;
        this.scanId = scanId;
        this.treatmentId = treatmentId;
        this.consultationId = consultationId;
        this.bedId = bedId;
        this.prescriptionId = prescriptionId;
    }

	

    public MedicalRecord(int consultationId, int bedId, int scanId, int treatmentId, int prescriptionId) {
        this.consultationId = consultationId;
        this.bedId = bedId;
        this.scanId = scanId;
        this.treatmentId = treatmentId;
        this.prescriptionId = prescriptionId;
    }




	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getScanId() {
		return scanId;
	}

	public void setScanId(int scanId) {
		this.scanId = scanId;
	}

	public int getTreatmentId() {
		return treatmentId;
	}

	public void setTreatmentId(int treatmentId) {
		this.treatmentId = treatmentId;
	}

	public int getConsultationId() {
		return consultationId;
	}

	public void setConsultationId(int consultationId) {
		this.consultationId = consultationId;
	}

	public int getBedId() {
		return bedId;
	}

	public void setBedId(int bedId) {
		this.bedId = bedId;
	}

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

    
    
}
