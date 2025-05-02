/**
* @author Timothy Andrla
*@version 1.0
*This class creates a graphical user interface for managing the staff information.
*it allows you to enter and submit data for the full-time staff members and the part-time staff members. As well
*as other functionality such as: Submitting new records, viewing all entries, search by vacancy number, update salary, update shifts
*terminate part-time staff and clearing the form after submitting.
*/


import javax.swing.*; /** imports all classes from swing package, this is used to build the GUI */
import java.awt.*;    /** imports all classes from Abstract Window Toolkit (AWT) used with swing to control layout manager and some other things */
import java.awt.event.*; /** imports event handling classes, used to respond to user actions (e. g. clicking a button) */
import java.util.ArrayList; /** imports ArrayList to store staff entries as they are added */

public class Recruitment_System_GUI {
    /** List to store all entries made into system */
    static ArrayList<StaffHire> staffInformationList = new ArrayList<>();

    public static void main(String[] args) {
        /** this creates the main window */
        JFrame frame = new JFrame("Staff Recruitment System");
        frame.setSize(550, 700); /** sets the width and height of the main window in pixels */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /** tells window to close when user clicks 'x' in the top corner */
        frame.setLayout(new BorderLayout()); /** creates object borderlayout which sorts how windows are arranged in the window*/
        
        /** creates the panel for inputting */
        JPanel formPanel = new JPanel(); /** makes a new panel in which you can organise GUI components*/
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS)); /** makes a boxlayout that stacks compnonents vertically */
        frame.add(formPanel, BorderLayout.NORTH); /** adds panel to top of main window *./

        /** Add the common fields for both staff types */
        JTextField vacancyNumberField = addField(formPanel, "Vacancy Number:"); /** JTextField lets the user type in a single line of text */
        JTextField designationField = addField(formPanel, "Designation:");
        JTextField jobTypeField = addField(formPanel, "Job Type:");
        JTextField staffNameField = addField(formPanel, "Staff Name:");
        JTextField joiningDateField = addField(formPanel, "Joining Date:");
        JTextField qualificationField = addField(formPanel, "Qualification:");
        JTextField appointedByField = addField(formPanel, "Appointed By:");
        JTextField joinedField = addField(formPanel, "Joined (true/false):");

        /** Makes a dropdown menu to select employee type 
        * full-time or part-time */
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); /** makes a new panel organises its layout horizontally */
        JLabel typeLabel = new JLabel("Employee Type:");
        JComboBox<String> typeBox = new JComboBox<>(new String[] {"Full-Time", "Part-Time"}); /** creates the drop down box which lets user select between full-time or part-time */
        typePanel.add(typeLabel);
        typePanel.add(typeBox);
        formPanel.add(typePanel);

        /** these are the fields only relevent to full-time
        * also creates the panels and interfaces for full-time options */
        JPanel fullTimePanel = new JPanel(new GridLayout(2, 2));
        JTextField salaryField = new JTextField();
        JTextField weeklyHoursField = new JTextField();
        fullTimePanel.add(new JLabel("Salary:"));
        fullTimePanel.add(salaryField);
        fullTimePanel.add(new JLabel("Weekly/Fractional Hours:"));
        fullTimePanel.add(weeklyHoursField);
        formPanel.add(fullTimePanel);

        /** these are the fields only relevent to part-time
        *creates the panels and interface for part-time options */
        JPanel partTimePanel = new JPanel(new GridLayout(3, 2));
        JTextField workingHoursField = new JTextField();
        JTextField wagesField = new JTextField();
        JTextField shiftsField = new JTextField();
        partTimePanel.add(new JLabel("Working Hours:"));
        partTimePanel.add(workingHoursField);
        partTimePanel.add(new JLabel("Wages per Hour:"));
        partTimePanel.add(wagesField);
        partTimePanel.add(new JLabel("Shifts:"));
        partTimePanel.add(shiftsField);
        formPanel.add(partTimePanel);
        
        /** sets the default view to the full time inputs*/
        fullTimePanel.setVisible(true);
        partTimePanel.setVisible(false);
        

        /** swap the fields based on the selection for type*/
        typeBox.addActionListener(e -> {
            boolean isFullTime = typeBox.getSelectedItem().equals("Full-Time");
            fullTimePanel.setVisible(isFullTime);
            partTimePanel.setVisible(!isFullTime);
            frame.revalidate();
            frame.repaint();
        
        });
        
        /** the following code is adding all the buttons to the gui*/
        JButton submitButton = new JButton("Submit");
        formPanel.add(submitButton);
        JButton terminateButton = new JButton("Terminate Part-Time Staff");
        formPanel.add(terminateButton);
        JButton viewAllButton = new JButton("View All Entries");
        formPanel.add(viewAllButton);
        JButton searchButton = new JButton("Display Staff Info");
        formPanel.add(searchButton);
        JButton updateSalaryButton = new JButton("Update Full-time Salary");
        formPanel.add(updateSalaryButton);
        JButton updateShiftsButton = new JButton("Update Part-Time Shifts");
        formPanel.add(updateShiftsButton);
        JButton clearButton = new JButton("Clear Form");
        formPanel.add(clearButton);
        
        /** makes an area to show output results*/
        JTextArea outputArea = new JTextArea(10, 45);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        /** creates and stores a new staff entry*/
        submitButton.addActionListener(e -> {
            try {
                /** gather all the common fields */
                int vacancyNumber = Integer.parseInt(vacancyNumberField.getText());
                String designation = designationField.getText();
                String jobType = jobTypeField.getText();
                String staffName = staffNameField.getText();
                String joiningDate = joiningDateField.getText();
                String qualification = qualificationField.getText();
                String appointedBy = appointedByField.getText();
                boolean joined = Boolean.parseBoolean(joinedField.getText());

                StaffHire employee;
                /** makes a full-time or part-time staff object based on what the user chose */
                if (typeBox.getSelectedItem().equals("Full-Time")) { /** what should happen if the user chooses full-time */
                    double salary = Double.parseDouble(salaryField.getText()); /** converts input into a double and stores it*/
                    int weeklyHours = Integer.parseInt(weeklyHoursField.getText()); /** converts input into an int and stores it*/

                    employee = new FullTimeStaffHire( /** stores new object in variable called employee and calls the contructor of FullTimeStaffHire*/
                        vacancyNumber, designation, jobType, staffName,
                        joiningDate, qualification, appointedBy, joined,
                        salary, weeklyHours
                    );
                } else {
                    int workingHours = Integer.parseInt(workingHoursField.getText()); /** coverts input to int and stores it*/
                    double wages = Double.parseDouble(wagesField.getText()); /** converts input to double and stores it*/
                    String shifts = shiftsField.getText(); /** does not need to converts as already a string, just gets stores */

                    employee = new PartTimeStaffHire( /** stores new object in variable called employee and calls PartTimeStaffHire contructor */
                        vacancyNumber, designation, jobType, staffName,
                        joiningDate, qualification, appointedBy, joined,
                        workingHours, wages, shifts, false // terminated = false
                    );
                }
                /** adds it to the staff list*/
                staffInformationList.add(employee);
                JTextArea tempOut = new JTextArea();
                employee.display();
                outputArea.append(getEmployeeDetails(employee));
                outputArea.append("----------------------------------------\n"); /** this adds a line to seperate staff info in the text area */

            } catch (Exception ex) { /** this handles any unexpected errors during the execution*/
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage()); /** retrieves what when wrong and tells the user in a popup */
            }
        });
        
        terminateButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter vacancy number of Part-Time staff to terminate");
            
            try {
                int targetVacancy = Integer.parseInt(input); 
                boolean found = false;
                
                for (StaffHire emp : staffInformationList) {
                    if (emp instanceof PartTimeStaffHire) { /** this if block searches through all staff in the list and compares their vacancy number to the user input and if it matches, terminates them */
                        PartTimeStaffHire pte = (PartTimeStaffHire) emp;
                        if (pte.getvacancyNumber() == targetVacancy) {
                            pte.terminateContract();
                            outputArea.append("\nTermination Complete:\n ");
                            outputArea.append(getEmployeeDetails(pte));
                            outputArea.append("----------------------------------------\n");
                            found = true;
                            break;
                        }
                    }
                }
            if (!found) { 
                JOptionPane.showMessageDialog(frame, "No Part-Time staff found with that vacancy number."); /** if the vacancy number doesn't match any in the list it will tell the user */
            }
        }catch (NumberFormatException ex){ /** if an invalid number is entered inform the user */
            JOptionPane.showMessageDialog(frame, "Invalid number entered.");
        }
        });
        /** for the view all entries button */
        viewAllButton.addActionListener(e -> {
            JFrame viewFrame = new JFrame("All Staff Entries");
            viewFrame.setSize(500, 600);
            viewFrame.setLayout(new BorderLayout());
            
            JTextArea viewArea = new JTextArea();
            viewArea.setEditable(false);
            JScrollPane viewscrollPane = new JScrollPane(viewArea);
            viewFrame.add(viewscrollPane, BorderLayout.CENTER);
            
            if (staffInformationList.isEmpty()){ /** error checked in case no entries have been made yet */
                viewArea.setText("No entries to display.");
            } else {
                for (StaffHire emp : staffInformationList) {
                    viewArea.append(getEmployeeDetails(emp));
                    viewArea.append("----------------------------------------\n");
                }
            }
            viewFrame.setVisible(true);
        });
        /** search for an employee by vacancy number */
        searchButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter vacancy number to search: ");
            
            try {
                int searchVacancy = Integer.parseInt(input);
                boolean found = false;
                
                for (StaffHire emp : staffInformationList) {
                    if (emp.getvacancyNumber() == searchVacancy) {
                        JTextArea resultArea = new JTextArea(getEmployeeDetails(emp));
                        resultArea.setEditable(false);
                        JScrollPane resultScroll = new JScrollPane(resultArea);
                        resultScroll.setPreferredSize(new Dimension(400, 300));
                        
                        JOptionPane.showMessageDialog(frame, resultScroll, "Staff Information: ", JOptionPane.INFORMATION_MESSAGE);
                        found = true;
                        break;
                    }
                }
            if (!found) {
                JOptionPane.showMessageDialog(frame, "No matching staff member found.");
            }     
        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
        }
        });
        /** button to update salary for a full-time staff member */
        updateSalaryButton.addActionListener(e -> {
            try {
                String vacInput = JOptionPane.showInputDialog(frame, "Enter Vacancy Number:");
                int vacancyNumber = Integer.parseInt(vacInput);
                String staffName = JOptionPane.showInputDialog(frame, "Enter Staff Name:");
                boolean found = false;
                
                for(StaffHire emp : staffInformationList){ /** searches list of full-time employees for matching vacancy number and name, will update the salary to inputted information if found */
                    if(emp instanceof FullTimeStaffHire){
                        FullTimeStaffHire fte = (FullTimeStaffHire) emp;
                        if(fte.getvacancyNumber() == vacancyNumber && fte.getstaffName().equalsIgnoreCase(staffName)){
                            String salaryInput = JOptionPane.showInputDialog(frame, "Enter New Salary:");
                            double newSalary = Double.parseDouble(salaryInput);
                            
                            fte.setsalary(newSalary);
                            JOptionPane.showMessageDialog(frame, "Salary Updated Successfully!");
                            found = true;
                            
                            JTextArea resultArea = new JTextArea(getEmployeeDetails(fte));
                            resultArea.setEditable(false);
                            JOptionPane.showMessageDialog(frame, new JScrollPane(resultArea), "Updated Staff Detials", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(frame, "Full-Time staff not found with matching vacancy number and name.");
                }
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter numbers where required.");   
            }
        });
        /** button to update shifts for part-time staff member */
        updateShiftsButton.addActionListener(e -> {
            try {
                String vacInput = JOptionPane.showInputDialog(frame, "Enter Vacancy Number:");
                int vacancyNumber = Integer.parseInt(vacInput);
                String staffName = JOptionPane.showInputDialog(frame, "Enter Staff Name:");
                boolean found = false;
                
                for(StaffHire emp : staffInformationList) {
                    if(emp instanceof PartTimeStaffHire){
                        PartTimeStaffHire pte = (PartTimeStaffHire) emp;
                        if(pte.getvacancyNumber() == vacancyNumber && pte.getstaffName().equalsIgnoreCase(staffName)) {
                            String newShift = JOptionPane.showInputDialog(frame, "Enter new shift:");
                            
                            pte.updateshifts(newShift);
                            JOptionPane.showMessageDialog(frame, "Shifts updated successfully!");
                            found = true;
                            
                            JTextArea resultArea = new JTextArea(getEmployeeDetails(pte));
                            resultArea.setEditable(false);
                            JOptionPane.showInputDialog(frame, new JScrollPane(resultArea), "Updated staff details", JOptionPane.INFORMATION_MESSAGE);
                            break;
                         }
                   }
               }
               if (!found) {
                JOptionPane.showMessageDialog(frame, "Part-Time staff not found with matching vacancy number and name.");
               }
           }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter numbers where required.");
           }
        });
        /** button to clear all text from the boxes */
        clearButton.addActionListener(e -> {
            vacancyNumberField.setText("");
            designationField.setText("");
            jobTypeField.setText("");
            staffNameField.setText("");
            joiningDateField.setText("");
            qualificationField.setText("");
            appointedByField.setText("");
            joinedField.setText("");
            
            salaryField.setText("");
            weeklyHoursField.setText("");
            workingHoursField.setText("");
            wagesField.setText("");
            shiftsField.setText("");
            
            typeBox.setSelectedIndex(0);
            
            fullTimePanel.setVisible(true);
            partTimePanel.setVisible(false);
        
        });
    
        frame.setVisible(true);
    }
    /** this method creates a labeled text input field and adds it to a panel. 
     * you can can just call this method every time you need a text field with a label to reduce repitition in the code
     */

        private static JTextField addField(JPanel panel, String label) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel jLabel = new JLabel(label);
        JTextField field = new JTextField(20);
        fieldPanel.add(jLabel);
        fieldPanel.add(field);
        panel.add(fieldPanel);
        return field;
    }
    /** this method formats and returns staff information as a string
     * it takes a StaffHire object(could be FullTimeStaffHire or PartTimeStaffHire) and formats it, will append relevent fields depends on
     * full-time or part-time status
     */

    private static String getEmployeeDetails(StaffHire emp) {
        StringBuilder details = new StringBuilder();

        if (emp instanceof FullTimeStaffHire) {
            FullTimeStaffHire fte = (FullTimeStaffHire) emp;
            details.append("Full-Time Employee\n");
            details.append("Vacancy Number: ").append(fte.getvacancyNumber()).append("\n");
            details.append("Designation: ").append(fte.getdesignationType()).append("\n");
            details.append("Job Type: ").append(fte.getjobType()).append("\n");
            details.append("Name: ").append(fte.getstaffName()).append("\n");
            details.append("Joining Date: ").append(fte.getjoiningDate()).append("\n");
            details.append("Qualification: ").append(fte.getQualification()).append("\n");
            details.append("Appointed By: ").append(fte.getappointedBy()).append("\n");
            details.append("Joined: ").append(fte.isjoined()).append("\n");
            details.append("Salary: ").append(fte.getsalary()).append("\n");
            details.append("Weekly Hours: ").append(fte.getweekfracHours()).append("\n");
        } else if (emp instanceof PartTimeStaffHire) {
            PartTimeStaffHire pte = (PartTimeStaffHire) emp;
            details.append("Part-Time Employee\n");
            details.append("Vacancy Number: ").append(pte.getvacancyNumber()).append("\n");
            details.append("Designation: ").append(pte.getdesignationType()).append("\n");
            details.append("Job Type: ").append(pte.getjobType()).append("\n");
            details.append("Name: ").append(pte.getstaffName()).append("\n");
            details.append("Joining Date: ").append(pte.getjoiningDate()).append("\n");
            details.append("Qualification: ").append(pte.getQualification()).append("\n");
            details.append("Appointed By: ").append(pte.getappointedBy()).append("\n");
            details.append("Joined: ").append(pte.isjoined()).append("\n");
            details.append("Hours Worked: ").append(pte.getworkingHour()).append("\n");
            details.append("Wages per Hour: ").append(pte.getwagesperHour()).append("\n");
            details.append("Shifts: ").append(pte.getshifts()).append("\n");
            details.append("Terminated: ").append(pte.isterminated()).append("\n");
        }

        return details.toString();
    }
}

        
        
        
    
        
        
    
            
       
    
        
        

    
    
    
    
    
    
    
    

