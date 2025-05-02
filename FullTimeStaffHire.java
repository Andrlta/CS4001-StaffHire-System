
/**
*@author Timothy Andrla
*@version 1.0
*This is a subclass which extends the StaffHire class and adds additional attributes which are only
*specific to the full-time positions, these being salary and weekly/fractional working hours.
*It also contains a display method which uses the same attributes as the super class and then
*the additional ones as well.
**/

/** this makes the FullTimeStaffHire a subclass of StaffHire */
public class FullTimeStaffHire extends StaffHire{
    /** declaring the two unique attributes to this subclass */
    private double salary;
    private int weekfracHours;
    /** this is a constructor which sets the inherited attributes and the new
    full-time specific fields, the super keyword calls the parent class */
    
    public FullTimeStaffHire(int vacancyNumber, String designationType, String jobType, String staffName, String joiningDate, String Qualification, String appointedBy, boolean joined, double salary,int weekfracHours){
        super(vacancyNumber, designationType, jobType, staffName, joiningDate, Qualification, appointedBy, joined);
        this.salary = salary;
        this.weekfracHours = weekfracHours;
    }
    /** setters and getters for new attributes */
    public double getsalary(){
        return salary;
    }
    public void setsalary(double salary){
        this.salary = salary;
    }
    
    public int getweekfracHours(){
        return weekfracHours;
    }
    public void setweekfracHours(int weekfracHours){
        this.weekfracHours = weekfracHours;
    }
    /** display method for information about the object, calls parent class then adds own attributes */
    public void display(){
        super.display();
        System.out.println("Salary: " + salary);
        System.out.println("Weekly/Fractional Hours: " + weekfracHours);
    }
    /** method to update the salary after the object has been created */
    public void updateSalary(double newSalary){
        if (isjoined()){ /** checks if the staff member has joined */
            this.salary = newSalary; /** if yes update the salary*/
            System.out.println("Salary Updated to: "+ salary);
        } else {
            System.out.println("No staff appointed to set the salary"); /** if no staff is appointed inform user */
        }
        
    }
    
    public void updateweekfracHours(int newHours){
        this.weekfracHours = newHours;
        System.out.println("Hours updated to: " + weekfracHours);
    }
    
}
    


