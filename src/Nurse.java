public class Nurse {
    private int nurseId;
    private int employeeId;
    private int departmentId;

    public Nurse() {};
    
    public Nurse(int nurseId, int employeeId, int departmentId) {
		super();
		this.nurseId = nurseId;
		this.employeeId = employeeId;
		this.departmentId = departmentId;
	}

	public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
