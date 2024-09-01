public class Administrator {
    private int administratorId;
    private int employeeId;
    private int departmentId;

    
    public Administrator(int administratorId, int employeeId, int departmentId) {
		super();
		this.administratorId = administratorId;
		this.employeeId = employeeId;
		this.departmentId = departmentId;
	}

	public int getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(int administratorId) {
        this.administratorId = administratorId;
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
