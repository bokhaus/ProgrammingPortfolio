/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ALT_u5a1_findhighlowoffiveint;

import java.util.Scanner;

public class ALT_U5A1_FindHighLowOfFiveInt {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int number;
        int high = Integer.MIN_VALUE;
        int low = Integer.MAX_VALUE;
        
        System.out.println("Assignment Copy");
        System.out.print("Enter five integers : ");
        
        for (int i = 0; i<5; i++){
            number = input.nextInt();
            if (i == 0 || number > high){
                high = number;
            }  
            if (i == 0 || number < low){ 
                low = number;  
            }   
        }             
        System.out.println("Highest integer is " + high);
        System.out.println("Lowest integer is " + low);
    }
}
