/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newthermostatgui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLTempWeekendPageController implements Initializable {
    
    @FXML
    private ListView<String> progDataNumberDay;
    @FXML
    private ListView<String> progDataNumberMonth;
    @FXML
    private ListView<String> progDataNumberYear;
    @FXML
    private ListView<String> progDay;
    @FXML
    private TextField progTemp;
    @FXML
    private Button saveProg;//private void saveToFile
    @FXML
    private Button returnToScreen;//private void handleButtonAction2
    @FXML
    private Button resetProg;//private void resetProgfile
    @FXML
    private Button dwnCurrent;//private void decreaseTemp
    @FXML
    private Button upCurrent;//private void increaseTemp

    ObservableList<String> dataDay = FXCollections.observableArrayList(
             "Weekend", "Holiday");
    
    ObservableList<String> dateNumberDay = FXCollections.observableArrayList(
    "1", "2", "3", "4","5", "6", "7", "8", "9", "10", "11", "12", "13",
    "14","15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
    "26", "27", "28", "29", "30", "31");

    ObservableList<String> dateNumberMonth = FXCollections.observableArrayList(
    "1", "2", "3", "4","5", "6", "7", "8", "9", "10", "11", "12");
    
    ObservableList<String> dateNumberYear = FXCollections.observableArrayList(
    "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026"
    , "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035");    


    @FXML
    private void handleButtonProg(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLProgramGUI.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);        
        
             //Apply Style Sheet
        String css = FXMLTempWeekendPageController.class.getResource("NewThermostatCSS.css").toExternalForm();
        home_page_scene.getStylesheets().add(css);
            
        Stage app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();        
        
    }


    public class convertToArray{ 
        
        // Make an array to store list items in the observable list as strings

        List<String> dataDay = new ArrayList<>();
        String[] wkdDay = new String[dataDay.size()];

        List<String> dateNumberDay = new ArrayList<>();
        String[] wkdNumDay = new String[dateNumberDay.size()];

        List<String> dateNumberMonth = new ArrayList<>();
        String[] wkdMonthDay = new String[dateNumberMonth.size()];

        List<String> dateNumberYear = new ArrayList<>();
        String[] wkdYear = new String[dateNumberYear.size()];  
    }

    
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //populates data to ListViews by calling ObservableList
        progDay.setItems(dataDay);
        progDataNumberDay.setItems(dateNumberDay);
        progDataNumberMonth.setItems(dateNumberMonth);
        progDataNumberYear.setItems(dateNumberYear);
        
        
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
                    //bf.newLine();
                    bf.write(ft.format(dateSys)+"\n");
                    bf.newLine();
                    bf.write("Program Day:  " + new_valProgDay);
                    //bf.newLine();
                    bf.write("\nTemperature:  " + userTemp);


                } catch (IOException ex) {
                Logger.getLogger(FXMLTempWeekendPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        });
        
        //*****Create ListView Listener - DataNumberDay and Append textFile
        progDataNumberDay.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener<String>() {
        
        @Override
        public void changed(ObservableValue<? extends String> observable,
        String old_val, String new_valDataNumberDay) {
        
        System.out.println("This is the Numerical Calender Day selection:  " + new_valDataNumberDay);
        
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("src/newthermostatgui/ProgramFile.txt", true)))
                {
                    bf.newLine();
                    bf.write("Date:  " + new_valDataNumberDay);
                    

                } catch (IOException ex) {
                Logger.getLogger(FXMLTempWeekendPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        });
        
        //*****Create ListView Listener - DataNumberMonth and Append textFile
        progDataNumberMonth.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener<String>() {
        
        @Override
        public void changed(ObservableValue<? extends String> observable,
        String old_val, String new_valDataNumberMonth) {
        
        System.out.println("This is the Numerical Month selection:  " + new_valDataNumberMonth);
        
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("src/newthermostatgui/ProgramFile.txt", true)))
                {

                    bf.write("/" + new_valDataNumberMonth);


                } catch (IOException ex) {
                Logger.getLogger(FXMLTempWeekendPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        });

        //*****Create ListView Listener - DataNumberYear and Append textFile
        progDataNumberYear.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener<String>() {
        
        @Override
        public void changed(ObservableValue<? extends String> observable,
        String old_val, String new_valDataNumberYear) {

        System.out.println("This is the Year selection:  " + new_valDataNumberYear);
        
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("src/newthermostatgui/ProgramFile.txt", true)))
                {

                    bf.write("/" + new_valDataNumberYear);
                    bf.newLine();                    
                    bf.write("\n*********End of Program*********");

                } catch (IOException ex) {
                Logger.getLogger(FXMLTempWeekendPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    });
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

    @FXML //Save program to TextFile
    private void saveToFile(ActionEvent event) throws IOException {

 
        String userTemp = Integer.toString(setProgram.getTemp());
        System.out.println("Programmed Temperature setting is:  " + userTemp);
        
        //Add popup ******** throws popup when system program cleared
        Alert popOne = new Alert(Alert.AlertType.INFORMATION);
        popOne.setTitle("*** SYSTEM MESSAGE ***");
        popOne.setContentText("Current program has been saved");
        popOne.setHeaderText("CONFIRMED");
        popOne.show();        

    }
 
    @FXML//Button function to switch to Program screen
    private void handleButtonAction2(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLHomepage.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        
             //Apply Style Sheet
        String css = FXMLTempWeekendPageController.class.getResource("NewThermostatCSS.css").toExternalForm();
        home_page_scene.getStylesheets().add(css);
            
        Stage app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();        
    }
    
    @FXML //Clear TextFile
    private void resetProgfile(ActionEvent event) throws IOException {

        
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
    
}
