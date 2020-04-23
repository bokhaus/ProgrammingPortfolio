/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newthermostatgui;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLHomepageController implements Initializable {

    @FXML
    private AnchorPane homePane;
    @FXML
    private Button button;
    @FXML
    private Label label;
    @FXML
    private Label label1;
    @FXML
    private Label label3;
    @FXML
    private TextField currTemp;
    @FXML
    private TextField currHumid;
    @FXML
    private TextField currSett;
    @FXML
    private TextField currTime;
    @FXML
    private Button loadProgPane;
    @FXML
    private Button upBtn;
    @FXML
    private Button dwnBtn;

    /**
     * Initializes the controller class.
     */

    public class currentSetting {

        int defaultTemp;

        currentSetting(int a) {
            defaultTemp = a;
        }

        public int increaseTemp() {
            return defaultTemp++;
        }

        public int decreaseTemp() {
            return defaultTemp--;
        }

        public int setTemp(int a) {
            defaultTemp = a;
            return a;
        }

        public int getTemp() {
            return defaultTemp;
        }
    }

       
    currentSetting progSet = new currentSetting(70);

  
    //Setting Controls  
    
    @FXML
    private void increaseTemp() {
        if (progSet.getTemp() < 90) {
            currSett.setText(Integer.toString(progSet.increaseTemp()));
        } else {
            
            //Add popup ********
            Alert popOne = new Alert(Alert.AlertType.INFORMATION);
            popOne.setTitle("Warning!!!");
            popOne.setContentText("Temperture may not be set above 90 degrees");
            popOne.setHeaderText("Temperature Range Limit -EXCEEDED.");
            popOne.show();
        }
    }
    @FXML
    private void decreaseTemp() {
        if (progSet.getTemp() > 50) {
            currSett.setText(Integer.toString(progSet.decreaseTemp()));
        } else {
            currSett.setText(Integer.toString(progSet.getTemp()));
            
            //Add popup ********
            Alert popTwo = new Alert(Alert.AlertType.INFORMATION);
            popTwo.setTitle("Warning!!!");
            popTwo.setContentText("Temperture may not be set below 50 degrees");
            popTwo.setHeaderText("Temperature Range Limit -EXCEEDED.");
            popTwo.show();
        }
    }
    @FXML
    private void resetSetting() {
        progSet.setTemp(70);
        currSett.setText(Integer.toString(progSet.getTemp()));
    }

    @FXML
    private void coolSetting() {
        progSet.setTemp(50);
        currSett.setText(Integer.toString(progSet.getTemp()));
    }

    @FXML
    private void exitSystem() throws InterruptedException {
                //Add popup ******** throws popup on exit
            Alert popOne = new Alert(Alert.AlertType.INFORMATION);
            popOne.setTitle("SYSTEM MESSAGE!!!");
            popOne.setContentText("Have a great day!");
            popOne.setHeaderText("System shutting down");
            popOne.show();
            
        Platform.exit();
        TimeUnit.SECONDS.sleep(2);
        System.exit(0);
    }
    @FXML
    public void handleButtonAction() throws Exception {

        String info = jsonGetRequest("http://media.capella.edu/BBCourse_Production/IT4774/temperature.json");
        

        
        //Class driver for unit display    
        class Identity {

            String ident, name, thermT, utcT;

            Identity(String a, String b, String c, String d) {
                ident = a;
                name = b;
                thermT = c;
                utcT = d;
            }

            public String displayIdentity() {
                return ident;
            }

            public String displayName() {
                return name;
            }

            public String displayThermostatTime() {
                return thermT;
            }

            public String displayUTCTime() {
                return utcT;
            }
        }

        //Class driver for tem and humidity
        class Run {
                
            double temp, humid;

            Run(double a, double b) {
                temp = a;
                humid = b;
            }

            public double displayTemp() {
                return temp;
            }

            public double displayHumidity() {
                return humid;
            }
        }

        //Class Driver for Status
        class Status {

            int code;
            String statusMessage;

            Status(int a, String b) {
                code = a;
                statusMessage = b;
            }

            public int displayCode() {
                return code;
            }

            public String displayStatus() {
                if (statusMessage.length() < 1) {
                    return "Idle";
                } else {
                    return statusMessage;
                }
            }
        }

        // Object from JSON string
        Object obj = new JSONParser().parse(info); 
          
        JSONObject thermoJson = (JSONObject) obj;
        
        String identifier = (String) thermoJson.get("identifier"); 
        String name = (String) thermoJson.get("name");
        String thermTime = (String) thermoJson.get("thermostatTime");
        String utcTime = (String) thermoJson.get("utcTime");

        Map runtime = ((Map)thermoJson.get("runtime")); 
        Map status = (Map)thermoJson.get("status");
                
        
        //iterat and map json data to arrays
        ArrayList<String> run = new ArrayList(2);
        Iterator<Map.Entry> run2;
        run2 = runtime.entrySet().iterator();
        
        while (run2.hasNext()) {
            Map.Entry pair = run2.next();
            run.add(pair.getValue().toString());
        }
        
        //Convert ArrayStrings to ints
        double dHumidity = Integer.parseInt(run.get(0));
        double dTemp = Integer.parseInt(run.get(1));

        ArrayList<String> stat = new ArrayList(2);
        Iterator<Map.Entry> stat2;
        stat2 = status.entrySet().iterator();
        
        while (stat2.hasNext()) {
            Map.Entry pair = stat2.next();
            stat.add(pair.getValue().toString());
        }
        int intCode = Integer.parseInt(stat.get(0));
        String msgStat = stat.get(1);//get message

        Identity pT = new Identity(identifier, name, thermTime, utcTime);
        
        Run dNumber = new Run(dTemp, dHumidity);
        
        Status sysStatus = new Status(intCode, msgStat);
        
        String pattern = "h:mm:ss a";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        String date = dateFormat.format(new Date());
        
        
        //Set current to GUI from json string array
        currTemp.setText(Double.toString(dNumber.displayTemp()));
        currHumid.setText(Double.toString(dNumber.displayHumidity()));
        currSett.setText(Integer.toString((int) progSet.getTemp()));
        currTime.setText(date);

        
    }
    //stream json string to scanner
    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();

        return text;
    }
    //request json from url
    public String jsonGetRequest(String jsonString) {
        String json = null;
        try {
            URL url = new URL(jsonString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Get status of Thermostat
            String statusResponse = connection.getResponseMessage();
            int statusCode = connection.getResponseCode();
            
            InputStream jsonIn = connection.getInputStream();
            json = streamToString(jsonIn); // input stream to string
        } catch (IOException ex) {
        }//catch error

        return json;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    } 
    @FXML
    private void handleButtonAction2(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLProgramGUI.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        
             //Apply Style Sheet
        String css = NewThermostatGUI.class.getResource("NewThermostatCSS.css").toExternalForm();
        home_page_scene.getStylesheets().add(css);
            
        Stage app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
}
