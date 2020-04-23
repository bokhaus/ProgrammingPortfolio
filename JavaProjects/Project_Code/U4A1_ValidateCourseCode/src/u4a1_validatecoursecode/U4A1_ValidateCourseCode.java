/*
 * This program is designed to validate IT course codes
 *@author Bok
 */
package u4a1_validatecoursecode;

import java.util.Scanner;

public class U4A1_ValidateCourseCode {

    public static void main(String[] args) {
        
       
        System.out.println("Assignment Copy");
        
        
        //Scanner for reading console input
        Scanner input = new Scanner(System.in);
        String code; //Variable to hold course code entered by user.
        boolean valid = true; //Overall is valid /is not valid. Starts at true. 
        String message = ""; //Holds error message details. Starts empty.
        System.out.print("Enter a course code to validate (e.g. IT4782): ");
        
        code = input.nextLine();
        //Test length of string first to verify correct amount of characters
        if(code.length() == 6) {
          
           // Length verified
           //Check first character at position 0 - first character.
           if(Character.toUpperCase(code.charAt(0)) != 'I'){
               valid = false;
               message += "First character is not an I or an i. \n";
            }
           //Check second character
           if(Character.toUpperCase(code.charAt(1)) !='T'){
               valid = false;
               message += "Second character is not a T or an t. \n";
            }
            //Check third character
            if(!Character.isDigit(code.charAt(2))){
                valid = false;
                message += "Third character is not a digit. \n";
            }
            //Check fourth character
            if(!Character.isDigit(code.charAt(3))){
                valid = false;
                message += "Fourth character is not a digit. \n";
            }
            //Check fifth character
            if(!Character.isDigit(code.charAt(4))){
                valid = false;
                message += "Fifth character is not a digit. \n";
            }
            //Check sixth character
            if(!Character.isDigit(code.charAt(5))){
                valid = false;
                message += "Sixth character is not a digit. \n";
            }  
        
        }
        //Wrong Length
        else{
            valid = false;
            message = "Course code: "+code + " is not valid.";
        }
        //Overall validity message
        if(valid){
            System.out.println("Course code: " + code + " is valid.");
        }
        //end of main if statement
        else{ //else means not all 6 caharacter are in length.
            System.out.println("Course code: " + code + " is not valid");
            System.out.println(message);
        }
    } 
}
