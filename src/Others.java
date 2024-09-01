public class Others {
    private int otherId;
    private int employeeId;
    private int departmentId;
    private String roleDescription;

    public Others() {};
    public Others(int otherId, int employeeId, int departmentId, String roleDescription) {
		super();
		this.otherId = otherId;
		this.employeeId = employeeId;
		this.departmentId = departmentId;
		this.roleDescription = roleDescription;
	}

	public int getOtherId() {
        return otherId;
    }

    public void setOtherId(int otherId) {
        this.otherId = otherId;
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

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
