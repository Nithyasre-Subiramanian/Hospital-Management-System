
import java.util.Date;

public class Billing {
    private int billId;
    private int patientId;
    private Date billingDate;
    private double amount;
    private String paymentStatus; 
    private String paymentMethod; 

   
    
    public Billing(int billId, int patientId, Date billingDate, double amount, String paymentStatus,
			String paymentMethod) {
		super();
		this.billId = billId;
		this.patientId = patientId;
		this.billingDate = billingDate;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
	}

    
	public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
