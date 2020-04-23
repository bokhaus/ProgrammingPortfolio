/*
 * This class is created to define a class, instantiates the class into 
 * a number of objects, and prints out the attributes of these objects 
 * in a specific way. 
 */
package u9a1_defineclassinstantiateobj;

/**
 *
 * @author Bok
 */
public class Course {
    /*Attributes are always private.
     * create object reference variables
     * Objects are accessed via the object’s reference variables, 
     * which contain references to the objects. 
     */ 
    private String code;
    private int creditHours;
    
    //Constructor to create a course object with corresponding data
    public Course(String code, int creditHours) {
        this.code = code;
        this.creditHours = creditHours;
    }
    
    String getCode(){ //get the course code
        return code;
    }
   
    int getCreditHours(){ //get the credit hours
        return creditHours;
    }
   
    

}

