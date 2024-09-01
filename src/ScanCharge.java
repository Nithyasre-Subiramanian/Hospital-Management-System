//import java.math.BigDecimal;

import java.math.BigDecimal;

public class ScanCharge {
    private int scanId;
    private String scanType;
    private BigDecimal charge;
    public ScanCharge() {}

    public ScanCharge(int scanId, String scanType, BigDecimal charge) {
		super();
		this.scanId = scanId;
		this.scanType = scanType;
		this.charge = charge;
	}

	public int getScanId() {
        return scanId;
    }

    public void setScanId(int scanId) {
        this.scanId = scanId;
    }

    public String getScanType() {
        return scanType;
    }

    public void setScanType(String scanType) {
        this.scanType = scanType;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }
}
