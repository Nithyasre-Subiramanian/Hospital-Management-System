import java.sql.Date;

public class SalaryRecord {
    private int salaryId;
    private int employeeId;
    private double baseSalary;
    private double specializationBonus;
    private double experienceBonus;
    private double totalSalary;
    private String salaryMonth;
    private Date salaryDate;
    private String status;
    private String notes;

 
    public SalaryRecord(int salaryId, int employeeId, double baseSalary, double specializationBonus, 
                        double experienceBonus, double totalSalary, String salaryMonth, 
                        Date salaryDate, String status, String notes) {
        this.salaryId = salaryId;
        this.employeeId = employeeId;
        this.baseSalary = baseSalary;
        this.specializationBonus = specializationBonus;
        this.experienceBonus = experienceBonus;
        this.totalSalary = totalSalary;
        this.salaryMonth = salaryMonth;
        this.salaryDate = salaryDate;
        this.status = status;
        this.notes = notes;
    }

  
    public int getSalaryId() { return salaryId; }
    public void setSalaryId(int salaryId) { this.salaryId = salaryId; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public double getBaseSalary() { return baseSalary; }
    public void setBaseSalary(double baseSalary) { this.baseSalary = baseSalary; }

    public double getSpecializationBonus() { return specializationBonus; }
    public void setSpecializationBonus(double specializationBonus) { this.specializationBonus = specializationBonus; }

    public double getExperienceBonus() { return experienceBonus; }
    public void setExperienceBonus(double experienceBonus) { this.experienceBonus = experienceBonus; }

    public double getTotalSalary() { return totalSalary; }
    public void setTotalSalary(double totalSalary) { this.totalSalary = totalSalary; }

    public String getSalaryMonth() { return salaryMonth; }
    public void setSalaryMonth(String salaryMonth) { this.salaryMonth = salaryMonth; }

    public Date getSalaryDate() { return salaryDate; }
    public void setSalaryDate(Date salaryDate) { this.salaryDate = salaryDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

   
    public String toString() {
        return "SalaryRecord [salaryId=" + salaryId + ", employeeId=" + employeeId + ", baseSalary=" + baseSalary +
                ", specializationBonus=" + specializationBonus + ", experienceBonus=" + experienceBonus +
                ", totalSalary=" + totalSalary + ", salaryMonth=" + salaryMonth + ", salaryDate=" + salaryDate +
                ", status=" + status + ", notes=" + notes + "]";
    }
}
