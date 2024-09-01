import java.math.BigDecimal;

public class BedCharge {
    private int bedId;
    private String bedType;
    private BigDecimal dailyRate;
    
    

    public BedCharge(int bedId, String bedType, BigDecimal dailyRate) {
		super();
		this.bedId = bedId;
		this.bedType = bedType;
		this.dailyRate = dailyRate;
	}

	public int getBedId() {
        return bedId;
    }

    public void setBedId(int bedId) {
        this.bedId = bedId;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }
}
