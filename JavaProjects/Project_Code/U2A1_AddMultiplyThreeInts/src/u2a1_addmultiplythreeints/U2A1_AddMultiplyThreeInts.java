/*
 * The purpose of this program is to add and multiply three integers 
 * from user inputs
 * and print the sum and product to the console
 */
package u2a1_addmultiplythreeints;

import java.util.Scanner; //imported scanner utility

/**
 *
 * @author Bok
 */
public class U2A1_AddMultiplyThreeInts {

   
    public static void main(String[] args) {
       
         // Created Scanner
        Scanner input = new Scanner (System.in);
     
        // teacher's copy statement per assignment
        System.out.println("Teacher's Copy "); 
        
        // prompt the user for input value 1 and assign variable
        System.out.print("Enter the first integer value: ");
        int userInput1 = input.nextInt();
        
        // prompt the user for input value 2 and assign variable
        System.out.print("Enter the second integer value: ");
        int userInput2 = input.nextInt();   
        
        // prompt the user for input value 3 and assign variable
        System.out.print("Enter the third integer value: ");
        int userInput3 = input.nextInt();
        
        //  declare variable for sum calculation
        int sum = userInput1 + userInput2 + userInput3;
        
        // declare variable for product calculation
        int product = userInput1 * userInput2 * userInput3;
        
        //Print sum of three integers
        System.out.println("The sum of the three integers = " + sum);
        
        //Print product of three integers
        System.out.println("The product of the three integers = " + product );
    }
    
}
