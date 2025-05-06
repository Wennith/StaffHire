import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class RecruitmentSystemGUI implements ActionListener {
    private JFrame frame;
    private Database database;

    private JTextField nameField, designationField, dateField, vacancyField,
    qualificationField, appointedByField,
    salaryField, WFHField, WHField, HourlyRateField, shiftsField,
    displayNumberField;

    private JButton addSalaryButton;
    private JButton addFullTimeStaffButton;
    private JButton addPartTimeStaffButton;
    private JButton addWorkingShiftsButton;
    private JButton terminateButton;
    private JButton searchButton;
    private JButton displayAllStaffButton;

    private JComboBox<String> jobTypeComboBox;
    private JComboBox<String> designationComboBox;
    private JComboBox<String> shiftTypeComboBox;

    public RecruitmentSystemGUI() {
        database = new Database();  // Initialize database
        makeFrame();                // Now build the GUI
    }

    private void makeFrame()
    {       
        frame = new JFrame("Staff-Hire");

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(0, 4, 10, 10)); 
        frame.setSize(900, 500); 

        // Create and add labels and fields
        vacancyField = new JTextField(15); 
        contentPane.add(new JLabel("Vacancy Number: "));
        contentPane.add(vacancyField);

        nameField = new JTextField(15);
        contentPane.add(new JLabel("Name:"));
        contentPane.add(nameField);

        String[] designation = {"Full-Time", "Part-Time"};
        designationComboBox = new JComboBox<>(designation);  
        designationComboBox.addActionListener(this); 
        contentPane.add(new JLabel("Designation:"));
        contentPane.add(designationComboBox);

        dateField = new JTextField(15);
        contentPane.add(new JLabel("Date:"));
        contentPane.add(dateField);

        String[] jobTypes = {"Permanent", "Contract", "Temporary"};
        jobTypeComboBox = new JComboBox<>(jobTypes); 
        contentPane.add(new JLabel("Job Type:"));
        contentPane.add(jobTypeComboBox);

        qualificationField = new JTextField(15);
        contentPane.add(new JLabel("Qualification:"));
        contentPane.add(qualificationField);

        appointedByField = new JTextField(15);
        contentPane.add(new JLabel("Appointed By:"));
        contentPane.add(appointedByField);

        salaryField = new JTextField(15);
        contentPane.add(new JLabel("Salary:"));
        contentPane.add(salaryField);

        contentPane.add(new JLabel(""));
        contentPane.add(new JLabel(""));
        contentPane.add(new JLabel(""));
        contentPane.add(new JLabel(""));

        WFHField = new JTextField(15);
        contentPane.add(new JLabel("Weekly Fractional Hours:"));
        contentPane.add(WFHField);

        WHField = new JTextField(15);
        contentPane.add(new JLabel("Working Hours:"));
        contentPane.add(WHField);

        HourlyRateField= new JTextField(15);
        contentPane.add(new JLabel("Wages Per Hour:"));
        contentPane.add(HourlyRateField);

        String[] shiftTypes = {"Morning", "Day", "Afternoon"};
        shiftTypeComboBox = new JComboBox<>(shiftTypes); 
        contentPane.add(new JLabel("Shifts:"));
        contentPane.add(shiftTypeComboBox);

        contentPane.add(new JLabel(""));
        contentPane.add(new JLabel(""));
        contentPane.add(new JLabel(""));
        contentPane.add(new JLabel(""));  
        contentPane.add(new JLabel(""));  

        addFullTimeStaffButton = new JButton("Add Full-Time Staff");
        contentPane.add(addFullTimeStaffButton);
        addFullTimeStaffButton.addActionListener(this);

        addPartTimeStaffButton = new JButton("Add Part-Time Staff");
        contentPane.add(addPartTimeStaffButton);
        addPartTimeStaffButton.addActionListener(this);

        contentPane.add(new JLabel(""));
        contentPane.add(new JLabel(""));
        contentPane.add(new JLabel(""));
        contentPane.add(new JLabel(""));
        contentPane.add(new JLabel(""));
        contentPane.add(new JLabel(""));

        displayNumberField = new JTextField(15);
        contentPane.add(new JLabel("Display Number:"));
        contentPane.add(displayNumberField);

        contentPane.add(new JLabel(""));

        searchButton = new JButton("Search");
        contentPane.add(searchButton);
        searchButton.addActionListener(this);

        terminateButton = new JButton("Terminate");
        contentPane.add(terminateButton);
        terminateButton.addActionListener(this);

        addSalaryButton = new JButton("Add Salary");
        contentPane.add(addSalaryButton);
        addSalaryButton.addActionListener(this);

        addWorkingShiftsButton = new JButton("Add Working Shifts");
        contentPane.add(addWorkingShiftsButton);
        addWorkingShiftsButton.addActionListener(this);

        displayAllStaffButton = new JButton("Display All Staff");
        contentPane.add(displayAllStaffButton);
        displayAllStaffButton.addActionListener(this);

        frame.setVisible(true);

        // Make certain fields invisible initially, if needed
        salaryField.setVisible(false);
        WFHField.setVisible(false);
        WHField.setVisible(false);
        HourlyRateField.setVisible(false);
        shiftTypeComboBox.setVisible(false);

        // Initially, update the fields to reflect the default designation (Full-Time)
        updateDesignationFields("Full-Time");
    }

    private void updateDesignationFields(String designation) {
        if (designation.equals("Full-Time")) {
            salaryField.setVisible(true);
            WFHField.setVisible(true);
            WHField.setVisible(false);
            HourlyRateField.setVisible(false); // Hide Hourly Rate for Full-Time
            shiftTypeComboBox.setVisible(false);     // Hide shifts for Full-Time
        } else if (designation.equals("Part-Time")) {
            salaryField.setVisible(false);    // Hide Salary for Part-Time
            WFHField.setVisible(false);       // Hide WFH for Part-Time
            WHField.setVisible(true);        // Hide Working Hours for Part-Time
            HourlyRateField.setVisible(true); // Show Hourly Rate for Part-Time
            shiftTypeComboBox.setVisible(true);     // Show shifts for Part-Time
        }
    }

    public void actionPerformed(ActionEvent event){
        String command = event.getActionCommand();

        if (event.getSource() == designationComboBox) {
            // Update fields whenever designation is selected
            String selectedDesignation = (String) designationComboBox.getSelectedItem();
            updateDesignationFields(selectedDesignation);
        }

        if (command.equals("Add Full-Time Staff")) {
            addFullTimeStaff();
        } else if (command.equals("Add Part-Time Staff")) {
            addPartTimeStaff();
        } else if (command.equals("Add Salary")) {
            addSalary();
        } else if (command.equals("Add Working Shifts")) {
            addWorkingShifts();
        } else if (command.equals("Terminate")) {
            terminate();
        } else if (command.equals("Search")) {
            search();
        }

        if (command.equals("Display All Staff")) {
            displayAllStaff();
        }
    }

    public void addFullTimeStaff() {
        String vacancyINumber = vacancyField.getText();
        if (vacancyINumber.isEmpty()) {
            System.out.println("Please enter a valid Vacancy Number."); 
            return;
        }

        int vacancyNumber = Integer.parseInt(vacancyINumber);

        if (database.vacancyNumberExists(vacancyNumber)) {
            System.out.println("This Vacancy Number is already in use. Please select another."); 
            return;
        }

        String selectedDesignation = (String) designationComboBox.getSelectedItem();
        if ("Part-Time".equals(selectedDesignation)) {
            System.out.println("You must select 'Full-Time' as the Designation to add a Full-Time staff member.");
            return;
        }

        String Name = nameField.getText();
        if (Name.isEmpty()) {
            System.out.println("Please enter a Staff Name."); 
            return;
        }

        String designation = "Full-Time";  
        String staffName = nameField.getText();
        String joiningDate = dateField.getText();
        String jobType = (String) jobTypeComboBox.getSelectedItem();
        String qualification = qualificationField.getText();
        String appointedBy = appointedByField.getText();
        boolean joined = true; 

        String salaryText = salaryField.getText();
        if (salaryText.isEmpty()) {
            System.out.println("Please enter a salary.");
            return;
        }

        int salary = Integer.parseInt(salaryText);
        String weeklyFractionalHours = WFHField.getText();

        FullTimeStaffHire newStaff = new FullTimeStaffHire(vacancyNumber, salary, weeklyFractionalHours, designation, 
                jobType, appointedBy, staffName, joiningDate, qualification, joined);

        database.addStaff(newStaff);
        System.out.println("Full-Time Staff Added Successfully!"); 

    }

    public void addPartTimeStaff() {
        String vacancyINumber = vacancyField.getText();
        if (vacancyINumber.isEmpty()) {
            System.out.println("Vacancy Number is Missing. Please enter a valid Vacancy Number.");
            return;
        }

        int vacancyNumber = Integer.parseInt(vacancyINumber);

        if (database.vacancyNumberExists(vacancyNumber)) {
            System.out.println("This Vacancy Number is already in use. Please select another."); 
            return;
        }

        String selectedDesignation = (String) designationComboBox.getSelectedItem();
        if ("Full-Time".equals(selectedDesignation)) {
            System.out.println("You must select 'Part-Time' as the Designation to add a Part-Time Staff.");
            return;
        }

        String Name = nameField.getText();
        if (Name.isEmpty()) {
            System.out.println("Please enter a Staff Name."); 
            return;
        }

        String designation = "Part-Time";
        String staffName = nameField.getText();
        String joiningDate = dateField.getText();
        String jobType = (String) jobTypeComboBox.getSelectedItem();
        String qualification = qualificationField.getText();
        String appointedBy = appointedByField.getText();
        boolean joined = true; 

        String workingHoursText = WHField.getText();
        if (workingHoursText.isEmpty()) {
            System.out.println("Please enter working hours."); 
            return;
        }

        int workingHours = Integer.parseInt(workingHoursText);

        String wagesPerHourText = HourlyRateField.getText();
        if (wagesPerHourText.isEmpty()) {
            System.out.println("Please enter wages per hour.");
            return;
        }

        double wagesPerHour = Double.parseDouble(wagesPerHourText);
        String shifts = (String) shiftTypeComboBox.getSelectedItem();

        PartTimeStaffHire newPartTimeStaff = new PartTimeStaffHire(vacancyNumber, workingHours, wagesPerHour, shifts, 
                false, designation, jobType, appointedBy, staffName, joiningDate, qualification, joined);

        database.addStaff(newPartTimeStaff);
        System.out.println("Part-Time Staff Added Successfully!");
    }

    public void addSalary() {
        String displayNumberText = displayNumberField.getText();
        if (displayNumberText.isEmpty()) {
            System.out.println("Please enter a display number.");
            return;
        }

        String newSalaryText = JOptionPane.showInputDialog(
        frame, "Enter the new salary:");
        if (newSalaryText == null || newSalaryText.trim().isEmpty()) {
            return; 
        }

        try {
            int vacancyNumber = Integer.parseInt(displayNumberText);
            int newSalary = Integer.parseInt(newSalaryText);

            database.changeStaffSalary(vacancyNumber, newSalary);

            System.out.println("Salary changed successfully!");
        }
        catch (NumberFormatException e) {
            System.out.println("Please enter valid numbers only."); 
        }
    }

    public void addWorkingShifts() {
        String displayNumberText = displayNumberField.getText();
        if (displayNumberText.isEmpty()) {
            System.out.println("Please enter an active display number.");
            return;
        }

        String[] shiftTypes = {"Morning", "Day", "Afternoon"};
        JComboBox<String> shiftTypeComboBox = new JComboBox<>(shiftTypes);

        int result = JOptionPane.showConfirmDialog(frame, shiftTypeComboBox,
                "Select Shift", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String selectedShift = (String) shiftTypeComboBox.getSelectedItem();

            try {
                int vacancyNumber = Integer.parseInt(displayNumberText);
                database.changeWorkingShift(vacancyNumber, selectedShift);
                System.out.println("Shift updated successfully!");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid vacancy number.");
            }
        }
    }

    public void terminate() {
        String displayNumberText = displayNumberField.getText();
        if (displayNumberText.isEmpty()) {
            System.out.println("Please enter a vacancy number.");
            return;
        }

        int vacancyNumber = Integer.parseInt(displayNumberText);

        boolean removed = database.removeStaffByVacancyNumber
        (vacancyNumber);//calling remove staff method from StaffHire

        if (removed) {
            System.out.println("Staff with Vacancy Number " + vacancyNumber + " has been terminated.");
        } else {
            System.out.println("No staff found with vacancy number: " + vacancyNumber);
        }
    }

    public void search() {
        String displayNumberText = displayNumberField.getText();
        if (displayNumberText.isEmpty()) {
            System.out.println("Please enter a vacancy number.");
            return;
        }

        try {
            int vacancyNumber = Integer.parseInt(displayNumberText);
            
            // Get the details of the staff member with the given vacancy number
            String staffDetails = database.searchStaff(vacancyNumber);
 
            // Print the staff details
            System.out.println(staffDetails);

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid vacancy number in 'Display Number:'.");
        }
    }

    public void displayAllStaff() {
        database.listAllStaff();
    }

}