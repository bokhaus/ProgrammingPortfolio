package edu.capella.it4749;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javax.json.stream.JsonParsingException;

/**
 *
 * @author BrianBok
 */
public class U02A1_Weather extends Application {
    GridPane grid = new GridPane();
    
    // FileChooser 
    FileChooser fileChooser = new FileChooser();
    
    //Logger for this application
    private static final Logger logger = Logger.getLogger(U02A1_Weather.class.getName());
    
    
    // This button should open the FileChooser
    Button openDataFileButton = new Button("Open Data File...");
    Button closeButton = new Button("Close");
    HBox openDataFileButtonBox = new HBox(openDataFileButton);
    
    // Set up TabPane with 2 tabs. Learner needs to add a 3rd tab
    TabPane tabPane = new TabPane();
    Tab temperatureTab = new Tab();
    Tab pressureTab = new Tab();
    Tab humidityTab = new Tab();
    // Create 3rd tab here
    Tab windTab = new Tab();
    
    BorderPane borderPane = new BorderPane();
    
    @Override
    public void start(Stage primaryStage) {
        RowConstraints row0 = new RowConstraints();
        RowConstraints row1 = new RowConstraints();
        
        // Configure row heights
        row0.setPercentHeight(45);
        row1.setPercentHeight(45);
        grid.getRowConstraints().addAll(row0, row1);
        grid.setAlignment(Pos.CENTER);
        grid.setPrefWidth(500);

        grid.setHgap(5);
        grid.setVgap(5);
        
        HBox tabPaneHBox = new HBox();
        tabPaneHBox.getChildren().add(tabPane);
        HBox.setHgrow(tabPane, Priority.ALWAYS);
        tabPane.setMinWidth(400);
        // Add TabPane with 2 tabs created above
        grid.add(tabPaneHBox, 0, 0);
        temperatureTab.setText("Temperature");
        pressureTab.setText("Pressure");
        humidityTab.setText("Humidity");
        // Set text for 3rd tab here 
        windTab.setText("Wind");
        
        
        borderPane.setCenter(grid);
       
        borderPane.setBottom(openDataFileButtonBox);
        openDataFileButtonBox.setAlignment(Pos.CENTER);
        openDataFileButtonBox.setPadding(new Insets(10, 10, 10, 10));
        
        // Contents for tabs organized in HBox and VBox layouts
        HBox temperatureHBox = new HBox();
        temperatureHBox.setAlignment(Pos.BASELINE_LEFT);
        HBox highTempHBox = new HBox();
        HBox lowTempHBox = new HBox();
        
        VBox temperatureVBox = new VBox();
        temperatureVBox.setPadding(new Insets(10, 10, 10, 10));
        
        HBox pressureHBox = new HBox();
        pressureHBox.setPadding(new Insets(10, 10, 10, 10));
        
        HBox humidityHBox = new HBox();
        humidityHBox.setPadding(new Insets(10, 10, 10, 10));
        
        Label currentTemperatureLabel = new Label("Current temp: ");
        Label currentTemperatureDisplay = new Label();
        temperatureHBox.getChildren().addAll(currentTemperatureLabel, currentTemperatureDisplay);
        
        Label highTemperatureLabel = new Label("High temp: ");
        Label highTemperatureDisplay = new Label();
        highTempHBox.getChildren().addAll(highTemperatureLabel, highTemperatureDisplay);
        
        Label lowTemperatureLabel = new Label("Low temp: ");
        Label lowTemperatureDisplay = new Label();
        lowTempHBox.getChildren().addAll(lowTemperatureLabel, lowTemperatureDisplay);
        
        temperatureVBox.getChildren().addAll(temperatureHBox, highTempHBox, lowTempHBox);
        temperatureTab.setContent(temperatureVBox);
        
        Label pressureLabel = new Label("Pressure: ");
        Label pressureDisplay = new Label();
        pressureHBox.getChildren().addAll(pressureLabel, pressureDisplay);
        pressureTab.setContent(pressureHBox);
        
        Label humidityLabel = new Label("Humidity: ");
        Label humidityDisplay = new Label();
        humidityHBox.getChildren().addAll(humidityLabel, humidityDisplay);
        humidityTab.setContent(humidityHBox);
        
        /**
         * ============================================
         *   Code for arranging contents of 4th tab. directionHBox
         * ============================================
         */
        
        //Wind HBox
        HBox windHBox = new HBox();
        windHBox.setAlignment(Pos.BASELINE_LEFT);
        HBox windSpeedHBox = new HBox();
        HBox windDirectionHBox = new HBox();
        
        //wind data
        VBox windVBox = new VBox();
        windVBox.setPadding(new Insets(10, 10, 10, 10));
        
        
        //Wind Speed
        Label windSpeedLabel = new Label("Wind Speed: ");
        Label windSpeedDisplay = new Label();
        windSpeedHBox.getChildren().addAll(windSpeedLabel, windSpeedDisplay );

        //Wind Direction
        Label windDirectionLabel = new Label("Wind Direction: ");
        Label windDirectionDisplay = new Label();
        windDirectionHBox.getChildren().addAll(windDirectionLabel, windDirectionDisplay);
        
        //Wind VBox setting
        windVBox.getChildren().addAll(windSpeedHBox, windDirectionHBox);
        windTab.setContent(windVBox);
        
        
        tabPane.getTabs().add(temperatureTab);
        tabPane.getTabs().add(pressureTab);
        tabPane.getTabs().add(humidityTab);
        // Add 3rd tab to TabPane here
        tabPane.getTabs().add(windTab);
        
        // Hide grid with tab pane until data is loaded
        grid.setVisible(false);
        
        Scene scene = new Scene(borderPane, 500, 500, Color.RED);
        
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        /**
         * Event handler lambda for Open Data File button. This parses JSON data, puts values in 
         * Weather object and displays info in tabbed interface.
         */
        openDataFileButton.setOnAction((ActionEvent e) -> {
            //Call method for fileChooser
            configureFileChooser(fileChooser);
            
            //Set level to INFO for starting fileChooser
            logger.log(Level.INFO,"Starting File Chooser Dialog");
            
            File weatherData = fileChooser.showOpenDialog(primaryStage);
            
            
            /*==========================================================
            Set logger level to CONFIG for selection of course text file
            ==========================================================*/            
            logger.log(Level.CONFIG,"SELECTED Weather JSON file");
            
            Scanner jsonInput = null;
            try {
                jsonInput = new Scanner(weatherData);
            } catch (FileNotFoundException ex) {
                //System error print and exit system
                System.err.println("Unable to locate file:" + weatherData);
                
                /*==========================================================
                Set logger level to WARNING for file not found
                ==========================================================*/                 
                logger.log(Level.WARNING,"SYSTEM ERROR...Unable to locate file...System exit");                
                
                System.exit(1);
            }
            
           
            // Try block to capture JSON Parsing Exception
            try {
                Weather currentConditions = WeatherParser.parseJsonWeatherData(jsonInput);
                
                logger.log(Level.INFO,"Accessing WeatherParser....Parsing JSON File");
            /** =============================================================
            *   Code to display data from currentConditions object. Learner will need to update
            *   to display data for items on the 3rd tab.
            *  ==============================================================
            */
            primaryStage.setTitle("Weather for " + currentConditions.getLocation());
            // Format to display numbers as Fahrenheit degress with 1 decimal place
            DecimalFormat tempFormat = new DecimalFormat("###.0 °F");
            DecimalFormat baroFormat = new DecimalFormat("##.00\"  hg");
            DecimalFormat windSpeedFormat = new DecimalFormat("## mph");
            DecimalFormat windDirectionFormat = new DecimalFormat("### degrees");
            currentTemperatureDisplay.setText(tempFormat.format(currentConditions.getCurrentTemperatureF()));
            highTemperatureDisplay.setText(tempFormat.format(currentConditions.getHighTemperatureF()));
            lowTemperatureDisplay.setText(tempFormat.format(currentConditions.getLowTemperatureF()));
            pressureDisplay.setText(baroFormat.format(currentConditions.getPressureInHg()));
            humidityDisplay.setText(Integer.toString(currentConditions.getHumidity()) + "%");
            windSpeedDisplay.setText(windSpeedFormat.format(currentConditions.getWindSpeed()));
            windDirectionDisplay.setText(windDirectionFormat.format(currentConditions.getWindDirection()));
            logger.log(Level.INFO,"Display Data to GUI using Weather.jar");
            
        }catch (JsonParsingException ex){
            
                /*==========================================================
                Set logger level to WARNING for JsonParsingException
                ==========================================================*/ 
                logger.log(Level.WARNING, "SYSTEM ERROR...Unable to Parse file located at:  {0}   System exit", weatherData);
                
                System.exit(1);
            }
           
            // Display the grid with the tab pane
            grid.setVisible(true);
            // Remove open file button
            openDataFileButtonBox.getChildren().clear();
            // Add close button
            openDataFileButtonBox.getChildren().add(closeButton);
        }); 
        
        closeButton.setOnAction(e -> {
            logger.log(Level.INFO,"Manual System Shutdown");
            Platform.exit();
        });
    }
    
    // Set FileChooser
    private static void configureFileChooser(FileChooser fileChooser){      

        //Set initial directory to user home.
        fileChooser.setTitle("View Text Files");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        
        //sets filter to display JSON files ONLY
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("JSON Files Only", "*.json")
        );
        logger.log(Level.INFO,"Create FileChooser");
    }
        
    public static void main(String[] args) {
    String logFile = "FileLogger.log";
    
    logger.log(Level.INFO,"Starting Log file");
    try{
        FileHandler fileLogHandler = new FileHandler(logFile);
        logger.addHandler(fileLogHandler);
        
        // Logs All levels to console
        Logger.getLogger("").getHandlers()[0].setLevel(Level.ALL);
        
        // Logs All levels to log file: FileLogger.log
        logger.setLevel(Level.ALL);
    }
    catch(IOException ex){
        System.err.println("Error opening file" + logFile + "for logging.");
        System.exit(1);
        //Set logger level to WARNING for file not found
        logger.log(Level.WARNING,"SYSTEM ERROR...Unable open logging file: FileLogger.log");
    }
    //Set level to INFO for starting GUI
    logger.log(Level.INFO,"Starting JavaFX user interface");
            
        
        launch(args);
    } 
}
