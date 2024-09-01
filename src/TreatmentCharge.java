import java.math.BigDecimal;

public class TreatmentCharge {
    private int treatmentId;
    private String treatmentName;
    private BigDecimal charge;
    
    public TreatmentCharge() {}
    public TreatmentCharge(int treatmentId, String treatmentName, BigDecimal charge) {
		super();
		this.treatmentId = treatmentId;
		this.treatmentName = treatmentName;
		this.charge = charge;
	}

	public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }
}
