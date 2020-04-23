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
public class U9A1_DefineClassInstantiateObj {

    public static void main(String[] args) {
        
        // create an array of seven course objects
        Course[] directory = new Course[7];
        /* Constructor calls to create 7 Course objects and store in the array
         * To construct an object from a class, invoke a constructor of the class using the new operator as follows:
         * new ClassName(arguments);
        */
        directory[0] = new Course("IT1006",6);
        directory[1] = new Course("IT4782",3);
        directory[2] = new Course("IT4789",3);
        directory[3] = new Course("IT4079",6);
        directory[4] = new Course("IT2230",3);
        directory[5] = new Course("IT3345",3);
        directory[6] = new Course("IT2249",6);
        
        //print Strings of text
        System.out.println("Assignment Copy");
        
        System.out.println("Course Objects each have a code (e.g. IT1006) and "
                + "credit hours (e.g. 6)");
        System.out.println("The number inside the [] is the display order number");
        System.out.println("The number inside the () is the credit hours for the course");
        System.out.println();//print blank line to double space the text from course info
        
        
        int courseCount = 1; //count courses
        
        /* for loop to go through the objects in the array. In each iteration of
        the loop, the variable holds the current item read from the array.
        */
        for(Course cd : directory){
            //Print array in order based on Course class objects
            System.out.println("["+ courseCount++ +"]" + cd.getCode()+ "(" + cd.getCreditHours()+")");
        }
        
    }
    
}
