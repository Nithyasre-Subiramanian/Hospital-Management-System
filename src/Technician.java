public class Technician {
    private int technicianId;
    private int employeeId;
    private String certification;
    private int departmentId;
    public Technician() {}
   
    public Technician(int technicianId, int employeeId, String certification, int departmentId) {
		super();
		this.technicianId = technicianId;
		this.employeeId = employeeId;
		this.certification = certification;
		this.departmentId = departmentId;
	}

	public int getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(int technicianId) {
        this.technicianId = technicianId;
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
