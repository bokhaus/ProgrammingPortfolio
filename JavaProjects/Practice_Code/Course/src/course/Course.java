/*
 * Create a class called Course
 */
package course;

/**
 *
 * @author Bok
 */
public class Course {
    private String code;
    private int creditHours;
          
    public Course(){
        creditHours = 0;
    }
    Course(String newCourseCode, int newCreditHours) {
        this.code = newCourseCode;
        this.creditHours = newCreditHours;
    }
    String getCode(){
        return code;
    }
   
    int getCreditHours(){
        return creditHours;
    }
    public void setCreditHours(int newCreditHours){
        this.creditHours = newCreditHours;
    }
     public void setCode(String newCode){
        code = newCode;
    }
    

}
