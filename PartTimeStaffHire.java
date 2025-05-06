/**
 * 
 */
public class PartTimeStaffHire extends StaffHire
{
    private int workingHours;
    private double wagesPerHour;
    private String shifts;
    private boolean terminated;

    public PartTimeStaffHire(int vacancyNumber, int workingHours, double wagesPerHour, String shifts, 
    boolean terminated, String designationType, String jobType, String appointedBy, 
    String staffName, String joiningDate, String qualification, boolean joined) 
    {
        super(vacancyNumber, designationType, jobType, appointedBy, staffName, 
            joiningDate, qualification, joined);

        this.workingHours = workingHours;//HoursPerDay
        this.wagesPerHour = wagesPerHour;//HourlyWage
        this.shifts = shifts;//TimeOfDay morning, day or evening
        this.terminated = terminated;
    }

    @Override
    public void printDetails() {
        super.printDetails(); // Print the basic details from StaffHire
        System.out.println("Shifts: " + shifts);
        System.out.println("Working Hours: " + workingHours);
        System.out.println("Wages Per Hour: " + wagesPerHour);
    }

    public void displayAllDetails() {
        System.out.println("Working hours per day: " + workingHours);
        System.out.println("Wages Per Hour: " + wagesPerHour);
        System.out.println("shifts: " + shifts);
        System.out.println("terminated: " + terminated);
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public int returnWorkingHours() {
        return workingHours;
    }

    public void setWagesPerHour(double wagesPerHour) {
        this.wagesPerHour = wagesPerHour;
    }

    public double returnWagesPerHour() {
        return wagesPerHour;
    }

    public void setShifts(String shifts) {
        this.shifts = shifts;
    }

    public String returnShifts() {
        return shifts;
    }
}