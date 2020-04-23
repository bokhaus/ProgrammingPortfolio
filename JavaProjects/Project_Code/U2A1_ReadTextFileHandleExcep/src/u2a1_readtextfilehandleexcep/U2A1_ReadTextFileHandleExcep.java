/*
 * This program uses BufferedReader to read a text file and incorporates a the use
 * of substrings to specify character breaks for each variable.
 * @author Bok
 */
package u2a1_readtextfilehandleexcep;

import java.io.*;


public class U2A1_ReadTextFileHandleExcep {

    public static void main(String[] args){

        System.out.println("Teacher's copy \n");
        
        // Create variable for file instance
        String fileText = "courses.txt";
        //creates line variable     
        String line;     

        //Try block reads one line at a time from text file 
        // utilizing the bufferedreader
        try {

            //BufferedReader class reads text from a character-input stream
            BufferedReader input = new BufferedReader(new FileReader(fileText));

            //Throw an IOExcpetion 
            //compares input stream to null
            while ((line = input.readLine()) != null) {
                //isolates substring of text between characters 0 and 6
                String courseCode = line.substring(0, 6);
                //isolates substring of text between characters 7 and 8
                String credit = line.substring(7,8);
                //isolates substring of text between characters 9 and the end of the string
                String courseTitle = line.substring(9);
                
                // print concatenated string in required format
                System.out.println("Course Code = "+courseCode+" | Credit hourse = "+credit+
                        " | Course Title = "+courseTitle);

                }
            //close resources to conserve memory
            input.close();

            }
        // start catch block to throw exceptions and custom messages
        catch(FileNotFoundException ex){  

            System.out.println( "Unable to open file - Path not found '" + 
                fileText + "'");

        }
        // Blank IoException throws default message
        catch (IOException ex) {
        
        }
    }

}