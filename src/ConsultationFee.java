import java.math.BigDecimal;

public class ConsultationFee {
    private int consultationId;
    private String consultationType;
    private BigDecimal feeAmount;

   
    
    
    public ConsultationFee(int consultationId, String consultationType, BigDecimal bigDecimal) {
		super();
		this.consultationId = consultationId;
		this.consultationType = consultationType;
		this.feeAmount = bigDecimal;
	}

	public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }

    public String getConsultationType() {
        return consultationType;
    }

    public void setConsultationType(String consultationType) {
        this.consultationType = consultationType;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }
}
