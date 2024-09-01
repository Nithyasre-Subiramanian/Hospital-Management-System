public class Doctor {
    private int doctorId;
    private int employeeId;
    private String specialization;
    private int departmentId;

   
    public Doctor(int doctorId, int employeeId, String specialization, int departmentId) {
		super();
		this.doctorId = doctorId;
		this.employeeId = employeeId;
		this.specialization = specialization;
		this.departmentId = departmentId;
	}

	public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
