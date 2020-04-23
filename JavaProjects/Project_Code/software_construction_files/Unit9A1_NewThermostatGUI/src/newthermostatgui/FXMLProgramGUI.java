/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newthermostatgui;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLProgramGUI implements Initializable {

    @FXML
    private ListView<String> progDay;;
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

    @FXML
    private void handleButtonAction1(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLTempWeekend.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        
                 //Apply Style Sheet
        String css = NewThermostatGUI.class.getResource("NewThermostatCSS.css").toExternalForm();
        home_page_scene.getStylesheets().add(css);

        Stage app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();         
    }

    @FXML
    private void ViewProgramText(ActionEvent event) {
                ViewProgram();  //calls scanner to display to textbox        
        
    }
 
    //Default Temperature Settings
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

    
    /*
     ****************Initializes the controller class.****************
    */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // Populates Observable List
        progDay.setItems(dataDay);
        progTime.setItems(dataTime);
        
        
        
        //*****Create ListView Listener - Day and Append textFile
        progDay.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener<String>() {
        
        @Override
        public void changed(ObservableValue<? extends String> observable,
        String old_val, String new_valProgDay) {
            
        String userTemp = Integer.toString(setProgram.getTemp());
        System.out.println("This is the Day selection:  " + new_valProgDay);
        
        Date dateSys = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("src/newthermostatgui/ProgramFile.txt", true)))
                {
                    bf.newLine();
                    bf.write("\n*************Thermostat Program*************\n");
                    bf.newLine();
                    bf.write(ft.format(dateSys)+"\n");
                    bf.newLine();
                    bf.write("Program Day:  " + new_valProgDay);//dummy data  + userDay
                    bf.newLine();
                    bf.write("Temperature:  " + userTemp);
                    bf.newLine();

                } catch (IOException ex) {
                Logger.getLogger(FXMLProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        });
        
        //*****Create ListView Listener - Time and Append textFile
        progTime.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener<String>() {
        
        @Override
        public void changed(ObservableValue<? extends String> observable,
        String old_val, String new_valProgTime) {
        
        System.out.println("This is the Time selection:  " + new_valProgTime);
        
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("src/newthermostatgui/ProgramFile.txt", true)))
                {

                    bf.write("Time:  " + new_valProgTime);
                    bf.newLine();
                    bf.write("\n*********End of Program*********");

                } catch (IOException ex) {
                Logger.getLogger(FXMLProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        });
        
        
    }
    

    @FXML//*****Create Method for MouseOver Popup event
    private void openHolidaySched(MouseEvent event) {
        
        /*Set to if  = 'Holiday' open weekend scheduler
        if (setProgram.getTemp() > 50) {
        progTemp.setText(Integer.toString(setProgram.decreaseTemp()));
        }
        */
    }

    @FXML  // Increase Temp setting
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
    
    @FXML //Decrease temp setting
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
        System.out.println("Programmed Temperature setting is:  " + userTemp);
         
    textArea.clear(); //Clears textBox and reloads with scanner. Removes Duplicate programs.


                ViewProgram();  //calls scanner to display to textbox
            }
    
    //Scans text file to display programs to TextBox  
    public void ViewProgram(){
        try{
            Scanner s = new Scanner(new File("src/newthermostatgui/ProgramFile.txt"));
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

    @FXML //reset program file
    private void resetProgfile(ActionEvent event) throws IOException {
    
         textArea.clear();  // Clears textBox when deleting all files from TextFile
        
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

    @FXML //Returns To Home Screen
    private void handleButtonAction2(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLHomepage.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        
                 //Apply Style Sheet
        String css = FXMLProgramGUI.class.getResource("NewThermostatCSS.css").toExternalForm();
        home_page_scene.getStylesheets().add(css);

        Stage app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

}
