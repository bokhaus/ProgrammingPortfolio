/*
 * This program is designed to determine the number of 
 * occurrences of each number in an array.
 * @author Bok
 */
package u7a1_numbeofoccurrinsevenints;

import java.util.Scanner;

public class U7A1_NumbeOfOccurrInSevenInts {

    
    public static void main(String[] args) {
         Scanner input = new Scanner(System.in); //install scanner
         
        //Declare final int array for 7 variables
        //Declare int variable max
         final int[] NUMBERS = new int[7];  
         int max = 0;
         
         //Print String text
         System.out.println("Assignment Copy");
         System.out.print("Enter seven numbers: ");
         
         //Generate array with user input
		for (int i = 0;i<NUMBERS.length; i++){
            NUMBERS[i]=input.nextInt();
        }//end of loop
        
        //loop to determine max value of array
		for (int i = 0;i<NUMBERS.length; i++){
            if (NUMBERS[i]>max){
                max = NUMBERS[i];
            }
        }//end of loop
        
        int[]count = new int[max+1];//create count array
        
        //Loop for creating count array 
        for (int i = 0;i<NUMBERS.length; i++){
            count[(int)(NUMBERS[i])]++; 
        }//end of loop
        
        //count occurrences in array index and print to console
        for(int i=0; i<count.length; i++){
            if(count[i]>0){
                System.out.println("Number "+ (int)i + " occurs " + count[i]+ " times.");
            }
        }//end of loop
        
    }
    
}
