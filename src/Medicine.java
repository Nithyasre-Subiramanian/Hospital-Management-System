public class Medicine {
    private int medicineId;
    private String medicineName;
    private double price;

    public Medicine(int medicineId, String medicineName, double price) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.price = price;
    }

    public Medicine(String medicineName, double price) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

   
    public String toString() {
        return "Medicine [ID=" + medicineId + ", Name=" + medicineName + ", Price=" + price + "]";
    }
}
