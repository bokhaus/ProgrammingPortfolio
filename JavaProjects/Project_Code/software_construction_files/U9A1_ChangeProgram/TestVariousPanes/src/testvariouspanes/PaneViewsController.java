/*
 * Attempt to use SimpleDateFormat as a way to populate Day on progScreen
     * Look at this option again. 
    
     * Date objDate3 = new Date(); // Current System Date and time is assigned to objDate
      
        *String strDateFormat3 = "E"; //Date format is Specified
            *SimpleDateFormat objSDF3 = new SimpleDateFormat(strDateFormat3); 
 */
package testvariouspanes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PaneViewsController implements Initializable {

    @FXML
    private ListView<String> progDay;
    @FXML
    private ListView<String> progTime;
    @FXML
    private TextArea textArea;    
    @FXML
    private TextField progTemp;//
    @FXML
    private Button upCurrent;//private void increaseTemp
    @FXML
    private Button dwnCurrent;//private void decreaseTemp
    @FXML
    private Button saveProg;//private void saveToFile
    @FXML
    private Button returnToScreen;//private void handleButtonAction2
    @FXML
    private Button resetProg;//private void resetProgfile

    
    ObservableList<String> dataDay = FXCollections.observableArrayList(
            "Monday", "Tuesday", "Wednesday", "Thursday", 
            "Friday", "Saturday", "Sunday", "Weekend", "Holiday");
    
    ObservableList<String> dataTime = FXCollections.observableArrayList(
              "12:00 AM", "12:15 AM", "12:30 AM", "12:45 AM",
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
              "11:00 PM", "11:15 PM", "11:30 PM", "11:45 PM");  
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
    
    public class userSettings {
                


        public int dayToSave;
        public int timeToSave;
 
    public userSettings() {
           

            
           }  
         //****************************
         //********WORKING HERE********
         //****************************
        
        String[] userDay = new String[dataDay.size()];
        String[] wktime = new String[dataTime.size()]; 
        
            public int setDay(int a) {
                dayToSave = a;
                return a;
            }

            public int getDay() {
                return dayToSave;
            }

            public String getDayString() {
                return userDay[dayToSave];
            }    

            public int setTime(int a) {
                timeToSave = a;
                return a;
            }

            public int getTime() {
                return timeToSave;
            }

            public String getTimeString() {
                return wktime[timeToSave];
            }

        }
    currentSetting setProgram = new currentSetting(70);
    userSettings set1 = new userSettings(); 


    /**
     ****************Initializes the controller class.****************
    */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // Populates Observable List
        progDay.setItems(dataDay);
        progTime.setItems(dataTime);
        
    }//*****Create Method for GridPane
    
    
    @FXML//*****Create Method for MouseOver Popup event
    private void openHolidaySched(MouseEvent event) {
    }

    @FXML
    private void increaseTemp(ActionEvent event) {
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
    
    @FXML
    private void decreaseTemp(ActionEvent event) {
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
    private void exitSystem(ActionEvent event) throws InterruptedException {
            //Add popup ******** throws popup on exit
            Alert popOne = new Alert(Alert.AlertType.INFORMATION);
            popOne.setTitle("*** SYSTEM MESSAGE ***");
            popOne.setContentText("Have a great day!");
            popOne.setHeaderText("System shutting down");
            popOne.show();
            
        Platform.exit();
        TimeUnit.SECONDS.sleep(1);
        System.exit(0);
    }
     //****************************
     //********WORKING HERE********
     //****************************
    @FXML
    private void saveToFile(ActionEvent event) throws IOException {

        String userTemp = Integer.toString(setProgram.getTemp());
        
        /*
        String userDay = Integer.toString(set1.getDay());
        String userTime = Integer.toString(set1.getTime());
        */
        
        String userDay = set1.getDayString();
        String userTime = set1.getTimeString();
        
        textArea.clear(); 
        
        /*
        set1.setDay("Temperature", userTemp);
        set1.setDay("Hour", userHour);
        set1.setDay("Day", userDay);
        */ 

   /////////////////////////////////////////////////////////////////////////
   
                Date dateSys = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
                /*
                String pattern = "###,###.###";
                DecimalFormat df = new DecimalFormat(pattern);
                */
                try (BufferedWriter bf = new BufferedWriter(new FileWriter("src/testvariouspanes/ProgramFile.txt", true)))
                {
                    bf.write("\n");
                    bf.write("*************Thermostat Program*************\n");
                    bf.newLine();
                    bf.write(ft.format(dateSys)+"\n");
                    bf.newLine();
                    bf.write("Program Day:  " + userDay);
                    bf.newLine();
                    bf.write("Time: " + userTime);
                    bf.newLine();
                    bf.write("Temperature:  " + userTemp);
                    bf.newLine();
                    bf.write("\n*********End of Program*********");
                    bf.write("\n");
                }
                catch (IOException ex)
                {
                    Logger.getLogger(PaneViewsController.class.getName()).log(Level.SEVERE, null, ex);
                }

                ViewProgram();
            }
    
    public void ViewProgram(){
        try{
            Scanner s = new Scanner(new File("src/testvariouspanes/ProgramFile.txt"));
            while (s.hasNext())
            {
                String line = s.nextLine();
                textArea.appendText(line + "\n");

            }
            
        }
        catch (FileNotFoundException ex)
        {
            System.err.println(ex);
        }
    }

    public String writeSpace(int length, String string)
    {
        String spaces = "";

        for (int i = 0; i < length - string.length(); i++)
        {
            spaces += " ";
        }

        System.out.println(string.length() + spaces.length());
        return string + spaces;
    }

    @FXML //reset program file
    private void resetProgfile(ActionEvent event) throws IOException {
    
         textArea.clear();  
        
        try (FileWriter fileWriter = new FileWriter("src/testvariouspanes/ProgramFile.txt")) {
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
    private void handleButtonAction2(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLHomepage.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        
                 //Apply Style Sheet
        String css = PaneViewsController.class.getResource("NewThermostatCSS.css").toExternalForm();
        home_page_scene.getStylesheets().add(css);

        Stage app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

}
