
package torontopostalcode;

import java.util.Scanner;
/**
* This program validates that a Canadian postal code has the correct form for Toronto:
*
* M1A 1A1 Canadian postal codes are 7 characters, counting the space in the middle.
* Toronto postal codes start with M
* 1st char must be capital M. 2nd is a digit. 3rd is a letter. 4th is a space.
* 5th is a digit. 6th is a letter. 7th is a digit.
*
* @author bberkland
*/
public class TorontoPostalCode {
 public static void main(String[] args) {
     
 // Scanner for reading console input
 Scanner input = new Scanner(System.in);
 
 String code; // Variable to hold postal code entered by user
 boolean valid = true; // Overall is valid/is not valid. Starts true
 String message = ""; // Holds error message details. Starts empty.
 System.out.print("Enter Toronto Postal Code (M1A 1A1): ");
 
// Read input as String using nextLine()
 code = input.nextLine();

 // Most general characteristic is length - check it first
 if(code.length() == 7) {
     
 // If length is valid check characters in each position
 // code.charAt(0) gets first char from input string
 if(Character.toUpperCase(code.charAt(0)) != 'M') {
 valid = false;
 message += "Firsts character is not M.\n";
 }
 // Check if second char in string is NOT a digit
 // ! is logical NOT
 if(!Character.isDigit(code.charAt(1))) {
 valid = false;
message += "Second character is not a digit.\n";
 }
 // Check if third character in string is NOT a letter
 if(!Character.isLetter(code.charAt(2))) {
 valid = false;
message += "Third character is not a letter.\n";
 }
 // Check if fourth character is NOT a space
 // isSpace() is deprecated so use isSpaceChar() instead
 if(!Character.isSpaceChar(code.charAt(3))) {
 valid = false;
message += "Fourth character is not a space.\n";
 }
 if(!Character.isDigit(code.charAt(4))) {
 valid = false;
 message += "Fifth character is not a digit.\n";
 }
 if(!Character.isLetter(code.charAt(5))) {
     valid = false;
message += "Sixth character is not a letter.\n";
 }
 if(!Character.isDigit(code.charAt(6))) {
 valid = false;
message += "Seventh character is not a digit.\n";
 }
 } // End of if() checking for 7 characters
 else { // else means not 7 characters in length
 valid = false;
 message = code + " is wrong length.";
 }

 // Print that code is valid
 if(valid) {
 System.out.println(code + " is a valid Toronto postal code.");
 }
 else { // Print that code is not valid and print reasons
 System.out.println(code + " is not a valid Toronto postal code.");
 // message contains list of errors separated with new line chars
 System.out.println(message);
 }
 }
}

 