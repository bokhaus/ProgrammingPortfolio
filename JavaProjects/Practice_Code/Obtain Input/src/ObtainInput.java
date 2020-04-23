

import java.util.Scanner;
public class ObtainInput {

   
    public static void main(String[] args) {
        // Select Scanner
        Scanner input = new Scanner (System.in);
        
        // prompt the user to use radius
        System.out.print("Enter a number for radius ");
        double radius =input.nextDouble();
        
        //compute area of circle
        double area = radius * radius *3.14159;
        
        //display result
        System.out.println("The are for the circle of a radius " + 
                radius + " is " + area);
        
    }
    
}
