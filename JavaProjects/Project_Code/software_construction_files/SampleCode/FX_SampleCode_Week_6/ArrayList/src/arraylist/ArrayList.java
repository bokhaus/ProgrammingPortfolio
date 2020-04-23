/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arraylist;

import java.util.Arrays;

/**
 *
 * @author User
 */
public class ArrayList {
 
    public static void main(String[] args) {
 
        // Create a List.
        System.out.println("Creating the List...");
        int intArray[] = {1,2,3}; 
          
        int cloneArray[] = intArray.clone(); 
        
        String[] weekDayArray = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String[] timeDailyArray = {"00:00", "01:00","02:00","03:00","04:00","05:00","06:00","07:00",
        "08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00",
        "20:00","21:00","22:00","23:00","24:00"}; 
        
          
        // will print false as deep copy is created 
        // for one-dimensional array 
        System.out.println(intArray == cloneArray); 
        System.out.println(Arrays.toString(weekDayArray));
        System.out.println(Arrays.toString(timeDailyArray));
        
        for (int i = 0; i < cloneArray.length; i++) { 
            System.out.print(cloneArray[i]+" "); 
        } 
    } 
} 