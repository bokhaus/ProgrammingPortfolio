/*
 * Controller for use with Test FXML
 */
package newthermostatgui;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author User
 */
public class TestClassController implements Initializable {
    @FXML
    private AnchorPane secondPane;
    @FXML
    private Label label;
    @FXML
    private Label label1;
    @FXML
    private Label label3;
    @FXML
    private Button returnToScrene;
    @FXML
    private Button upCurrent;
    @FXML
    private Button dwnCurrent;
    @FXML
    private Button timeUp;
    @FXML
    private Button timeDwn;
    @FXML
    private Button dayUp;
    @FXML
    private Button dayDwn;
    @FXML
    private Button saveProg;
    @FXML
    private Button resetProg;
    @FXML
    private TextField progDay;
    @FXML
    private TextField progTime;
    @FXML
    private TextField progTemp;

    //Default Settings
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

       
currentSetting setProgram = new currentSetting(70);
    
public class UserSettings {
    
    
       Path settingsFile = Paths.get("src/newthermostatgui/ProgramFile.txt");
       public final Properties userProgram = new Properties();
       //Write File
       public void setDay(String key, String value){

       userProgram.setProperty(key, value);
   
        try {
        Writer storeDay = Files.newBufferedWriter(settingsFile);
        userProgram.store(storeDay, "User Settings"); 
        }
        catch(IOException Ex) {
        System.out.println("Error :" +
        Ex.getMessage());
        
        //Add popup ******** throws popup when system program cleared
        Alert popOne = new Alert(Alert.AlertType.INFORMATION);
        popOne.setTitle("*** ERROR MESSAGE ***");
        popOne.setContentText("Error :" + Ex.getMessage());
        popOne.setHeaderText("*** ERROR MESSAGE ***");
        popOne.show();        
        }
       
   }  
   
   //Read File
    public void getDay(String temp, String hour, String day){
        try {      
          Reader daySettings = 
            Files.newBufferedReader(settingsFile);
            userProgram.load(daySettings);
            
            //Add popup ******** throws popup when system program cleared
            Alert popOne = new Alert(Alert.AlertType.INFORMATION);
            popOne.setTitle("*** SYSTEM MESSAGE ***");
            popOne.setContentText("The temperature was not saved"); 
            popOne.setHeaderText("*** ERROR MESSAGE ***");
            popOne.show();     
           }
        catch(IOException Ex)
        {
            System.out.println("Error :" +
                    Ex.getMessage());
        }    
    }    
}

public class controlsDayTime {
    //Arrays to Populate data to the specified screens on progScreen 
    String[] weekDayArray = {"Monday", "Tuesday", "Wednesday", "Thursday", 
                         "Friday", "Saturday", "Sunday"};
    
    String[] timeDailyArray = 
                         {"12:00 AM", "12:15 AM", "12:30 AM", "12:45 AM",
                          "1:00 AM", "1:15 AM", "1:30 AM", "1:45 AM",
                          "2:00 AM", "2:15 AM", "2:30 AM", "2:45 AM", 
                          "3:00 AM", "3:15 AM", "3:30 AM", "3:45 AM", 
                          "4:00 AM", "4:15 AM", "4:30 AM", "4:45 AM", 
                          "5:00 AM", "5:15 AM", "5:30 AM", "5:45 AM", 
                          "6:00 AM", "6:15 AM", "6:30 AM", "6:45 AM", 
                          "7:00 AM", "7:15 AM", "7:30 AM", "7:45 AM", 
                          "8:00 AM", "8:15 AM", "8:30 AM", "8:45 AM", 
                          "9:00 AM", "9:15 AM", "9:30 AM", "9:45 AM", 
                          "10:00 AM", "10:15 AM", "10:30 AM", "10:45 AM", 
                          "11:00 AM", "11:15 AM", "11:30 AM", "11:45 AM", 
                          "12:00 PM", "12:15 PM", "12:30 PM", "12:45 PM", 
                          "1:00 PM", "1:15 PM", "1:30 PM", "1:45 PM", 
                          "2:00 PM", "2:15 PM", "2:30 PM", "2:45 PM",  
                          "3:00 PM", "3:15 PM", "3:30 PM", "3:45 PM", 
                          "4:00 PM", "4:15 PM", "4:30 PM", "4:45 PM", 
                          "5:00 PM", "5:15 PM", "5:30 PM", "5:45 PM", 
                          "6:00 PM", "6:15 PM", "6:30 PM", "6:45 PM", 
                          "7:00 PM", "7:15 PM", "7:30 PM", "7:45 PM", 
                          "8:00 PM", "8:15 PM", "8:30 PM", "8:45 PM", 
                          "9:00 PM", "9:15 PM", "9:30 PM", "9:45 PM", 
                          "10:00 PM", "10:15 PM", "10:30 PM", "10:45 PM", 
                          "11:00 PM", "11:15 PM", "11:30 PM", "11:45 PM", 
                        };
    
    /*Attempt to use SimpleDateFormat as a way to populate Day on progScreen
     * Look at this option again. 
    
     * Date objDate3 = new Date(); // Current System Date and time is assigned to objDate
      
      String strDateFormat3 = "E"; //Date format is Specified
      SimpleDateFormat objSDF3 = new SimpleDateFormat(strDateFormat3); 
    */
    
       public int dayToSave;
       public int timeToSave;
       
     controlsDayTime(int a, int b) {
            dayToSave = a;
            timeToSave = b;
        }
     
      String increaseDay() {
          dayToSave++;
            return weekDayArray[dayToSave];
        }

        public String decreaseDay() {
            dayToSave--;
            return weekDayArray[dayToSave];
        }

        public int setDay(int a) {
            dayToSave = a;
            return a;
        }

        public int getDay() {
            return dayToSave;
        }
        
        public String getDayString() {
            return weekDayArray[dayToSave];
        }    
        
        String increaseTime() {
          timeToSave++;
            return timeDailyArray[timeToSave];
        }

        public String decreaseTime() {
            timeToSave--;
            return timeDailyArray[timeToSave];
        }

        public int setTime(int a) {
            timeToSave = a;
            return a;
        }

        public int getTime() {
            return timeToSave;
        }
        
        public String getTimeString() {
            return timeDailyArray[timeToSave];
        }       
}
    
    
    controlsDayTime programSave = new controlsDayTime(0,0);
    UserSettings programSetting = new UserSettings();  
    
    @FXML
    private void increaseTemp() { //Increase Temp unless above 90
        if (setProgram.getTemp() < 90) {
            progTemp.setText(Integer.toString(setProgram.increaseTemp()));
        } else {
            
            //Add popup ******** throws popup when above 90
            Alert popOne = new Alert(Alert.AlertType.INFORMATION);
            popOne.setTitle("*** Warning ***");
            popOne.setContentText("Temperture may not be set above 90 degrees");
            popOne.setHeaderText("Temperature Range Limit -EXCEEDED.");
            popOne.show();
        }
    }
    String userDay = programSave.getDayString();
    String userHour = programSave.getTimeString();
    String userTemp = Integer.toString(setProgram.getTemp());
         

    @FXML
    private void decreaseTemp() {
        if (setProgram.getTemp() > 50) {
            progTemp.setText(Integer.toString(setProgram.decreaseTemp()));
        
        } else {
            progTemp.setText(Integer.toString(setProgram.getTemp()));
            
            //Add popup ******** throws popup when below 50
            Alert popTwo = new Alert(Alert.AlertType.INFORMATION);
            popTwo.setTitle("*** Warning ***");
            popTwo.setContentText("Temperture may not be set below 50 degrees");
            popTwo.setHeaderText("Temperature Range Limit -EXCEEDED.");
            popTwo.show();
        }
    }

    @FXML
    private void increaseTime() { //Increase Time in progScreen
        if (programSave.getTime() < 23) {
        progTime.setText(programSave.increaseTime());
        }
    }

    @FXML
    private void decreaseTime() { //decrease time in progScreen
        if (programSave.getTime() > 0) {
        progTime.setText(programSave.decreaseTime());
        }
    }

    @FXML
    private void increaseDay() { //Increase day in progScreen
        if (programSave.getDay() < 6) {
            progDay.setText(programSave.increaseDay());
        }
        else progDay.setText(programSave.getDayString());
        
    }

    @FXML
    private void decreaseDay() { //decrease day in progScreen
        if (programSave.getDay() > 0) {
        progDay.setText(programSave.decreaseDay());
        }
    }

    @FXML
    private void saveToFile() throws IOException { //Save file to text file
        
                
         String programDay = programSave.getDayString();
         String programHour = programSave.getTimeString();
         String programTemp = Integer.toString(setProgram.getTemp());

            programSetting.setDay("Temperature", userTemp);
            programSetting.setDay("Hour", userHour);
            programSetting.setDay("Day", userDay);
            
            
        //Add popup ******** throws popup when system program cleared
        Alert popOne = new Alert(Alert.AlertType.INFORMATION);
        popOne.setTitle("*** SYSTEM MESSAGE ***");
        popOne.setContentText("ALL program files have been erased from the system");
        popOne.setContentText("TEMPERATURE: " + userTemp +"\n" + "DAY: " + 
                userDay + "\n" + userHour);
        popOne.setHeaderText("CONFIRMED");
        popOne.show();
        
        System.out.println(userTemp + " at  " + userHour + " on " + userDay + " saved successfully.");
            
    }

    @FXML
    private void resetProgfile() throws IOException { //Clear program from text file
    
    try (FileWriter fileWriter = new FileWriter("src/newthermostatgui/ProgramFile.txt")) {
    fileWriter.append("");
    
    //Add popup ******** throws popup when system program cleared
    Alert popOne = new Alert(Alert.AlertType.INFORMATION);
    popOne.setTitle("*** SYSTEM MESSAGE ***");
    popOne.setContentText("ALL program files have been erased from the system");
    popOne.setHeaderText("CONFIRMED");
    popOne.show();
    }
}
    
    @FXML
    private void exitSystem() throws InterruptedException { //Exit system
        //Add popup ******** throws popup on exit
            Alert popOne = new Alert(Alert.AlertType.INFORMATION);
            popOne.setTitle("*** SYSTEM MESSAGE ***");
            popOne.setContentText("Have a great day!");
            popOne.setHeaderText("System shutting down");
            popOne.show();
            
        Platform.exit();
        TimeUnit.SECONDS.sleep(2);
        System.exit(0);
    }



    /**
     * Initializes the Main Method.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    //connect back to home_screen
    @FXML
    private void handleButtonAction2(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLHomepage.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        
                 //Apply Style Sheet
        String css = NewThermostatGUI.class.getResource("NewThermostatCSS.css").toExternalForm();
        home_page_scene.getStylesheets().add(css);

        Stage app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    
}




