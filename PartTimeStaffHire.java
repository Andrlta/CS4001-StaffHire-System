
/**
@author Timothy Andrla
@version 1.0
*This is a subclass which extends the StaffHire super class and additional attributes unique to the 
*PartTimeStaffHire class. These being Working Hours, Wages per Hour, Shifts and a terminated status. This contains
*a display method to show this information using the inhertied attributes from the super class and then the
*unique attributes from this class.
*/

/** this makes PartTimeStaffHire a subclass of StaffHire */
public class PartTimeStaffHire extends StaffHire{
    
    private int workingHour;
    private double wagesperHour;
    private String shifts;
    private boolean terminated;
    
    /** constructor for an object, its inheriting the attributes from StaffHire and then including the PartTime specific ones */
    public PartTimeStaffHire(int vacancyNumber, String designationType, String jobType, String staffName, String joiningDate, String Qualification, String appointedBy, boolean joined, int workingHour, double wagesperHour, String shifts, boolean terminated){
        super(vacancyNumber, designationType, jobType, staffName, joiningDate, Qualification, appointedBy, joined);
        this.workingHour = workingHour;
        this.wagesperHour = wagesperHour;
        this.shifts = shifts;
        this.terminated = terminated;
    }
    /** getters and setters for the attributes */
    public int getworkingHour(){
        return workingHour;
    }
    public void setworkingHour(int workingHour){
        this.workingHour = workingHour;

    }
    
    public double getwagesperHour(){
        return wagesperHour;
    }
    public void setwagesperHour(double wagesperHour){
        this.wagesperHour = wagesperHour;
    }
    
    public String getshifts(){
        return shifts;
    }
    public void setshifts(String shifts){
        this.shifts = shifts;
    }
    
    public boolean isterminated(){
        return terminated;
    }
    public void setterminated(boolean terminated){
        this.terminated = terminated;
    }
    
    public void updateshifts(String newshifts){
        this.shifts = newshifts;
        System.out.println("The new shifts are: " + shifts);
    }
    /** method for displaying the information about the object
     * with the part-time information as well
     */
    public void display(){
        super.display();
        System.out.println("Working Hours are: " + workingHour);
        System.out.println("Wage per Hour is: " + wagesperHour);
        System.out.println("Shifts are as follows :" + shifts);
        System.out.println("Terminated status: " + terminated);
        System.out.println("Income per day: " +(workingHour*wagesperHour));
        
    }
    
    /** this method terminates the contract of a part-time staff member
     * If they are already terminated it tells the user
     * Else it will clear the details and mark them as terminated
     */
    public void terminateContract(){
        if (terminated) {
            System.out.println("This staff member has been terminated already");
        }else{
            setstaffName("");
            setjoiningDate("");
            setQualification("");
            setappointedBy("");
            setjoined(false);
            setterminated(true);
            System.out.println("This staff member has been successfully terminated.");
        }
    }
    
}
            

        
        




    

