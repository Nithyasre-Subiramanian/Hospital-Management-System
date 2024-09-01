public class Pharmacist {
    private int pharmacistId;
    private int employeeId;
    private String certification;
    private int departmentId;

   
    public Pharmacist() {}

	public Pharmacist(int pharmacistId, int employeeId, String certification, int departmentId) {
		super();
		this.pharmacistId = pharmacistId;
		this.employeeId = employeeId;
		this.certification = certification;
		this.departmentId = departmentId;
	}

	public int getPharmacistId() {
        return pharmacistId;
    }

    public void setPharmacistId(int pharmacistId) {
        this.pharmacistId = pharmacistId;
    }

    public int getEmployeeId() {
    	
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
