/*
 * High and low test code
 */
package findhighandlowtestcode;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class FindHighAndLowTestCode {
    

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args){
       Scanner input = new Scanner(System.in);
       int number;
       int high = Integer.MIN_VALUE;
       int low = Integer.MAX_VALUE;
        
       System.out.println("Assignment Copy");
       System.out.print("Enter five integers : "); 

             
       for (int x = 0; x<5; x++){
           number = input.nextInt(); 
           
            if (x == 0 || number > high){ 
                high = number;
                }  
            if (x == 0 || number < low){ 
                low = number;  
                }   
            
           }                 
                  System.out.println("Highest integer is " + high);
                  System.out.println("Lowest integer is " + low);
    }
}