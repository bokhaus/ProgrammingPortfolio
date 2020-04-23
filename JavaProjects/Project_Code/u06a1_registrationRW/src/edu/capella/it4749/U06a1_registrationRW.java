/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.capella.it4749;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLInvalidAuthorizationSpecException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



/**
 *
 * @author BrianBok
 */

public class U06a1_registrationRW extends Application {
    
    //MariaDB login info CONSTANTS. Order NEEDED for constructor (USERNAME, PASSWORD, DB_URL, SQL)
    private static final String USERNAME = "registrar";
    private static final String PASSWORD = "P@ssword";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/registration";    
            
    //FX objects
    GridPane grid = new GridPane();
    Label selectPromptLabel = new Label("Please select a course for which you want to register");
    ComboBox<Course> coursesComboBox = new ComboBox<>();   
    Label confirmPromptLabel = new Label("");
    Label registeredCoursesPromptLabel = new Label("You are registering for the following courses");
    Label creditHourPromptLabel = new Label("Current total Credit Hours");            
    Label registeredCoursesLabel = new Label("");
    Label creditHoursLabel = new Label("");
    Label dataFilePathLabel = new Label("");
    Label inputStudentId = new Label("Enter Student ID and Press ENTER.");
    
    
    //Creates logger CONSTANT.   
    static final Logger LOG = Logger.getLogger(U06a1_registrationRW.class.getName()); 
    
    //ConfirmButton
    final Button cfirmButton = new Button("Confirm Registration"); 
    //ExitButton
    final Button exitButton = new Button("EXIT \nDo NOT save");
    //TextField for StudentID
    final TextField studentID = new TextField("Enter Student ID"); 

    
    //Variables
    Course choice;
    final int MAX_CREDIT_LOAD = 9;
    int totalCredit = 0; 
    
    
    // Query to get list of available courses
    //***COMPLETE
    private final String courseSQL = "select course_code, credit_hours from course_offerings order by course_code";
        
    // Query to add registration for a learner in a course
    //***NEEDS WORK
    private final String regSQL = "select learner_registration.course_code, credit_hours " +
                                  "from learner_registration inner join course_offerings "+
                                  "using(course_code) where learner_id =?";
    
    // Query to get current registration for a given learner
    //***COMPLETE   
    private final String insertSQL = "insert into learner_registration(course_code, learner_id) "+ 
                                     "values(?, ?)";
        
    // Clear learner_registration table
    //***COMPLETE 
    private final String deleteSQL = "delete from learner_registration where learner_id = ?";       
            
    public static void main(String[] args) {
        
        //Format the logger in both log file and console
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        
        //FileHandler for logger
        try{
            FileHandler fileHandler = new FileHandler("fileLog.log");
            fileHandler.setFormatter(simpleFormatter);
            LOG.addHandler(fileHandler);
            
            //Logger for log file --> weather.log. Records all levels
            LOG.setLevel(Level.ALL);
            
            //Logger for console --> ONLY records Level.SEVERE
            Logger.getLogger("").getHandlers()[0].setLevel(Level.SEVERE);
            
        //multi-catch block   
        } catch (IOException | SecurityException ex) {
            LOG.log(Level.SEVERE,ex.getMessage());
            System.exit(1);
        }
        LOG.log(Level.INFO, "Creating logger for program.");
        
        launch(args);
    }//end of main method    
    
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

        // add button for Confirmation
        grid.add(cfirmButton, 1, 5);

        // add button for Exit
        grid.add(exitButton,0 , 5);
        
        // add textfield
        grid.add(studentID,1,1);
        studentID.setText("");
        
        // add label to display path to course data file
        grid.add(dataFilePathLabel, 0, 1);
        GridPane.setHalignment(dataFilePathLabel, HPos.LEFT);
        grid.add(selectPromptLabel, 0, 2);
        GridPane.setHalignment(selectPromptLabel, HPos.LEFT);
        grid.add(inputStudentId, 1, 0);
        GridPane.setHalignment(inputStudentId, HPos.LEFT);
        
        // Show select course prompt 
        selectPromptLabel.setVisible(true);
        
        // configure & add combobox for available courses
        coursesComboBox.setMaxWidth(Double.MAX_VALUE);
        grid.add(coursesComboBox, 0, 3);
        GridPane.setValignment(coursesComboBox, VPos.TOP);
        
        // show combobox 
        coursesComboBox.setVisible(true);

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
        // StudentID listener event handler
        // ****************************************************
        
        studentID.setOnAction((ActionEvent e) -> {
            
            dataFilePathLabel.setText("The Student ID entered was " + studentID.getText() +".");
            
            try {
                // Clear current registration - delete from DB.
                deleteRegistrations(DB_URL, USERNAME, PASSWORD, studentID.getText());
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, "SQLException{0}{1}{2}", new Object[]{ex.getMessage(), ex.getClass(), ex.getCause()});
            }
            
            LOG.log(Level.INFO, " The parameter: studentID = {0}", studentID.getText());
            
        }); 
        
        // ******************************************************************
        // Confirmation button event handler. Displays registered courses 
        // ******************************************************************
        cfirmButton.setOnAction((ActionEvent e) -> {
            
            //Clear the textLabels
            registeredCoursesLabel.setText("");
            creditHoursLabel.setText("");
            confirmPromptLabel.setText("");
            registeredCoursesPromptLabel.setText("");
            creditHourPromptLabel.setText("");
            
            
            //Change text String to affirm courses are verified.
            registeredCoursesPromptLabel.setText("The following course are CONFIRMED!");
            
            // ArrayList to hold registered courses
            ArrayList<Course> registeredCourses = null;
            
            try{
                // Get list of registered courses for display
                registeredCourses = getRegisteredCourses(DB_URL, USERNAME, PASSWORD, studentID.getText());
                
                //StringBuilder to iterate over registeredCourses ArrayList
                StringBuilder out = new StringBuilder();
                
                for(Course g : registeredCourses) {
                    
                   out.append(g.getCode()).append(" (").append(g.getCredits()).append(")\n");
                }
                registeredCoursesLabel.setText(out.toString());
                
                LOG.log(Level.INFO, "Iterate ArrayList \tparameter: StudentID = {0}", studentID.getText());
                
            }
            catch (SQLException ex){
                LOG.log(Level.SEVERE, "SQLException{0}{1}{2}", new Object[]{ex.getMessage(), ex.getClass(), ex.getCause()});
            }
            
        }); 
        
        // ***************************************
        // Exit button event handler. 
        // ***************************************
        exitButton.setOnAction((ActionEvent e) -> {
            
            registeredCoursesLabel.setText("");
            creditHoursLabel.setText("");
            confirmPromptLabel.setText("");
            
            try {
                // Clear current registration - delete from DB.
                deleteRegistrations(DB_URL, USERNAME, PASSWORD, studentID.getText());
                
                //EXIT PROGRAM
                Platform.exit();
                try {
                    TimeUnit.SECONDS.sleep(1);
                    LOG.log(Level.CONFIG,"Exit system - Delete Records");
                    
                } catch (InterruptedException ex) {
                    LOG.log(Level.SEVERE, "InterruptedException{0}{1}", new Object[]{ex.getMessage(), ex.getClass()});
                }
                System.exit(0);
            
                } catch (SQLException ex) {
                    LOG.log(Level.SEVERE, "SQLException{0}{1}{2}", new Object[]{ex.getMessage(), ex.getClass(), ex.getCause()});
                }
                
        }); 
        
        
        
        // ****************************************************
        // Course combobox event handler
        // ****************************************************
        coursesComboBox.setOnAction((ActionEvent e) -> {
             if(totalCredit < MAX_CREDIT_LOAD) {     
                choice = coursesComboBox.getValue();
                if(!choice.getIsRegisteredFor()) {
                    try {
                        choice.setIsRegisteredFor(true);
                        String registeredCourses = registeredCoursesLabel.getText();
                        registeredCourses += choice + "\n";
                        registeredCoursesLabel.setText(registeredCourses);
                        totalCredit += choice.getCredits();
                        creditHoursLabel.setText(Integer.toString(totalCredit));
                        confirmPromptLabel.setText("You have selected...\ncourse " + choice.getCode());
                        
                        //Confirm connection is OK
                        boolean registrationOK = registerForCourse(DB_URL, USERNAME, PASSWORD, studentID.getText(), choice.getCode());
                        if(!registrationOK) {
                            LOG.log(Level.WARNING, "Error writing registration to database.");
                        }
                        //Set level to INFO for course selection
                        LOG.log(Level.INFO,"SELECTED course to register");
                    } 
                    catch (SQLException ex) {
                        LOG.log(Level.SEVERE, "SQLException{0}{1}{2}", new Object[]{ex.getMessage(), ex.getClass(), ex.getCause()});
                    }
                }
                else {
                    confirmPromptLabel.setText("**Invalid**\nYou have already \nregistered for " + choice.getCode());
                    //Set level to Severe for attempting to register for the same class twice
                    LOG.log(Level.SEVERE,"Attempted duplicate registration");
                }
            }
            else {
                confirmPromptLabel.setText("**Invalid**\nYou cannot register for \nmore than 9 credits.");
                    
                //Set level to Severe for attempting to register for more than 9 credit hours
                LOG.log(Level.SEVERE,"Attempted registration for more than 9 credit hours");
            }
        });
  
        // Get list of available courses
        //creating future object called courses and passing parameters for method call
        try{
            Future<ArrayList<Course>> courses = queryDatabaseAsync(DB_URL, USERNAME, PASSWORD);
            
            LOG.log(Level.INFO,"Call Async method for thread query");
            
            //FOR loop to iterate all course data for database populate to courseComboBox
            try{
                for(Course c : courses.get()){
                coursesComboBox.getItems().add(new Course(c.getCode(), c.getCredits()));
                }
            } 
            catch (InterruptedException | ExecutionException ex) {
                LOG.log(Level.WARNING, "Asynchronous call error. Exiting system: {0}", ex.getMessage());
                
                System.exit(1);
            }
        }
        catch(SQLInvalidAuthorizationSpecException ex) {
            LOG.log(Level.SEVERE, "Invalid DB login.{0}{1}", new Object[]{ex.getMessage()});
        }
        catch(SQLNonTransientConnectionException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }
        catch(SQLSyntaxErrorException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }
        catch(SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }
    }//end JavaFX start method
    
    private  ArrayList<Course> getAvailableCourses(String dbURL, String user, String password) throws SQLException {
        ArrayList<Course> courses = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(dbURL, user, password)){
            Statement stmt = conn.createStatement();
            LOG.log(Level.INFO, " Connection is valid: {0}", conn.isValid(0));
            if(stmt != null) {
                ResultSet result = stmt.executeQuery(courseSQL);
                while(result.next()) {
                    String code = result.getString("course_code");
                    int credits = result.getInt("credit_hours");
                    courses.add(new Course(code, credits));
                }
            }
            else {
                LOG.log(Level.WARNING, "Statement in getAvailableCourses cannot be null.");
            } 
            return courses;
        }
    } // end of getAvailableCourses();
    
    private  Future<ArrayList<Course>> queryDatabaseAsync(String dbURL, String user, String password) throws SQLException {
        CompletableFuture<ArrayList<Course>> result = new CompletableFuture<>();
        
        new Thread(()->{
            
            try {
                result.complete(getAvailableCourses(dbURL, user, password));
                
                
            } catch (SQLException ex) {
                Logger.getLogger(U06a1_registrationRW.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }).start();
        LOG.log(Level.INFO, "Method Start....");
        return result;
    }//end Async method

    // Method writes data registration in database.
    private  boolean registerForCourse(String dbURL, String user, String password, String id, String course) throws SQLException {
        
        try(Connection conn = DriverManager.getConnection(dbURL, user, password)){
            
            LOG.log(Level.INFO, "Connection status {0}", conn.isValid(0));
           
            // Turn off autocommit in DB so problems can be rolled back
            conn.setAutoCommit(false);
            LOG.log(Level.CONFIG, "AutoCommit OFF {0}");

            boolean status = true;

            PreparedStatement stmt = conn.prepareStatement(insertSQL);
            stmt.setString(1, course);
            stmt.setString(2, id);

            // Display insert statement in log - useful for tracing/debugging
            LOG.log(Level.INFO, "JDBC Insert SQL: {0}", stmt.toString());

            int addedRowCount = stmt.executeUpdate();
            LOG.log(Level.INFO, "Rows added: {0}", addedRowCount);

            // Only 1 row should be added - 0 or more than 1 points to a problem
            if(addedRowCount != 1) {
                LOG.log(Level.WARNING, "Incorrect # of rows added: {0}", addedRowCount);
                LOG.log(Level.CONFIG, "AutoCommit OFF {0}");

                // If there was a problem, rollback database change
                conn.rollback();
                LOG.log(Level.WARNING, "Rollback database change {0}");

                status = false;
            }
            else {
                // If only 1 row was added, no error, commit DB change
                conn.commit();
                LOG.log(Level.INFO, "DB Insert committed.");
            }
            // Turn autocommit back on at end of method
            conn.setAutoCommit(true);
            LOG.log(Level.CONFIG, "AutoCommit ON {0}");
        
        return status;
        }
    } // end of registerForCourse()
    
    // Method to return list of registered courses for an ID in registration table
    private  ArrayList<Course> getRegisteredCourses(String dbURL, String user, String password, String id) throws SQLException {
        ArrayList<Course> registered = new ArrayList<>();
        
        try(Connection conn = DriverManager.getConnection(dbURL, user, password)){
            PreparedStatement stmt = conn.prepareStatement(regSQL);
            stmt.setString(1, id);
            ResultSet result = stmt.executeQuery();

            LOG.log(Level.INFO, "Results of registration query ready: {0}", result.isBeforeFirst());
            
            while(result.next()) {
                String code = result.getString("course_code");
                int credits = result.getInt("credit_hours");
                registered.add(new Course(code, credits));

            }
            return registered;
        }
    }//END getRegisteredCourses()
    
    // Method to clear registration table. Called when program starts and through a button to EXIT system
    private void deleteRegistrations(String dbURL, String user, String password, String userID) throws SQLException {
        
        try(Connection conn = DriverManager.getConnection(dbURL, user, password)){
           LOG.log(Level.INFO, "Connection status " + conn.isValid(0));
           
           PreparedStatement stmt = conn.prepareStatement(deleteSQL);
           stmt.setString(1, userID);
           int deletedRows = stmt.executeUpdate();
           LOG.log(Level.INFO, "{0} registrations deleted.", deletedRows);
        }
    }
}//end program

/* ************REFERENCE************
 * Remember that the call to start() in main() starts the JavaFX application. 
 * You can set up the logging in main() before calling start(), but the JavaFX user 
 * interface does not exist until the code in start() runs. 
 * You need to fetch the info from the database in start().   
 * The program flow doesn't return to main() until the program ends. 
 */