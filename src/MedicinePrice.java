//import java.math.BigDecimal;

import java.math.BigDecimal;

public class MedicinePrice {
    private int medicineId;
    private String medicineName;
    private BigDecimal price;

    
    public MedicinePrice(int medicineId, String medicineName, BigDecimal price) {
		
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.price = price;
	}

	public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
