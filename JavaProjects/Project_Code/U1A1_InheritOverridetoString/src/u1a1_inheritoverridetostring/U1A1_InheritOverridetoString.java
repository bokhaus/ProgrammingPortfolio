/*
 * This program is designed to extend classes and 
 * override the superclass with two subclass methods
 * and print.
 *
 *@author Bok
 */

package u1a1_inheritoverridetostring;

public class U1A1_InheritOverridetoString {

    public static void main(String[] args) {
        
        //Teachers copy print text string
        System.out.println("Teachers Copy");
        
        // Print toStrings
        System.out.printf(
                "Java class name = 'Course' Course Code = %s\n", 
                new Course().toString());
	System.out.printf(
                "Java class name = 'FlexPathCourse' Course Code = %s\n", 
                new FlexPathCourse().toString());
        System.out.printf(
                "Java class name = 'GuidedPathCourse' Course Code = %s\n", 
                new GuidedPathCourse().toString());
    }
}
//Superclass created
class Course {
  public static String code = "TBA";
  public static String creditHours;
  public static String title;
 
  public String toString() {
    return code;
  }
}
//subclass created named FlexPathCourse
class FlexPathCourse extends Course {
  public static String optionalResources;
  public static String code = "IT2230";

  @Override
  public String toString() {
    return code;
  }
}
//subclass created named GuidedPathCourse
class GuidedPathCourse extends Course {
  public static int duration;
  public static String code = "ITFP4789";
  public static String requiredResource;

  @Override
  public String toString() {
    return code;
  }  
   
}


    