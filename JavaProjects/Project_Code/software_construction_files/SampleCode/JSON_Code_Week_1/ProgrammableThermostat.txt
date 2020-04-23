/*
 * To read JSON data from a webservice and map to console
 */
package programmablethermostat;

/**
 *
 * @author Brian Bok
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject; 

public class ProgrammableThermostat {

    
    public static void main(String[] args) {
	try {
        ProgrammableThermostat.call_me(); //Error call on Json HTTP
	} catch (Exception e) {
            
            System.out.println();

            System.out.println(  "Unable to complete jsonData read due to error with Json string at \n" + "http://media.capella.edu/BBCourse_Production/IT4774/temperature.json#" );
            System.out.println();
        e.printStackTrace();
        
	}
}


public static void call_me() throws Exception {
	String url = "http://media.capella.edu/BBCourse_Production/IT4774/temperature.json#";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
        //Print the Json webserve HTTP
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println();
        
        //Read string from Json file
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        
        //Loop Json file 
        while ((inputLine = in.readLine()) != null) {
        	response.append(inputLine);
        }
        in.close();
        
        //print Map of Json info in String
        System.out.println(response.toString());
        System.out.println();
        
        //Read JSON response and print
        JSONObject jsonData = new JSONObject(response.toString());
        System.out.println("Results displayed after Reading JSON Data from HTTP URI.");
        System.out.println();
        System.out.println("identifier: "+ jsonData.getString("identifier"));
        System.out.println("name: "+ jsonData.getString("name"));
        System.out.println("thermostatTime: "+ jsonData.getString("thermostatTime"));
        System.out.println("utcTime: "+ jsonData.getString("utcTime"));
        System.out.println("actualTemperature: "+ jsonData.getDouble("actualTemperature"));
        System.out.println("status: "+ jsonData.getString("status"));
        System.out.println("code: "+ jsonData.getInt("code"));
        System.out.println("message: "+ jsonData.getString("message"));
	}
}

