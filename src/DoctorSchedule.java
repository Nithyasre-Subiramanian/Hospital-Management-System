public class DoctorSchedule {
    private int slotId;
    private String session;
    private String slotTime;
    private int doctorId;
    private boolean isAvailable;

    public DoctorSchedule(int slotId, String session, String slotTime, int doctorId, boolean isAvailable) {
        this.slotId = slotId;
        this.session = session;
        this.slotTime = slotTime;
        this.doctorId = doctorId;
        this.isAvailable = isAvailable;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(String slotTime) {
        this.slotTime = slotTime;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

   
    public String toString() {
        return String.format("Slot ID: %d, Session: %s, Time: %s, Doctor ID: %d, Available: %b",
                slotId, session, slotTime, doctorId, isAvailable);
    }
}
