/*
 * This program is designed to calculate area of a circle 
 *with a given radius
 */

 import java.util.Scanner;
public class ComputerAreaWithConsoleInput {


    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner (System.in);
        
        // prompt the user to use radius
        System.out.print("Enter a number for radius ");
        double radius =input.nextDouble();
        
        //compute area of circle
        double area = radius * radius *3.14159;
        
        //display result
        System.out.println("The area for the circle of a radius " + 
                radius + " is " + area);
    }
}