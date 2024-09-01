public class Department {
    private int departmentId;
    private String departmentName;
    private String description;
    private int headId;
    private int bedCount;

    public Department(int departmentId, String departmentName, String description, int headId, int bedCount) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.description = description;
        this.headId = headId;
        this.bedCount = bedCount;
    }

   
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHeadId() {
        return headId;
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }

    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }
}
