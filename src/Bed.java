public class Bed {
    private int bedId;
    private int departmentId;
    private String bedNumber;
    private String bedType;
    private String status; 

    
    public Bed(int departmentId, String bedNumber, String bedType, String status) {
        this.departmentId = departmentId;
        this.bedNumber = bedNumber;
        this.bedType = bedType;
        this.status = status;
    }
    
    public int getBedId() {
        return bedId;
    }

    public void setBedId(int bedId) {
        this.bedId = bedId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

	public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public boolean isAvailable() {
		
		return false;
	}
}
