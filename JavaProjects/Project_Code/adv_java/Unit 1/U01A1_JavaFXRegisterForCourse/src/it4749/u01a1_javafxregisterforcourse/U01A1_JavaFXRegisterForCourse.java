package it4749.u01a1_javafxregisterforcourse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.RowConstraints;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;

/**
 *
 * @author BrianBok
 * 
 *  Use the following code as a starting point and add logging using a FileHandler so that log information 
 *  about the program is written to a log file. Add logging at 3 levels that include messages written when an 
 *  exception is thrown, messages written when the program is handling a user error, and messages that trace 
 *  the execution of the code. 
 * 
 *  You should also use the complete the configureFileChooser() method. 
 * 
 *  The program should read the course.data.txt file to load the available course data.  
 */
public class U01A1_JavaFXRegisterForCourse extends Application {
    GridPane grid = new GridPane();
    Label selectPromptLabel = new Label("Please select a course for which you want to register");
    ComboBox<Course> coursesComboBox = new ComboBox<>();   
    Label confirmPromptLabel = new Label("");
    Label registeredCoursesPromptLabel = new Label("You are currently registered for");
    Label creditHourPromptLabel = new Label("Current total Credit Hours");            
    Label registeredCoursesLabel = new Label("");
    Label creditHoursLabel = new Label("0");
    Label dataFilePathLabel = new Label("");

    // FileChooser & button that opens FileChooser
    final FileChooser fileChooser = new FileChooser();    
    final Button openButton = new Button("Open course data file ...");
    //Logger for this application
    private static final Logger logger = Logger.getLogger(U01A1_JavaFXRegisterForCourse.class.getName());
    
    Course choice;
    final int MAX_CREDIT_LOAD = 9;
    int totalCredit = 0;     
           
    @Override
    public void start(Stage primaryStage) {
        RowConstraints row0 = new RowConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        RowConstraints row4 = new RowConstraints();
        RowConstraints row5 = new RowConstraints();
        
        // Configure row heights
        row0.setPercentHeight(10);
        row1.setPercentHeight(5);
        row2.setPercentHeight(10);
        row3.setPercentHeight(30);
        row4.setPercentHeight(10);
        row5.setPercentHeight(35);
         
        grid.getRowConstraints().addAll(row0, row1,row2, row3, row4, row5);

        grid.setAlignment(Pos.CENTER);

        grid.setHgap(5);
        grid.setVgap(5);
         
        // add button for FileChooser
        grid.add(openButton, 0, 0);
        // add lable to display path to course data file
        grid.add(dataFilePathLabel, 0, 1);
        GridPane.setHalignment(dataFilePathLabel, HPos.LEFT);
        grid.add(selectPromptLabel, 0, 2);
        GridPane.setHalignment(selectPromptLabel, HPos.LEFT);
        // hide select course prompt until combobox is populated & displayed
        selectPromptLabel.setVisible(false);
        
        // configure & add combobox for available courses
        coursesComboBox.setMaxWidth(Double.MAX_VALUE);
        grid.add(coursesComboBox, 0, 3);
        GridPane.setValignment(coursesComboBox, VPos.TOP);
        // hide combobox until user has selected data file to populate it
        coursesComboBox.setVisible(false);

        grid.add(confirmPromptLabel, 1, 3);  
        GridPane.setHalignment(confirmPromptLabel, HPos.LEFT);
        GridPane.setValignment(confirmPromptLabel, VPos.TOP);
        
        grid.add(registeredCoursesPromptLabel, 0, 4);  
        GridPane.setHalignment(registeredCoursesPromptLabel, HPos.LEFT);
        GridPane.setValignment(registeredCoursesPromptLabel, VPos.TOP);
        
        grid.add(creditHourPromptLabel, 1, 4);  
        GridPane.setHalignment(creditHourPromptLabel, HPos.LEFT);   
        GridPane.setValignment(creditHourPromptLabel, VPos.TOP);
         
        grid.add(registeredCoursesLabel, 0, 5);
        GridPane.setHalignment(registeredCoursesLabel, HPos.LEFT);   
        GridPane.setValignment(registeredCoursesLabel, VPos.TOP);
        registeredCoursesLabel.setStyle("-fx-background-color: #fff600;");
  
        grid.add(creditHoursLabel, 1, 5); 
        GridPane.setHalignment(creditHoursLabel, HPos.LEFT);   
        GridPane.setValignment(creditHoursLabel, VPos.TOP);
        creditHoursLabel.setStyle("-fx-background-color: #fff600;");
         
        Scene scene = new Scene(grid, 500, 500, Color.RED);
        
        primaryStage.setTitle("JavaFX Register for Courses");
        primaryStage.setScene(scene);
        primaryStage.show();
        // ****************************************************
        // Course combobox event handler
        // ****************************************************
        coursesComboBox.setOnAction(e -> {
             if(totalCredit < MAX_CREDIT_LOAD) {     
                choice = coursesComboBox.getValue();
                if(!choice.getIsRegisteredFor()) {
                    choice.setIsRegisteredFor(true);
                    String registeredCourses = registeredCoursesLabel.getText();
                    registeredCourses += choice + "\n";
                    registeredCoursesLabel.setText(registeredCourses);
                    totalCredit += choice.getCredits();
                    creditHoursLabel.setText(Integer.toString(totalCredit));
                    confirmPromptLabel.setText("Registration confirmed for\n course " + choice.getCode());
                    
                    //Set level to INFO for course selection
                    logger.log(Level.INFO,"SELECTED course to register");
                }
                else {
                    confirmPromptLabel.setText("**Invalid**\nYou have already \nregistered for " + choice.getCode());
                    
                    //Set level to Severe for attempting to register for the same class twice
                    logger.log(Level.SEVERE,"Attempted duplicate registration");
                }
             }
             else {
                    confirmPromptLabel.setText("**Invalid**\nYou cannot register for \nmore than 9 credits.");
                    
                    //Set level to Severe for attempting to register for more than 9 credit hours
                    logger.log(Level.SEVERE,"Attempted registration for more than 9 credit hours");
             }
                               
        });
        
        // ******************************************************************
        // Open course data file button event handler. Displays FileChooser. 
        // ******************************************************************
        openButton.setOnAction((ActionEvent e) -> {
            // Clear any existing items in courses combobox
            coursesComboBox.getItems().clear();
            configureFileChooser(fileChooser);
            File file = fileChooser.showOpenDialog(primaryStage);
            dataFilePathLabel.setText(file.getAbsolutePath());
            
            /*==========================================================
            Set logger level to CONFIG for selection of course text file
            ==========================================================*/            
            logger.log(Level.CONFIG,"SELECTED course text file");
            
            Scanner dataReader;
            try {
                dataReader = new Scanner(file);
                while(dataReader.hasNextLine()) {
                    /* Trim off newline & split each line on comma. First half is course number, second half is credit hours.
                        Need to convert string with digit to integer.
                    */
                    String courseInfo[] = dataReader.nextLine().trim().split(",");
                    coursesComboBox.getItems().add(new Course(courseInfo[0], Integer.parseInt(courseInfo[1])));
                }
            } catch (FileNotFoundException ex) {
                
                //System error print and exit system
                System.err.println("Unable to locate file:" + file);
                System.exit(1);
                
                /*==========================================================
                Set logger level to WARNING for file not found
                ==========================================================*/                 
                logger.log(Level.WARNING,"SYSTEM ERROR...Unable to locate file...System exit");
            
            } catch (ArrayIndexOutOfBoundsException ex) {
                
                //System error print and exit system
                System.err.println("Unable to locate an element of array "
                        + "that does not exist or is not valid");
                System.exit(1);
                
                /*==========================================================
                Set logger level to WARNING for invalid element of array
                ==========================================================*/                 
                logger.log(Level.WARNING,"ArrayIndexOutOfBoundsException...System exit");
            }
            
            // Display courses combobox and prompt label
            coursesComboBox.setVisible(true);
            selectPromptLabel.setVisible(true);
        });    
    }
   
    private static void configureFileChooser(final FileChooser fileChooser){      

        //Set initial directory to user home.
        fileChooser.setTitle("View Text Files");
        fileChooser.setInitialDirectory(
            new File(System.getProperty("user.home"))
        );
        
        //sets filter to display text files ONLY
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Text Files Only", "*.txt")
        );
        
    }
    
    public static void main(String[] args) throws IOException {
    String logFile = "FileLogger.log";
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
    //Set level to INFO for starting Course Registration GUI
    logger.log(Level.INFO,"Starting JavaFX user interface");
    
    launch(args);
    } 
}
