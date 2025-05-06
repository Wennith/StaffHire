/**
 * Create a Database for arraylist
 */
import java.util.ArrayList;

public class Database {
    private ArrayList<StaffHire> staffList;

    public Database() {
        staffList = new ArrayList<>();
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

    public boolean removeStaffByVacancyNumber(int vacancyNumber) {
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).getVacancyNumber() == vacancyNumber) {
                staffList.remove(i);
                return true;
            }
            else{
                System.out.println("Staff with Vacancy Number " 
                + vacancyNumber + " not found.");
            }
        }
        return false;
    }

    public void changeStaffSalary(int vacancyNumber, int newSalary) {
        for (StaffHire staff : staffList) {
            if (staff instanceof FullTimeStaffHire) {
                if (staff.getVacancyNumber() == vacancyNumber) {
                    ((FullTimeStaffHire) staff).setSalary(newSalary);
                    System.out.println("Salary updated for Vacancy Number " + vacancyNumber + ".");
                    return;
                }
            }
        }
        System.out.println("Full-Time Staff with Vacancy Number " + vacancyNumber + " not found.");
    }

    public void changeWorkingShift(int vacancyNumber, String newShift) {
        for (StaffHire staff : staffList) {
            if (staff.getVacancyNumber() == vacancyNumber) {
                if (staff instanceof PartTimeStaffHire) {
                    ((PartTimeStaffHire) staff).setShifts(newShift);
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
        for (StaffHire staff : staffList) {
            if (staff.getVacancyNumber() == vacancyNumber) {
                staff.printDetails();  
                return "Staff Found!";
            }
        }
        return "No staff found with Vacancy Number: " + vacancyNumber;
    }

    public void listAllStaff() {
        if (staffList.isEmpty()) {
            System.out.println("No staff hired yet.");
        } else {
            for (StaffHire staff : staffList) {
                System.out.println("Staff Name: " + staff.getStaffName());
                System.out.println("Vacancy Number: " + staff.getVacancyNumber());
                System.out.println("Designation: " + staff.getDesignation());
                System.out.println("Job Type: " + staff.getJobType());
                System.out.println("Appointed By: " + staff.getAppointedBy());
                System.out.println("Qualification: " + staff.getQualification());
                System.out.println("Joining Date: " + staff.getJoiningDate());

                if (staff instanceof FullTimeStaffHire) {
                    FullTimeStaffHire fullTime = (FullTimeStaffHire) staff;
                    System.out.println("Salary: " + fullTime.returnSalary());
                    System.out.println("Weekly Hours: " + fullTime.returnweeklyFractionalHours());
                } else if (staff instanceof PartTimeStaffHire) {
                    PartTimeStaffHire partTime = (PartTimeStaffHire) staff;
                    System.out.println("Working Hours: " + partTime.returnWorkingHours());
                    System.out.println("Wages Per Hour: " + partTime.returnWagesPerHour());
                    System.out.println("Shifts: " + partTime.returnShifts());
                }
                System.out.println("-----");
                System.out.println("-----");
            }
        }
    }

}

