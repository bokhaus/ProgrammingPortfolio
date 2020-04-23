/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitseventesting;

/**
 *
 * @author User
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class UnitSevenTesting {


    private static final String lineSeparator = System.getProperty("line.separator");

    public static void main(String[] args) throws FileNotFoundException, JSONException {
        try {
        UnitSevenTesting.call_me(); //Error call on Json 
	} catch (IOException e) {
            
            System.out.println();

            System.out.println("Unable to complete jsonData read due to error with Json string at \n" + "src/newjson.json" );
            System.out.println();
        
	}

    }
        
    public static void call_me() throws IOException, FileNotFoundException, JSONException{
        File jsonFile = new File("src/newjson.json");
        StringBuilder response;
        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
            response = new StringBuilder();
            String text;
            while ((text = reader.readLine()) != null) {
                response.append(text).append(lineSeparator);
            }

            System.out.println(response);
            
            //Read JSON response and print
            JSONObject jsonData = new JSONObject(response.toString());
            
            JSONObject runtime = jsonData.getJSONObject("runtime");
            double actualTemperature = runtime.getDouble("actualTemperature");
            double actualHumidity = runtime.getDouble("actualHumidity");
          
            
            JSONObject status = jsonData.getJSONObject("status");
            double code = status.getDouble("code");
            String message = status.getString("message");
            
            
            System.out.println("Results displayed after Reading JSON data from file src/newjson.json");
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

        
        } catch (IOException e) {
        }

    }
}
