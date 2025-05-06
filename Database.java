/**
 * Create a Database for arraylist
 */
import java.util.ArrayList;

public class Database {
    private ArrayList<StaffHire> staffList;//arraylist to store the staff data

    public Database() {
        staffList = new ArrayList<>();//constructor 
    }

    public void addStaff(StaffHire staff) {
        staffList.add(staff);
        System.out.println("Staff added successfully.");
    }

    public boolean vacancyNumberExists(int vacancyNumber) {
        for (StaffHire staff : staffList) {
            if (staff.getVacancyNumber() == vacancyNumber) {
                return true;
            }
        }
        return false;
    }

    //loop through the list to identify staff and remove
    public boolean removeStaffByVacancyNumber(int vacancyNumber) {
        // Loop through each staff member in the list
        for (int i = 0; i < staffList.size(); i++) {
            // Check if the current staff member has the matching vacancy number
            if (staffList.get(i).getVacancyNumber() == vacancyNumber) {
                staffList.remove(i); // Remove the staff member from the list
                return true; // Return true since the staff was found and removed
            }
        }

        // if no matching staff was found in the loop, print a message and return false
        System.out.println("Staff with Vacancy Number " + vacancyNumber + " not found.");
        return false;
    }

    
    public void changeStaffSalary(int vacancyNumber, int newSalary) {
        for (StaffHire staff : staffList) {//loop through the list
            if (staff instanceof FullTimeStaffHire) {
                if (staff.getVacancyNumber() == vacancyNumber) {//if vacancy number is found
                    ((FullTimeStaffHire) staff).setSalary(newSalary);//call setsalary and update. then print a message and return
                    System.out.println("Salary updated for Vacancy Number " + vacancyNumber + ".");
                    return;
                                    }
            }
        }
        System.out.println("Full-Time Staff with Vacancy Number " + vacancyNumber + " not found.");
    }

    public void changeWorkingShift(int vacancyNumber, String newShift) {
        for (StaffHire staff : staffList) {//loop the list
            if (staff.getVacancyNumber() == vacancyNumber) {//check vacancy number
                if (staff instanceof PartTimeStaffHire) {
                    ((PartTimeStaffHire) staff).setShifts(newShift);//call setShifts and update shift
                    System.out.println("Shift updated for Vacancy Number " + vacancyNumber + ".");
                    return;
                } else {
                    System.out.println("Staff with Vacancy Number " + vacancyNumber + " is not part-time.");
                    return;
                }

            }
        }
        System.out.println("Staff with Vacancy Number " + vacancyNumber + " not found.");
    }

    public String searchStaff(int vacancyNumber) {
        for (StaffHire staff : staffList) {//loop the list
            if (staff.getVacancyNumber() == vacancyNumber) {//check vacancy number exists
                staff.printDetails();  //call print details
                return "Staff Found!";
            }
        }
        return "No staff found with Vacancy Number: " + vacancyNumber;//return if not
    }

    public void listAllStaff() {
        if (staffList.isEmpty()) {//print message if list is empty
            System.out.println("No staff hired yet.");
        } else {
            for (StaffHire staff : staffList) {//loop list and print all staff details
                System.out.println("Staff Name: " + staff.getStaffName());
                System.out.println("Vacancy Number: " + staff.getVacancyNumber());
                System.out.println("Designation: " + staff.getDesignation());
                System.out.println("Job Type: " + staff.getJobType());
                System.out.println("Appointed By: " + staff.getAppointedBy());
                System.out.println("Qualification: " + staff.getQualification());
                System.out.println("Joining Date: " + staff.getJoiningDate());

                if (staff instanceof FullTimeStaffHire) {//only show relevent details depending on instance of FullTimeStaffHire or PartTimeStaffHire
                    FullTimeStaffHire fullTime = (FullTimeStaffHire) staff;
                    System.out.println("Salary: " + fullTime.returnSalary());
                    System.out.println("Weekly Hours: " + fullTime.returnweeklyFractionalHours());
                } else if (staff instanceof PartTimeStaffHire) {
                    PartTimeStaffHire partTime = (PartTimeStaffHire) staff;
                    System.out.println("Working Hours: " + partTime.returnWorkingHours());
                    System.out.println("Wages Per Hour: " + partTime.returnWagesPerHour());
                    System.out.println("Shifts: " + partTime.returnShifts());
                }
                System.out.println("-----");//two spacers to create a gap between the listed staff
                System.out.println("-----");
            }
        }
    }

}

