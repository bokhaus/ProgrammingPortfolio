/*
 * The purpose of this program is to 
 * find the highest and lowest integers in a string of 
 * fiver characters.
 * @author Bok
 */
package u5a1_findhighlowoffiveints;
//import Scanner
import java.util.Scanner;

public class U5A1_FindHighLowOfFiveInts {
    public static void main(String[] args) {
        //Create Scanner
        Scanner input = new Scanner(System.in);
        
        //Print Assignment Text
        System.out.println("Assignment Copy");
        
        //Print request for input
        System.out.print("Enter five integers : "); 

        //Initialized and Declare Variables  
        int number;
        int high = Integer.MIN_VALUE;
        int low = Integer.MAX_VALUE;
        
        //Begin FOR loop to compare numbers for high and low
        for (int i = 0; i<5; i++){ 
            number = input.nextInt();
            if (number > high){ 
                high = number; 
            }
            if (number < low){ 
                low = number;  
            } 
        }  
        //End FOR loop and Print High and Low Integers
        System.out.println("Highest integer is " + high);
        System.out.println("Lowest integer is " + low);
    }
 
}
    
