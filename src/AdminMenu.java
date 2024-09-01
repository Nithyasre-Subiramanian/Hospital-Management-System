import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.sql.Time;
import java.util.InputMismatchException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

             
                	
//Menu to Admin to select particular field
            
                	public class AdminMenu {

                		 public static void showMenu() throws SQLException {
                		        try (Scanner scanner = new Scanner(System.in)) {
                		            int option = -1;
                		            boolean validInput = false;

                		            while (!validInput) {
                		                try {
                		                    System.out.println("\nAdmin Menu:");
                		                    System.out.println("1. Manage Employees");
                		                    System.out.println("2. Manage Patients");
                		                    System.out.println("3. Manage Appointments");
                		                    System.out.println("4. Manage Departments");
                		                    System.out.println("5. Manage Bed Details");
                		                    System.out.println("6. Manage Admissions");
                		                    System.out.println("7. Manage Medical Records");
                		                    System.out.println("8. Manage Billing");
                		                    System.out.println("9. Manage Medicine");
                		                    System.out.println("10. Manage Operations");
                		                    System.out.println("11. Manage Duty Records");
                		                    System.out.println("12. Manage Salary Details");
                		                    System.out.println("13. Manage Prescriptions");
                		                    System.out.println("14. Logout");

                		                    System.out.print("Select an option: ");
                		                    if (scanner.hasNextInt()) {
                		                        option = scanner.nextInt();
                		                        validInput = true; 
                		                    } else {
                		                        System.out.println("Invalid input. Please enter a number.");
                		                        scanner.next(); 
                		                    }

                		                } catch (InputMismatchException e) {
                		                    System.out.println("Invalid input. Please enter a number.");
                		                    scanner.next(); 
                		                } catch (NoSuchElementException e) {
                		                    System.out.println("No input available. Please try again.");
                		                    break; 
                		                } catch (IllegalStateException e) {
                		                    System.out.println("Scanner is closed. Exiting...");
                		                    return; 
                		                }
                		            }

                	
                		            switch (option) {
                		                case 1:
                		                    manageEmployees();
                		                    break;
                		                case 2:
                		                    managePatients();
                		                    break;
                		                case 3:
                		                    manageAppointments();
                		                    break;
                		                case 4:
                		                    manageDepartments();
                		                    break;
                		                case 5:
                		                    manageBedDetails();
                		                    break;
                		                case 6:
                		                    manageAdmissions();
                		                    break;
                		                case 7:
                		                    manageMedicalRecords();
                		                    break;
                		                case 8:
                		                    manageBilling();
                		                    break;
                		                case 9:
                		                    manageMedicine();
                		                    break;
                		                case 10:
                		                    manageOperations();
                		                    break;
                		                case 11:
                		                    manageDutyRecords();
                		                    break;
                		                case 12:
                		                    manageSalaryDetails();
                		                    break;
                		                case 13:
                		                    managePrescriptions();
                		                    break;
                		                case 14:
                		                    System.out.println("Logging out...");
                		                    break;
                		                default:
                		                    System.out.println("Invalid option. Please try again.");
                		                    break;
                		            }
                		        }
                		    }


    //employee details                   
 static void manageEmployees() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Manage Employees:");
        System.out.println("1. Add Employee");
        System.out.println("2. View Employee Details");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                EmployeeDAO.addEmployee();
                break;
            case 2:
                EmployeeDAO.viewEmployeeDetails();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

 // patients
    private static void managePatients() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Manage Patients:");
        System.out.println("1. Add Patient");
        System.out.println("2. View Patient Details");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                PatientDAO.addPatient();
                break;
            case 2:
                PatientDAO.viewPatientDetails();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }
    //Appointments
    private static void manageAppointments() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Manage Appointments:");
        System.out.println("1. Add Appointment");
        System.out.println("2. View Appointment Details");
        System.out.println("3. Update Appointment");
        System.out.println("4. Delete Appointment");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); 

        switch (option) {
            case 1:
                AppointmentDAO.addAppointment(getAppointmentDetails());
                break;
            case 2:
                viewAppointmentDetails();
                break;
            case 3:
                updateAppointment();
                break;
            case 4:
                deleteAppointment();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private static Appointment getAppointmentDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Appointment Details:");

        System.out.print("Patient ID: ");
        int patientId = scanner.nextInt();

        System.out.print("Doctor ID: ");
        int doctorId = scanner.nextInt();

        System.out.print("Appointment Date (YYYY-MM-DD): ");
        Date appointmentDate = Date.valueOf(scanner.next());

        System.out.print("Appointment Date (YYYY-MM-DD): ");
        Date appointmentDate1 = Date.valueOf(scanner.next());

        Time appointmentTime = getTimeFromInput();

        System.out.print("Status (Scheduled/Completed/Cancelled): ");
        String status = scanner.next();

        System.out.print("Reason: ");
        String reason = scanner.next();

        return new Appointment(0, patientId, doctorId, appointmentDate1, appointmentTime, status, reason);
    }

    private static void viewAppointmentDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Appointment ID to view: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Appointment appointment = AppointmentDAO.getAppointmentById(appointmentId);
            if (appointment != null) {
                System.out.println("Appointment Details:");
                System.out.println(appointment);
            } else {
                System.out.println("No appointment found with ID " + appointmentId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateAppointment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Appointment ID to update: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine(); 

        try {
            Appointment appointment = AppointmentDAO.getAppointmentById(appointmentId);
            if (appointment != null) {
                System.out.println("Current details: " + appointment);
                System.out.println("Enter new details:");

                System.out.print("Patient ID: ");
                appointment.setPatientId(scanner.nextInt());
                scanner.nextLine(); 

                System.out.print("Doctor ID: ");
                appointment.setDoctorId(scanner.nextInt());
                scanner.nextLine(); 

                System.out.print("Appointment Date (YYYY-MM-DD): ");
                appointment.setAppointmentDate(Date.valueOf(scanner.next()));

                System.out.print("Appointment Time (HH:MM): ");
                appointment.setAppointmentTime(Time.valueOf(scanner.next() + ":00"));

                System.out.print("Status (Scheduled/Completed/Cancelled): ");
                appointment.setStatus(scanner.next());

                System.out.print("Reason: ");
                appointment.setReason(scanner.next());

                AppointmentDAO.updateAppointment(appointment);
                System.out.println("Appointment updated successfully.");
            } else {
                System.out.println("No appointment found with ID " + appointmentId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteAppointment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Appointment ID to delete: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine(); 

        try {
            AppointmentDAO.deleteAppointment(appointmentId);
            System.out.println("Appointment deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // department
    private static void manageDepartments() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Manage Departments:");
        System.out.println("1. Add Department");
        System.out.println("2. View Department Details");
        System.out.println("3. Update Department");
        System.out.println("4. Delete Department");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
            	DepartmentDAO.addDepartment();
                break;
            case 2:
            	DepartmentDAO.viewDepartmentDetails();
                break;
            case 3:
            	DepartmentDAO.updateDepartment();
                break;
            case 4:
            	DepartmentDAO.deleteDepartment();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

//bed details
                
    private static void manageBedDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Manage Bed Details:");
        System.out.println("1. Add Bed Details");
        System.out.println("2. View Bed Details");
        System.out.println("3. Count Available Beds Per Department");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); 

        switch (option) {
            case 1:
                BedDAO.addBed();
                break;
            case 2:
            	BedDAO.viewBedDetails();
                break;
            case 3:
            	BedDAO.countAvailableBeds();
                break;
//            case 4:
//                patientDetailsOnBed();
//                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

//Medical records
    
    private static void manageMedicalRecords() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Manage Medical Records:");
        System.out.println("1. Add Medical Record");
        System.out.println("2. View Medical Records");
        System.out.println("3. Update Medical Record");
        System.out.println("4. Delete Medical Record");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); 

        switch (option) {
            case 1:
            	MedicalRecordDAO.addMedicalRecord();
                break;
            case 2:
            	MedicalRecordDAO.viewMedicalRecords();
                break;
            case 3:
            	MedicalRecordDAO.updateMedicalRecord();
                break;
            case 4:
            	MedicalRecordDAO.deleteMedicalRecord();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }



    // Manage Medicine records
    private static void manageMedicine() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Manage Medicine:");
        System.out.println("1. Add Medicine");
        System.out.println("2. View Medicine");
        System.out.println("3. Update Medicine");
        System.out.println("4. Delete Medicine");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); 

        switch (option) {
            case 1:
                addMedicine();
                break;
            case 2:
                viewMedicine();
                break;
            case 3:
                updateMedicine();
                break;
            case 4:
                deleteMedicine();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private static void addMedicine() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add Medicine:");
        System.out.print("Enter Medicine Name: ");
        String medicineName = scanner.nextLine();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        Medicine medicine = new Medicine(medicineName, price);
        try {
            MedicineDAO.addMedicine(medicine);
            System.out.println("Medicine added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding Medicine: " + e.getMessage());
        }
    }

    private static void viewMedicine() {
        try {
            List<Medicine> medicines = MedicineDAO.getAllMedicines();
            for (Medicine medicine : medicines) {
                System.out.println(medicine);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving Medicine: " + e.getMessage());
        }
    }

    private static void updateMedicine() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Medicine ID to update: ");
        int medicineId = scanner.nextInt();
        scanner.nextLine(); 

        try {
            Medicine existingMedicine = MedicineDAO.getMedicineById(medicineId);
            if (existingMedicine == null) {
                System.out.println("Medicine not found.");
                return;
            }

            System.out.print("Enter new Medicine Name (or leave blank to keep current): ");
            String medicineName = scanner.nextLine();
            medicineName = medicineName.isEmpty() ? existingMedicine.getMedicineName() : medicineName;

            System.out.print("Enter new Price (or leave blank to keep current): ");
            String input = scanner.nextLine();
            double price = input.isEmpty() ? existingMedicine.getPrice() : Double.parseDouble(input);

            Medicine updatedMedicine = new Medicine(medicineId, medicineName, price);
            MedicineDAO.updateMedicine(updatedMedicine);
            System.out.println("Medicine updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating Medicine: " + e.getMessage());
        }
    }

    private static void deleteMedicine() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Medicine ID to delete: ");
        int medicineId = scanner.nextInt();

        try {
            MedicineDAO.deleteMedicine(medicineId);
            System.out.println("Medicine deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting Medicine: " + e.getMessage());
        }
    }
    
    private static void manageOperations() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Manage Operations:");
        System.out.println("1. Add Operation");
        System.out.println("2. View Operations");
        System.out.println("3. Update Operation");
        System.out.println("4. Delete Operation");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); 

        switch (option) {
            case 1:
                addOperation();
                break;
            case 2:
                viewOperations();
                break;
            case 3:
                updateOperation();
                break;
            case 4:
                deleteOperation();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private static void addOperation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add Operation:");

        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();

        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();

        System.out.print("Enter Nurse ID: ");
        int nurseId = scanner.nextInt();

        System.out.print("Enter Operation Date (YYYY-MM-DD): ");
        Date operationDate = Date.valueOf(scanner.next());

        System.out.print("Enter Operation Time (HH:MM:SS): ");
        Time operationTime = Time.valueOf(scanner.next() + ":00");

        scanner.nextLine(); 
        System.out.print("Enter Operation Type: ");
        String operationType = scanner.nextLine();

        System.out.print("Enter Operation Room: ");
        String operationRoom = scanner.nextLine();

        System.out.print("Enter Duration (HH:MM:SS): ");
        Time duration = Time.valueOf(scanner.next() + ":00");

        System.out.print("Enter Status (Scheduled, Completed, Cancelled): ");
        String status = scanner.nextLine();

        System.out.print("Enter Notes: ");
        String notes = scanner.nextLine();

        Operation operation = new Operation(patientId, doctorId, nurseId, operationDate, operationTime,
                                             operationType, operationRoom, duration, status, notes);
        try {
            OperationDAO.addOperation(operation);
            System.out.println("Operation added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding Operation: " + e.getMessage());
        }
    }

    private static void viewOperations() {
        try {
            List<Operation> operations = OperationDAO.getAllOperations();
            for (Operation operation : operations) {
                System.out.println(operation);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving Operations: " + e.getMessage());
        }
    }

    private static void updateOperation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Operation ID to update: ");
        int operationId = scanner.nextInt();
        scanner.nextLine(); 

        try {
            Operation existingOperation = OperationDAO.getOperationById(operationId);
            if (existingOperation == null) {
                System.out.println("Operation not found.");
                return;
            }

            System.out.print("Enter new Patient ID (or leave blank to keep current): ");
            String input = scanner.nextLine();
            int patientId = input.isEmpty() ? existingOperation.getPatientId() : Integer.parseInt(input);

            System.out.print("Enter new Doctor ID (or leave blank to keep current): ");
            input = scanner.nextLine();
            int doctorId = input.isEmpty() ? existingOperation.getDoctorId() : Integer.parseInt(input);

            System.out.print("Enter new Nurse ID (or leave blank to keep current): ");
            input = scanner.nextLine();
            int nurseId = input.isEmpty() ? existingOperation.getNurseId() : Integer.parseInt(input);

            System.out.print("Enter new Operation Date (YYYY-MM-DD) (or leave blank to keep current): ");
            input = scanner.nextLine();
            Date operationDate = input.isEmpty() ? existingOperation.getOperationDate() : Date.valueOf(input);

            System.out.print("Enter new Operation Time (HH:MM:SS) (or leave blank to keep current): ");
            input = scanner.nextLine();
            Time operationTime = input.isEmpty() ? existingOperation.getOperationTime() : Time.valueOf(input + ":00");

            System.out.print("Enter new Operation Type (or leave blank to keep current): ");
            String operationType = scanner.nextLine();
            operationType = operationType.isEmpty() ? existingOperation.getOperationType() : operationType;

            System.out.print("Enter new Operation Room (or leave blank to keep current): ");
            String operationRoom = scanner.nextLine();
            operationRoom = operationRoom.isEmpty() ? existingOperation.getOperationRoom() : operationRoom;

            System.out.print("Enter new Duration (HH:MM:SS) (or leave blank to keep current): ");
            input = scanner.nextLine();
            Time duration = input.isEmpty() ? existingOperation.getDuration() : Time.valueOf(input + ":00");

            System.out.print("Enter new Status (Scheduled, Completed, Cancelled) (or leave blank to keep current): ");
            String status = scanner.nextLine();
            status = status.isEmpty() ? existingOperation.getStatus() : status;

            System.out.print("Enter new Notes (or leave blank to keep current): ");
            String notes = scanner.nextLine();
            notes = notes.isEmpty() ? existingOperation.getNotes() : notes;

            Operation updatedOperation = new Operation(patientId, doctorId, nurseId, operationDate, operationTime,
                                                       operationType, operationRoom, duration, status, notes);
            updatedOperation.setOperationId(operationId);
            OperationDAO.updateOperation(updatedOperation);
            System.out.println("Operation updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating Operation: " + e.getMessage());
        }
    }

    private static void deleteOperation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Operation ID to delete: ");
        int operationId = scanner.nextInt();

        try {
            OperationDAO.deleteOperation(operationId);
            System.out.println("Operation deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting Operation: " + e.getMessage());
        }
    }



    private static Time getTimeFromInput() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Time appointmentTime = null;

        while (appointmentTime == null) {
            System.out.print("Enter Appointment Time (HH:MM:SS): ");
            String timeInput = scanner.nextLine();

            try {
                java.util.Date parsedDate = dateFormat.parse(timeInput);
                appointmentTime = new Time(parsedDate.getTime());
            } catch (ParseException e) {
                System.out.println("Invalid time format. Please enter the time in HH:MM:SS format.");
            }
        }

        return appointmentTime;
    }
    private static void manageDutyRecords() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Manage Duty Records:");
        System.out.println("1. Add Duty Record");
        System.out.println("2. View Duty Records");
        System.out.println("3. Update Duty Record");
        System.out.println("4. Delete Duty Record");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); 

        switch (option) {
            case 1:
                addDutyRecord();
                break;
            case 2:
                viewDutyRecords();
                break;
            case 3:
                updateDutyRecord();
                break;
            case 4:
                deleteDutyRecord();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private static void addDutyRecord() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add Duty Record:");

        System.out.print("Enter Employee ID: ");
        int employeeId = scanner.nextInt();

        System.out.print("Enter Department ID: ");
        int departmentId = scanner.nextInt();

        System.out.print("Enter Duty Date (YYYY-MM-DD): ");
        Date dutyDate = Date.valueOf(scanner.next());

        System.out.print("Enter Shift (Morning, Afternoon, Evening, Night): ");
        String shift = scanner.next();

        System.out.print("Enter In Time (HH:MM:SS): ");
        Time inTime = Time.valueOf(scanner.next() + ":00");

        System.out.print("Enter Out Time (HH:MM:SS): ");
        Time outTime = Time.valueOf(scanner.next() + ":00");

        System.out.print("Enter Status (Scheduled, Completed, Missed): ");
        String status = scanner.next();

        System.out.print("Enter Notes: ");
        String notes = scanner.next();

        DutyRecord dutyRecord = new DutyRecord(employeeId, departmentId, dutyDate, shift, inTime, outTime, status, notes);
        try {
            DutyRecordDAO.addDutyRecord(dutyRecord);
            System.out.println("Duty record added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding duty record: " + e.getMessage());
        }
    }

    private static void viewDutyRecords() {
        try {
            List<DutyRecord> dutyRecords = DutyRecordDAO.getAllDutyRecords();
            for (DutyRecord dutyRecord : dutyRecords) {
                System.out.println(dutyRecord);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving duty records: " + e.getMessage());
        }
    }

    private static void updateDutyRecord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Duty ID to update: ");
        int dutyId = scanner.nextInt();
        scanner.nextLine(); 

        try {
            DutyRecord existingRecord = DutyRecordDAO.getDutyRecordById(dutyId);
            if (existingRecord == null) {
                System.out.println("Duty record not found.");
                return;
            }

            System.out.print("Enter new Employee ID (or leave blank to keep current): ");
            String input = scanner.nextLine();
            int employeeId = input.isEmpty() ? existingRecord.getEmployeeId() : Integer.parseInt(input);

            System.out.print("Enter new Department ID (or leave blank to keep current): ");
            input = scanner.nextLine();
            int departmentId = input.isEmpty() ? existingRecord.getDepartmentId() : Integer.parseInt(input);

            System.out.print("Enter new Duty Date (YYYY-MM-DD) (or leave blank to keep current): ");
            input = scanner.nextLine();
            Date dutyDate = input.isEmpty() ? existingRecord.getDutyDate() : Date.valueOf(input);

            System.out.print("Enter new Shift (Morning, Afternoon, Evening, Night) (or leave blank to keep current): ");
            String shift = scanner.nextLine();
            shift = shift.isEmpty() ? existingRecord.getShift() : shift;

            System.out.print("Enter new In Time (HH:MM:SS) (or leave blank to keep current): ");
            input = scanner.nextLine();
            Time inTime = input.isEmpty() ? existingRecord.getInTime() : Time.valueOf(input + ":00");

            System.out.print("Enter new Out Time (HH:MM:SS) (or leave blank to keep current): ");
            input = scanner.nextLine();
            Time outTime = input.isEmpty() ? existingRecord.getOutTime() : Time.valueOf(input + ":00");

            System.out.print("Enter new Status (Scheduled, Completed, Missed) (or leave blank to keep current): ");
            String status = scanner.nextLine();
            status = status.isEmpty() ? existingRecord.getStatus() : status;

            System.out.print("Enter new Notes (or leave blank to keep current): ");
            String notes = scanner.nextLine();
            notes = notes.isEmpty() ? existingRecord.getNotes() : notes;

            DutyRecord updatedRecord = new DutyRecord(employeeId, departmentId, dutyDate, shift, inTime, outTime, status, notes);
            updatedRecord.setDutyId(dutyId);
            DutyRecordDAO.updateDutyRecord(updatedRecord);
            System.out.println("Duty record updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating duty record: " + e.getMessage());
        }
    }

    private static void deleteDutyRecord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Duty ID to delete: ");
        int dutyId = scanner.nextInt();

        try {
            DutyRecordDAO.deleteDutyRecord(dutyId);
            System.out.println("Duty record deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting duty record: " + e.getMessage());
        }
    }

    private static void manageAdmissions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Manage Admissions:");
        System.out.println("1. Add Admission");
        System.out.println("2. View Admission Details");
        System.out.println("3. Update Admission");
        System.out.println("4. Delete Admission");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); 

        switch (option) {
            case 1:
                addAdmissions(scanner);
                break;
            case 2:
                viewAdmissions();
                break;
            case 3:
                updateAdmission(scanner);
                break;
            case 4:
                deleteAdmission(scanner);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private static  void addAdmissions(Scanner scanner) {
    	
        System.out.print("Enter Bed ID: ");
        int bedId = scanner.nextInt();
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Admission Date (YYYY-MM-DD): ");
        Date admissionDate = Date.valueOf(scanner.next());
        System.out.print("Enter Discharge Date (YYYY-MM-DD): ");
        Date dischargeDate = Date.valueOf(scanner.next());

        Admission admission = new Admission(bedId, patientId, patientId, admissionDate, dischargeDate);
        try {
            AdmissionDAO.addAdmission(admission);
            System.out.println("Admission added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding admission.");
        }
    }

    private static void viewAdmissions() {
        try {
            List<Admission> admissions = AdmissionDAO.getAllAdmissions();
            for (Admission admission : admissions) {
                System.out.println(admission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving admissions.");
        }
    }

    private static void updateAdmission(Scanner scanner) {
        System.out.print("Enter Admission ID to update: ");
        int admissionId = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter new Bed ID: ");
        int bedId = scanner.nextInt();
        System.out.print("Enter new Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter new Admission Date (YYYY-MM-DD): ");
        Date admissionDate = Date.valueOf(scanner.next());
        System.out.print("Enter new Discharge Date (YYYY-MM-DD): ");
        Date dischargeDate = Date.valueOf(scanner.next());

        Admission admission = new Admission(admissionId, bedId, patientId, admissionDate, dischargeDate);
        try {
            AdmissionDAO.updateAdmission(admission);
            System.out.println("Admission updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating admission.");
        }
    }

    private static void deleteAdmission(Scanner scanner) {
        System.out.print("Enter Admission ID to delete: ");
        int admissionId = scanner.nextInt();

        try {
            AdmissionDAO.deleteAdmission(admissionId);
            System.out.println("Admission deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting admission.");
        }
    }
    private static void managePrescriptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Manage Prescriptions:");
        System.out.println("1. Add Prescription");
        System.out.println("2. View Prescription Details");
        System.out.println("3. Update Prescription");
        System.out.println("4. Delete Prescription");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); 

        switch (option) {
            case 1:
                addPrescription(scanner);
                break;
            case 2:
                viewPrescriptions();
                break;
            case 3:
                updatePrescription(scanner);
                break;
            case 4:
                deletePrescription(scanner);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private static void addPrescription(Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter Prescription Date (YYYY-MM-DD): ");
        Date prescriptionDate = Date.valueOf(scanner.next());
        scanner.nextLine(); 
        System.out.print("Enter Instructions: ");
        String instructions = scanner.nextLine();

        Prescription prescription = new Prescription(0, patientId, doctorId, prescriptionDate, instructions); // 0 for auto-generated ID
        try {
            PrescriptionDAO.addPrescription(prescription);
            System.out.println("Prescription added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding prescription.");
        }
    }

    private static void viewPrescriptions() {
        try {
            List<Prescription> prescriptions = PrescriptionDAO.getAllPrescriptions();
            for (Prescription prescription : prescriptions) {
                System.out.println(prescription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving prescriptions.");
        }
    }

    private static void updatePrescription(Scanner scanner) {
        System.out.print("Enter Prescription ID to update: ");
        int prescriptionId = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter new Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter new Doctor ID: ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter new Prescription Date (YYYY-MM-DD): ");
        Date prescriptionDate = Date.valueOf(scanner.next());
        scanner.nextLine(); 
        System.out.print("Enter new Instructions: ");
        String instructions = scanner.nextLine();

        Prescription prescription = new Prescription(prescriptionId, patientId, doctorId, prescriptionDate, instructions);
        try {
            PrescriptionDAO.updatePrescription(prescription);
            System.out.println("Prescription updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating prescription.");
        }
    }

    private static void deletePrescription(Scanner scanner) {
        System.out.print("Enter Prescription ID to delete: ");
        int prescriptionId = scanner.nextInt();

        try {
            PrescriptionDAO.deletePrescription(prescriptionId);
            System.out.println("Prescription deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting prescription.");
        }
    
}  
    private static void manageSalaryDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Manage Salary Records:");
        System.out.println("1. Add Salary Record");
        System.out.println("2. View Salary Records");
        System.out.println("3. Update Salary Record");
        System.out.println("4. Delete Salary Record");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); 

        switch (option) {
            case 1:
                addSalaryRecord(scanner);
                break;
            case 2:
                viewSalaryRecords();
                break;
            case 3:
                updateSalaryRecord(scanner);
                break;
            case 4:
                deleteSalaryRecord(scanner);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private static void addSalaryRecord(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int employeeId = scanner.nextInt();
        System.out.print("Enter Base Salary: ");
        double baseSalary = scanner.nextDouble();
        System.out.print("Enter Specialization Bonus: ");
        double specializationBonus = scanner.nextDouble();
        System.out.print("Enter Experience Bonus: ");
        double experienceBonus = scanner.nextDouble();
        System.out.print("Enter Total Salary: ");
        double totalSalary = scanner.nextDouble();
        scanner.nextLine(); 
        System.out.print("Enter Salary Month: ");
        String salaryMonth = scanner.nextLine();
        System.out.print("Enter Salary Date (YYYY-MM-DD): ");
        Date salaryDate = Date.valueOf(scanner.nextLine());
        System.out.print("Enter Status (Paid/Pending): ");
        String status = scanner.nextLine();
        System.out.print("Enter Notes: ");
        String notes = scanner.nextLine();

        SalaryRecord salaryRecord = new SalaryRecord(0, employeeId, baseSalary, specializationBonus, experienceBonus, 
                                                     totalSalary, salaryMonth, salaryDate, status, notes);
        try {
            salaryRecordDAO.addSalaryRecord(salaryRecord);
            System.out.println("Salary record added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding salary record.");
        }
    }

    private static void viewSalaryRecords() {
        try {
            List<SalaryRecord> salaryRecords = salaryRecordDAO.getAllSalaryRecords();
            if (salaryRecords.isEmpty()) {
                System.out.println("No salary records found.");
            } else {
                for (SalaryRecord record : salaryRecords) {
                    System.out.println(record);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving salary records.");
        }
    }

    private static void updateSalaryRecord(Scanner scanner) {
        System.out.print("Enter Salary ID to update: ");
        int salaryId = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter new Employee ID: ");
        int employeeId = scanner.nextInt();
        System.out.print("Enter new Base Salary: ");
        double baseSalary = scanner.nextDouble();
        System.out.print("Enter new Specialization Bonus: ");
        double specializationBonus = scanner.nextDouble();
        System.out.print("Enter new Experience Bonus: ");
        double experienceBonus = scanner.nextDouble();
        System.out.print("Enter new Total Salary: ");
        double totalSalary = scanner.nextDouble();
        scanner.nextLine(); 
        System.out.print("Enter new Salary Month: ");
        String salaryMonth = scanner.nextLine();
        System.out.print("Enter new Salary Date (YYYY-MM-DD): ");
        Date salaryDate = Date.valueOf(scanner.nextLine());
        System.out.print("Enter new Status (Paid/Pending): ");
        String status = scanner.nextLine();
        System.out.print("Enter new Notes: ");
        String notes = scanner.nextLine();

        SalaryRecord salaryRecord = new SalaryRecord(salaryId, employeeId, baseSalary, specializationBonus, 
                                                     experienceBonus, totalSalary, salaryMonth, salaryDate, status, notes);
        try {
            salaryRecordDAO.updateSalaryRecord(salaryRecord);
            System.out.println("Salary record updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating salary record.");
        }
    }

    private static void deleteSalaryRecord(Scanner scanner) {
        System.out.print("Enter Salary ID to delete: ");
        int salaryId = scanner.nextInt();

        try {
            salaryRecordDAO.deleteSalaryRecord(salaryId);
            System.out.println("Salary record deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting salary record.");
        }
    }
    
    private static void manageBilling() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Manage Billing:");
        System.out.println("1. Add Billing Record");
        System.out.println("2. View Billing Records");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); 

        switch (option) {
            case 1:
                addBillingRecord(scanner);
                break;
            case 2:
                viewBillingRecords();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    // Add Billing Record
    private static void addBillingRecord(Scanner scanner) throws SQLException {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Prescription ID: ");
        int prescriptionId = scanner.nextInt();
        scanner.nextLine(); 

        try (Connection connection = DatabaseConnection.getConnection()) {
            
            String sql = "SELECT consultation_id,record_id, bed_id, scan_id, treatment_id FROM medical_records WHERE prescription_id = ?";
            int consultationId = 0, record_id=0, bedId = 0, scanId = 0, treatmentId = 0;

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, prescriptionId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        consultationId = resultSet.getInt("consultation_id");
                        record_id=resultSet.getInt("record_id");
                        bedId = resultSet.getInt("bed_id");
                        scanId = resultSet.getInt("scan_id");
                        treatmentId = resultSet.getInt("treatment_id");
                    } else {
                        System.out.println("No medical records found for the given Prescription ID.");
                        return;
                    }
                }
            }

            BillingDAO billingDAO = new BillingDAO(connection);
            double totalAmount = billingDAO.calculateTotalAmount(patientId, consultationId, bedId, scanId, treatmentId);

            sql = "INSERT INTO billing (patient_id, record_id, total_amount, bill_date, payment_status, prescription_id) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, patientId);
                statement.setInt(2, record_id); 
                statement.setDouble(3, totalAmount);
                statement.setDate(4, new java.sql.Date(System.currentTimeMillis()));
                statement.setString(5, "Pending");
                statement.setInt(6, prescriptionId);
                statement.executeUpdate();
                System.out.println("Billing record added successfully.");
            }
        }
    }


    // View Billing Records
    private static void viewBillingRecords() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM billing";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println("Bill ID: " + resultSet.getInt("bill_id"));
                    System.out.println("Patient ID: " + resultSet.getInt("patient_id"));
                    System.out.println("Record ID: " + resultSet.getInt("record_id"));
                    System.out.println("Total Amount: " + resultSet.getDouble("total_amount"));
                    System.out.println("Bill Date: " + resultSet.getDate("bill_date"));
                    System.out.println("Payment Status: " + resultSet.getString("payment_status"));
                    System.out.println("Prescription ID: " + resultSet.getInt("prescription_id"));
                    System.out.println("------------------------------");
                }
            }
        }
    }
                }
                
    
    
