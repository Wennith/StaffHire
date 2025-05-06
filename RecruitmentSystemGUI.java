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
        makeFrame();                // build the GUI
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

    //method to hide fields based on the designation selected
    private void updateDesignationFields(String designation) {
        if (designation.equals("Full-Time")) {
            salaryField.setVisible(true);
            WFHField.setVisible(true);
            WHField.setVisible(false);
            HourlyRateField.setVisible(false); // Hide hourly rate for Full-Time
            shiftTypeComboBox.setVisible(false);     // Hide shifts for Full-Time
        } else if (designation.equals("Part-Time")) {
            salaryField.setVisible(false);    // Hide Salary for Part-Time
            WFHField.setVisible(false);       // Hide WFH for Part-Time
            WHField.setVisible(true);        // Hide WH for Part-Time
            HourlyRateField.setVisible(true); // Show HR for Part-Time
            shiftTypeComboBox.setVisible(true);     // Show shifts for Part-Time
        }
    }

    public void actionPerformed(ActionEvent event){
        String command = event.getActionCommand();

        if (event.getSource() == designationComboBox) {
            // update fields whenever a designation is selected via the method above
            String selectedDesignation = (String) designationComboBox.getSelectedItem();
            updateDesignationFields(selectedDesignation);
        }

        //call a method depending on which button is clicked
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
        String vacancyINumber = vacancyField.getText();//get vacancy number
        if (vacancyINumber.isEmpty()) {
            System.out.println("Please enter a valid Vacancy Number."); 
            return;//return if number box is empty
        }

        //convert vacancyNumber to int
        int vacancyNumber = Integer.parseInt(vacancyINumber);

        //check the vacancy number is not in use 
        if (database.vacancyNumberExists(vacancyNumber)) {
            System.out.println("This Vacancy Number is already in use. Please select another."); 
            return;//return if it is
        }

        //check designation field
        String selectedDesignation = (String) designationComboBox.getSelectedItem();
        if ("Part-Time".equals(selectedDesignation)) {
            System.out.println("You must select 'Full-Time' as the Designation to add a Full-Time staff member.");
            return;
        }

        
        //get the inputed name
        String Name = nameField.getText();
        if (Name.isEmpty()) {
            System.out.println("Please enter a Staff Name."); 
            return;
        }

        // get the rest of the details
        String designation = "Full-Time";  //designation pre set as will allways be this if hire full time staff button is pressed
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

        //convert salary to int
        int salary = Integer.parseInt(salaryText);
        String weeklyFractionalHours = WFHField.getText();//get wfh

        //create a FullTimeStaffHire object to save in the database
        FullTimeStaffHire newStaff = new FullTimeStaffHire(vacancyNumber, salary, weeklyFractionalHours, designation, 
                jobType, appointedBy, staffName, joiningDate, qualification, joined);

        database.addStaff(newStaff);//save the staff
        System.out.println("Full-Time Staff Added Successfully!");//print succsess message 

    }

    public void addPartTimeStaff() {
        String vacancyINumber = vacancyField.getText();
        if (vacancyINumber.isEmpty()) {
            System.out.println("Vacancy Number is Missing. Please enter a valid Vacancy Number.");
            return;
        }
        
        //convert the vacancy number from text to int
        int vacancyNumber = Integer.parseInt(vacancyINumber);

        //check the database for if the vacancy number is in use
        if (database.vacancyNumberExists(vacancyNumber)) {
            System.out.println("This Vacancy Number is already in use. Please select another."); 
            return;
        }
        

        //check the user input in the designation box
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

        //get the shared staff details 
        String designation = "Part-Time";
        String staffName = nameField.getText();
        String joiningDate = dateField.getText();
        String jobType = (String) jobTypeComboBox.getSelectedItem();
        String qualification = qualificationField.getText();
        String appointedBy = appointedByField.getText();
        boolean joined = true; 

        //check wh field
        String workingHoursText = WHField.getText();
        if (workingHoursText.isEmpty()) {
            System.out.println("Please enter working hours."); 
            return;
        }

        //convert wh to int
        int workingHours = Integer.parseInt(workingHoursText);

        String wagesPerHourText = HourlyRateField.getText();
        if (wagesPerHourText.isEmpty()) {
            System.out.println("Please enter wages per hour.");
            return;
        }

        //convert wph to double
        double wagesPerHour = Double.parseDouble(wagesPerHourText);
        String shifts = (String) shiftTypeComboBox.getSelectedItem();//get the selected shift

        //create a PartTimeStaffHire object to store in the database
        PartTimeStaffHire newPartTimeStaff = new PartTimeStaffHire(vacancyNumber, workingHours, wagesPerHour, shifts, 
                false, designation, jobType, appointedBy, staffName, joiningDate, qualification, joined);

        database.addStaff(newPartTimeStaff);//add to database
        System.out.println("Part-Time Staff Added Successfully!");
    }

    public void addSalary() {
        String displayNumberText = displayNumberField.getText();//check vacancy number
        if (displayNumberText.isEmpty()) {
            System.out.println("Please enter a display number.");
            return;
        }

        String newSalaryText = JOptionPane.showInputDialog(frame, "Enter the new salary:");//pop up dialog box for the user to enter a new salary
        if (newSalaryText == null || newSalaryText.trim().isEmpty()) {
            return; //return if text is empty
        }

        try {
            int vacancyNumber = Integer.parseInt(displayNumberText);//convert to int
            int newSalary = Integer.parseInt(newSalaryText);

            //call changeStaffSalary in database to update the salary
            database.changeStaffSalary(vacancyNumber, newSalary);

            System.out.println("Salary changed successfully!");
        }//catch for if the user enters something other than a number
        catch (NumberFormatException e) {
            System.out.println("Please enter valid numbers only."); 
        }
    }

    public void addWorkingShifts() {
        String displayNumberText = displayNumberField.getText();//check vacancy
        if (displayNumberText.isEmpty()) {
            System.out.println("Please enter an active display number.");
            return;//return if empty
        }

        String[] shiftTypes = {"Morning", "Day", "Afternoon"};
        JComboBox<String> shiftTypeComboBox = new JComboBox<>(shiftTypes);

        //JOptionPane to enable a new shift to be chosen, using the same combobox as in the GUI
        int result = JOptionPane.showConfirmDialog(frame, shiftTypeComboBox, "Select Shift", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String selectedShift = (String) shiftTypeComboBox.getSelectedItem();

            try {
                int vacancyNumber = Integer.parseInt(displayNumberText);
                database.changeWorkingShift(vacancyNumber, selectedShift);//call method in database to change the shift
                System.out.println("Shift updated successfully!");
            } catch (NumberFormatException e) {//error if not a valid vacancy number
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

        int vacancyNumber = Integer.parseInt(displayNumberText);//convert to int

        boolean removed = database.removeStaffByVacancyNumber(vacancyNumber);//calling remove staff method from StaffHire

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