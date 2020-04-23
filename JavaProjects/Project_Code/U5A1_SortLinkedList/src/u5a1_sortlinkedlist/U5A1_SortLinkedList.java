/*
 * This assignment is to design and code a Java console application that reads 
 * in a varying number of course codes, stores them in a Java LinkedList, 
 * sort them, prints out the size of the LinkedList, and prints out the sorted 
 * course codes one by one.
 *
 * @author Bok
 */
package u5a1_sortlinkedlist;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 
 */
public class U5A1_SortLinkedList {
    
    public static void main(String[] args) {
        
        System.out.println("Teacher's Copy");
        
        // Created LinkedList objects 
        LinkedList<String> list1 = new LinkedList<>();//store COLLECTION 1 course codes

        LinkedList<String> list2 = new LinkedList<>();//store COLLECTION 2 course codes

        LinkedList<String> list3 = new LinkedList<>();//store COLLECTION 3 course codes
        
        System.out.println("Enter 3 collections of cource codes one collection per line:");
        
        // Create Scanner object
        Scanner sc = new Scanner(System.in);
        
        //**Create first string array convert to link list and sort
        String set1=sc.nextLine();//input one line at time for set
        String [] arrStr1 = set1.split(" ");//spliting of set in to String array at each space
        list1.addAll(Arrays.asList(arrStr1)); //Push array to link list
        Collections.sort(list1);//sort the linklist
        
        //Print size of array
        System.out.print("SIZE: " + list1.size());
        System.out.print(" Sorted: ");
        
        //iterate over link list to print the SORTED Course code string
        for(int num=0; num<list1.size(); num++){
            System.out.print(list1.get(num)+" ");
        }
        System.out.println();
        System.out.println();
        
        //**Create second string array convert to link list and sort
        String set2=sc.nextLine();
        String [] arrStr2 = set2.split(" ");//spliting of set in to String array at each space
        list2.addAll(Arrays.asList(arrStr2)); //Push array to linklist
        Collections.sort(list2);//sort the linklist
        
        //Print size of array
        System.out.print("SIZE: " + list2.size());
        System.out.print(" Sorted: ");
        
        //iterate over link list to print the SORTED Course code string
        for(int num=0; num<list2.size(); num++){
            System.out.print(list2.get(num)+" ");
        }
        System.out.println();
        System.out.println();
        
        //**Create third string array convert to link list and sort
        String set3=sc.nextLine();
        String [] arrStr3 = set3.split(" ");//spliting of set in to String array at each space
        list3.addAll(Arrays.asList(arrStr3));//Push array to linklist
        Collections.sort(list3);//sort the linklist
        
        //Print size of array
        System.out.print("SIZE: " + list3.size());
        System.out.print(" Sorted: ");
        
        //iterate over link list to print the SORTED Course code string
        for(int num=0; num<list3.size(); num++){
            System.out.print(list3.get(num)+" ");
        }
        System.out.println();
        System.out.println();
    }
    
}


// end of program