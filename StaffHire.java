
/**
 * Write a description of class StaffHire here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StaffHire
{
    private int vacancyNumber;
    private String designation;
    private String jobType; // Job Type indicates permanent, contract, temporary job
    private String staffName;
    private String joiningDate;
    private String qualification;
    private String appointedBy;
    private boolean joined;

    // Constructor to initialize a StaffHire object.
    public StaffHire(int vacancyNumber, String designation, String jobType, String staffName, 
    String joiningDate, String qualification, String appointedBy, boolean joined) 
    {
        this.vacancyNumber = vacancyNumber;
        this.designation = designation;
        this.jobType = jobType;
        this.staffName = staffName;
        this.joiningDate = joiningDate;
        this.qualification = qualification;
        this.appointedBy = appointedBy;
        this.joined = joined; 
    }

    // Getter and Setter methods
    public int getVacancyNumber() {
        return vacancyNumber;
    }

    public void setVacancyNumber(int vacancyNumber) {
        this.vacancyNumber = vacancyNumber;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public String getQualification() {
        return qualification;
    }

    public String getAppointedBy() {
        return appointedBy;
    }

    public boolean getJoined() {
        return joined;
    }

    public void setJoined(boolean joined) {
        this.joined = joined;
    }

    // Method to print staff details 
    public void printDetails() {
        System.out.println("Staff Name: " + staffName);
        System.out.println("Vacancy Number: " + vacancyNumber);
        System.out.println("Designation: " + designation);
        System.out.println("Job Type: " + jobType);
        System.out.println("Joining Date: " + joiningDate);
        System.out.println("Qualification: " + qualification);
        System.out.println("Appointed By: " + appointedBy);
        System.out.println("Joined: " + (joined ? "Yes" : "No"));
    }

}
