/*
 * Debugged Copy
 */
package u3a1_debugfixifstmts;

import java.util.Scanner;

public class U3A1_DebugFixIFStmts {

    
    public static void main(String[] args) {
        // application logic 
        
        System.out.println("Teacher's Copy");
                
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter three integers: ");
        int firstChoice = input.nextInt();
        int secondChoice = input.nextInt();
        int thirdChoice = input.nextInt();
        
        // applied curly brackets to all condition_statements.
        if (firstChoice == 0) {
            System.out.println("State of choices: \n" +
                    "no choices made yet");
        }
        
        // 1. changed_if_ statement to_else if_ statement.
        // specifies a new condition to test, if the first statement is false.
        // this form creates multi-way if-else statement.
        else if (secondChoice == 0) {
            System.out.println("State of choices: \n" +
                    "user made first choice (" + firstChoice + ")\n" +
                    "number of choices = 1");
        }
        
        // 2. added second equal sign to make a relational operator
        else if (thirdChoice == 0) {
            System.out.println("State of choices: \n" +
                    "user made first choice (" + firstChoice + ")\n" +
                    "user made second choice (" + secondChoice + ")\n" +
                    "number of choices = 2");
        }
        
        // 3. added else statement to match_if statement.
        // 4. added semicolon to complete statement.
        else {
            System.out.println("State of choices: \n" +
                    "user made first choice (" + firstChoice + ")\n" +
                    "user made second choice (" + secondChoice + ")\n" +
                    "user made third choice (" + thirdChoice + ")\n" +
                    "number of choices = 3");
        }
    }
    
}
