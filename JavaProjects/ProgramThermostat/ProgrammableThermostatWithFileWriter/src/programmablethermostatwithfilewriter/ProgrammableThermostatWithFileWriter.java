/*
 * This program is designed to read data from user input and store to a local 
 * file called 'UserProgram.txt'. This program also reads data from a JSon URL
 * and stores it to a local file called ProgrammableThermostat.json
 */
package programmablethermostatwithfilewriter;

/**
 *
 * @author Brian Bok
 * 
 */
import java.io.*;
import java.net.*;
import org.json.*; 
import java.util.*;

public class ProgrammableThermostatWithFileWriter {

    /**
     *
     * @param args
     * @throws IOException
     */
    
    public static void main(String[] args) throws IOException, Exception,InputMismatchException{
        
        try {

            ProgrammableThermostatWithFileWriter.write_Array(); //Error call on Scanner
            } 

        catch (IndexOutOfBoundsException | InputMismatchException e) {
            System.out.println();

            //Custome Message
            System.out.println("Unable to write file...SYSTEM Out of Bounds ERROR");
            System.out.println();
        }
                    
        try {

            ProgrammableThermostatWithFileWriter.call_json(); //Error call on Json HTTP
            } 

        catch (Exception e) {
            //Custome Message
            System.out.println();
            System.out.println(  "ERROR: Unable to complete settings reading due to error with Thermostat located at: " + "HOME");
            System.out.println("------------------------------------------------");
        }
    }
    
    public static void write_Array() throws IndexOutOfBoundsException, IOException{
        //iterate variable 
        int i = 0;
        
        //Array used to hold week days
        String[] weekDays = new String[7];
        
        //Array used for daily temp setting
        double[]tempDaily = new double[7];
        
        //Create object scanner for user input
        Scanner input = new Scanner(System.in);
        
        //Ask user for input
        System.out.print("\nPlease enter day of the week to program\n");
        
        //While loop used to accept data from user
        while(i<7)
            { 
            weekDays[i] = input.next();
            i++; //increase the value of variable to get the next week day
            
        }
        //Initialize the value of i
        //Start for user data for temp setting
        i=0;
        
        //Display message to user
        System.out.print("\nPlease enter temperature "
                + "setting for each day\n");
        
        //While loop for user input tempSetting
        while(i<7)
                {   //Get user input for temp
                    tempDaily[i] = input.nextDouble();
                i++;
                }
       
        //Display request for tempSetting
        System.out.println("-------------------------------------------------------------");
        System.out.print("Your programmed temperature settings "
                + "for each day of the week\n");
        System.out.println("-------------------------------------------------------------");
             
        
            //Display values to user
            System.out.print("\nDay        Temperature\n");
            System.out.print(weekDays[0] + ":\t\t" + 
                    tempDaily[0] + " F\n");
            System.out.print(weekDays[1] + ":\t" + 
                    tempDaily[1] + " F\n");
            System.out.print(weekDays[2] + ":\t" + 
                    tempDaily[2] + " F\n");
            System.out.print(weekDays[3] + ":\t" + 
                    tempDaily[3] + " F\n");
            System.out.print(weekDays[4] + ":\t\t" + 
                    tempDaily[4] + " F\n");
            System.out.print(weekDays[5] + ":\t" + 
                    tempDaily[5] + " F\n");
            System.out.print(weekDays[6] + ":\t\t" + 
                    tempDaily[6] + " F\n");
            
        try (FileWriter fileWriter = new FileWriter("UserProgram.txt")) {
            fileWriter.append("\nDay        Temperature\n");
            fileWriter.append(weekDays[0] + ":\t\t" + 
                    tempDaily[0] + " F\n");
            fileWriter.append(weekDays[1] + ":\t" + 
                    tempDaily[1] + " F\n");
            fileWriter.append(weekDays[2] + ":\t" + 
                    tempDaily[2] + " F\n");
            fileWriter.append(weekDays[3] + ":\t" + 
                    tempDaily[3] + " F\n");
            fileWriter.append(weekDays[4] + ":\t\t" + 
                    tempDaily[4] + " F\n");
            fileWriter.append(weekDays[5] + ":\t" + 
                    tempDaily[5] + " F\n");
            fileWriter.append(weekDays[6] + ":\t\t" + 
                    tempDaily[6] + " F\n");
            
            System.out.print("\nProgram settings copied to file:...UserProgram\n");
        }
        catch (IndexOutOfBoundsException | InputMismatchException e) {
            
            //Custome Message
            System.out.println("Unable to write file...SYSTEM Out of Bounds ERROR");
            System.out.println();
        }
    }
    
    public static void call_json() throws Exception {
        String url = "http://media.capella.edu/BBCourse_Production/IT4774/temperature.json#";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Print the Json webserve HTTP
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Sending request to Thermostat...HOME at:" + "\n"+url );
        System.out.println("---------------------------------------------------------------------");

        StringBuffer response;
        try ( //Read string from Json file
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuffer();

            //Loop Json file
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);

            }
            //close resources
            in.close();
        }
        //Read JSON response and print
        JSONObject jsonData = new JSONObject(response.toString());
        
        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter("ProgrammableThermostat.json")) {
            file.append(response.toString());
            
            System.out.println("\nSuccessfully Copied Current Thermostat STATUS of HOME to File..." + "ProgrammableThermostat\n");
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("\nCURRENT THERMOSTAT SETTINGS\n" + jsonData+ "/n");
            System.out.println();
            
        }
        catch(IOException e) {
            System.out.println("Unable to write file...SYSTEM ERROR");
        }
        
        System.out.println("-------------------------------------------------------");
        System.out.println("The Current Settings for Thermostat HOME: " + jsonData.getString("identifier"));
        System.out.println();
        System.out.println("identifier: "+ jsonData.getString("identifier"));
        System.out.println("name: "+ jsonData.getString("name"));
        System.out.println("thermostatTime: "+ jsonData.getString("thermostatTime"));
        System.out.println("utcTime: "+ jsonData.getString("utcTime"));
        System.out.println("actualTemperature: "+ jsonData.getString("actualTemperature"));
        System.out.println("status: "+ jsonData.getString("status"));
        System.out.println("code: "+ jsonData.getString("code"));
        System.out.println("message: "+ jsonData.getString("message/n"));
        System.out.println("------------------------------------------------");
    }
}
    
    

    
    
           
           
        

