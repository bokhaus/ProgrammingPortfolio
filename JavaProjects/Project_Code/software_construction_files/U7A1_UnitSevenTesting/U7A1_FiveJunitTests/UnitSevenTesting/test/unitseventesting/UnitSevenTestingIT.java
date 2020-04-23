/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitseventesting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author User
 */
public class UnitSevenTestingIT {
    
    public UnitSevenTestingIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        //System.out.println("This is set method");
    }
    
    @AfterClass
    public static void tearDownClass() {
        //System.out.println("This is tear down method");
    }

    /**
     * Test of main method, of class UnitSevenTesting.
     */
    private static final String lineSeparator = System.getProperty("line.separator");
    
    //Test of call_me method, FileNotFoundException.
    @Test(expected=FileNotFoundException.class)
    public void mainMethodTestFNF() throws IOException, FileNotFoundException, JSONException{
        System.out.println("This is a main method test for FileNotFoundException");

        File jsonFile = new File("src/nofile.json");//Fictitious file
        StringBuilder response;
        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
            response = new StringBuilder();
            String text;
            while ((text = reader.readLine()) != null) {
                response.append(text).append(lineSeparator);
            }
        }
    }
    //Test of call_me method, IOException.
    @Test(expected=IOException.class)
    public void mainMethodTestIO() throws IOException, FileNotFoundException, JSONException{
        System.out.println("This is a main method test for IOException");

        File jsonFile = new File("src/nofile.json");//Fictitious file
        StringBuilder response;
        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
            response = new StringBuilder();
            String text;
            while ((text = reader.readLine()) != null) {
                response.append(text).append(lineSeparator);
            }
        }
    }
    //Test of call_me method, JSONException.
    @Test(expected=JSONException.class)
    public void mainMethodTestJSONExcept() throws IOException, FileNotFoundException, JSONException{
        System.out.println("This is a main method test for JSONException");

        File jsonFile = new File("src/NoJsonData.txt");//File without Json Data 
        StringBuilder response;
        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
            response = new StringBuilder();
            String text;
            while ((text = reader.readLine()) != null) {
                response.append(text).append(lineSeparator);
            }
            JSONObject jsonData = new JSONObject(response.toString());
        }
    }
    //Test of call_me method, of class UnitSevenTesting.
    @Test
    public void call_me_Test() throws Exception {
        System.out.println("This tests the call_me_Test() method");
        
        
      File jsonFile = new File("src/newjson.json");
        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {

            StringBuilder response = new StringBuilder();
            String text;
            while ((text = reader.readLine()) != null) {
                response.append(text).append(lineSeparator);
            }

            
            //Read JSON response and print
            JSONObject jsonData = new JSONObject(response.toString());


            assertEquals("ProgrammableThermostat", jsonData.getString("name"));   
        }
    }
    //Test call_me() method timeout - set to 500 milliseconds
    @Test (timeout=500)
    public void call_me_Test_MilSecToRun() throws Exception {
        
        System.out.println("This tests the call_me_Test_MilSecToRun() method");
        
                File jsonFile = new File("src/newjson.json");
        
        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
            StringBuilder response = new StringBuilder();
            String text;
            while ((text = reader.readLine()) != null) {
                response.append(text).append(lineSeparator);
            }

            //Read JSON response and print
            JSONObject jsonData = new JSONObject(response.toString());
            
            JSONObject runtime = jsonData.getJSONObject("runtime");
            double actualTemperature = runtime.getDouble("actualTemperature");
            double actualHumidity = runtime.getDouble("actualHumidity");
          
            
            JSONObject status = jsonData.getJSONObject("status");
            double code = status.getDouble("code");
            String message = status.getString("message");
            
            
            System.out.println("\nResults displayed after Reading JSON data from file src/newjson.json");
            System.out.println();
            System.out.println("identifier: "+ jsonData.getString("identifier"));
            System.out.println("name: "+ jsonData.getString("name"));
            System.out.println("thermostatTime: "+ jsonData.getString("thermostatTime"));
            System.out.println("utcTime: "+ jsonData.getString("utcTime"));
            System.out.println("\nruntime: ");
            
            System.out.println("    actualTemperature: "+ runtime.getDouble("actualTemperature"));
            System.out.println("    actualHumidity: "+ runtime.getDouble("actualHumidity"));
            
            System.out.println("\nstatus: ");
            System.out.println("    code: "+ status.getDouble("code"));
            System.out.println("    message: "+ status.getString("message"));
            
        }
    }
}
