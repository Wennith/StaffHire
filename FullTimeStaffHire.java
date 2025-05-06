
/**
 * Write a description of class FullTimeStaffHire here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FullTimeStaffHire extends StaffHire
{
    private int salary;  
    private String weeklyFractionalHours;

    public FullTimeStaffHire(int vacancyNumber, int salary, String weeklyFractionalHours,
    String designationType, String jobType, String appointedBy, 
    String staffName, String joiningDate, String qualification,
    boolean joined) {

        super(vacancyNumber, designationType, jobType, staffName, joiningDate, 
            qualification, appointedBy, joined);

        this.salary = salary;
        this.weeklyFractionalHours = weeklyFractionalHours;

    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Salary: " + salary);
        System.out.println("Weekly Fractional Hours: " + weeklyFractionalHours);
    }

    public void setSalary(int amount) {
        this.salary = amount; // Corrected to actually update the salary
    }

    public int returnSalary(){

        return salary;
    }

    public void setweeklyFractionalHours(String weeklyFractionalHours)
    {
        this.weeklyFractionalHours = weeklyFractionalHours;
    }

    public String returnweeklyFractionalHours()
    {
        return weeklyFractionalHours;
    }

}
