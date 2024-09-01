import java.util.Date;

public class Patient {
    private int patientId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String contactNumber;
    private String email;
    private String address;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private String insuranceNumber;
    private String insuranceProvider;
    private String bloodGroup;
   
    
   public Patient() {}

    public Patient(int patientId, String firstName, String lastName, Date dateOfBirth, String gender,
			String contactNumber, String email, String address, String emergencyContactName,
			String emergencyContactNumber, String insuranceNumber, String insuranceProvider, String bloodGroup
			) {
		super();
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.email = email;
		this.address = address;
		this.emergencyContactName = emergencyContactName;
		this.emergencyContactNumber = emergencyContactNumber;
		this.insuranceNumber = insuranceNumber;
		this.insuranceProvider = insuranceProvider;
		this.bloodGroup = bloodGroup;
		
	}

	public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return (Date) dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

  

   
}
