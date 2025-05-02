
/**
* @author Timothy Andrla
* @version 1.0
* This class is the base class that is used to represent the general information about vacancy position
* It includes all the general attributes and keeps track of whether someone has joined the position.
* It contains getter and setter methods for the attributes and the display method to print the details.
*/

public class StaffHire{

    /**Declaring the attributes and their data types*/
    private int vacancyNumber;
    private String designationType;
    private String jobType;
    private String staffName;
    private String joiningDate;
    private String Qualification;
    private String appointedBy;
    private boolean joined;
    
    /**Constuctor for a vacancy position*/
    public StaffHire(int vacancyNumber, String designationType, String jobType, String staffName, String joiningDate, String Qualification, String appointedBy, boolean joined){
        this.vacancyNumber = vacancyNumber;
        this.designationType = designationType;
        this.jobType = jobType;
        this.staffName = staffName;
        this.joiningDate = joiningDate;
        this.Qualification = Qualification;
        this.appointedBy = appointedBy;
        this.joined = joined;
    }
    /** This is the method for displaying the details in a easy to read format
     * All it does is print the info, e.g. Vacancy Number and then the attribute */
    public void display(){
        System.out.println("Staff Details:");
        System.out.println("Vacancy Number: " + vacancyNumber);
        System.out.println("Designation Type: " + designationType);
        System.out.println("Job Type: " + jobType);
        System.out.println("Staff Name: " + staffName);
        System.out.println("Joining Date: " + joiningDate);
        System.out.println("Qualification: " + Qualification);
        System.out.println("Appointed By: " + appointedBy);
        System.out.println("Joined: " + joined);
    }
    
    /** Getter and Setters for the attributes */
    public int getvacancyNumber(){
        return vacancyNumber;
    }
    public void setvacancyNumber(int vacancyNumber){
        this.vacancyNumber = vacancyNumber;
    }
    
    public String getdesignationType(){
        return designationType;       
    }
    public void setdesignationType(String designationType){
        this.designationType = designationType;
    }
    
    public String getjobType(){
        return jobType;
    }
    public void setjobType(String jobType){
        this.jobType = jobType;
    }
    
    public String getstaffName(){
        return staffName;
    }
    public void setstaffName(String staffName){
        this.staffName = staffName;
    }
    
    public String getjoiningDate(){
        return joiningDate;
    }
    public void setjoiningDate(String joiningDate){
        this.joiningDate = joiningDate;
    }
    
    public String getQualification(){
        return Qualification;
    }
    public void setQualification(String Qualification){
        this.Qualification = Qualification;
    }
    
    public String getappointedBy(){
        return appointedBy;
    }
    public void setappointedBy(String appointedBy){
        this.appointedBy = appointedBy;
    }
    /** note the boolean is "isjoined" instead of "get" as its a boolean */
    public boolean isjoined(){
        return joined;
    }
    public void setjoined(boolean joined){
        this.joined = joined;
    }
    
}    



    
    
    
 
