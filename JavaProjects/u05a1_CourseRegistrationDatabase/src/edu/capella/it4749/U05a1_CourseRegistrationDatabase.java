/*
 * This program is designed to read a query from a MariaDB and populate a drop down 
 * box with the query data. The user may select courses to register for during a quarter
 */

package edu.capella.it4749;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.RowConstraints;
import javafx.scene.control.ComboBox;

/**
 *
 * @author User
 */
public class U05a1_CourseRegistrationDatabase extends Application {
    
    GridPane grid = new GridPane();
    Label selectPromptLabel = new Label("Please select a course for which you want to register");
    ComboBox<Course> coursesComboBox = new ComboBox<>();   
    Label confirmPromptLabel = new Label("");
    Label registeredCoursesPromptLabel = new Label("You are currently registered for");
    Label creditHourPromptLabel = new Label("Current total Credit Hours");            
    Label registeredCoursesLabel = new Label("");
    Label creditHoursLabel = new Label("0");
    Label dataFilePathLabel = new Label("");

    
    //Creates logger CONSTANT variable.   
    static final Logger LOG = Logger.getLogger(U05a1_CourseRegistrationDatabase.class.getName());  
    
  
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
         

        // add lable to display path to course data file
        grid.add(dataFilePathLabel, 0, 1);
        GridPane.setHalignment(dataFilePathLabel, HPos.LEFT);
        grid.add(selectPromptLabel, 0, 2);
        GridPane.setHalignment(selectPromptLabel, HPos.LEFT);
        
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
                    LOG.log(Level.INFO,"SELECTED course to register");
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

        // SQL query to select course codes and credit hours, sorted by course code
        String sql = "select course_code, credit_hours from course_offerings order by course_code";
        
        //creating future object called courses and passing parameters for method call
        Future<ArrayList<Course>> courses = queryDatabaseAsync("registrar","P@ssword",
                "jdbc:mariadb://localhost:3306/registration",sql );
        
        LOG.log(Level.INFO,"Call Async method for thread query");
        
        
        //printing statement to show async call
        while(!courses.isDone()) {
            System.out.println("Waiting...");
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException ex){
                LOG.log(Level.INFO, "Error in call to Thread.sleep");
            }
        }
        
        //for loop to iterate all course data for database
        try{
            for(Course c : courses.get()){
            coursesComboBox.getItems().add(new Course(c.getCode(), c.getCredits()));  
            }
            LOG.log(Level.INFO, "Populate courseComboBox");
        } catch (InterruptedException | ExecutionException ex) {
            LOG.log(Level.WARNING, "Asynchronous call error. Exiting system: " + ex.getMessage());
            System.exit(1);
            
        }
    
    }//end JavaFX start method

    private static ArrayList<Course> queryDatabase(String user, String password,String dbURL, String sql ){
        ArrayList<Course> courses = new ArrayList<Course>();
        Statement stmt = null;
        ResultSet result = null;
        
        //closes code after execution
        try(Connection conn = DriverManager.getConnection(dbURL, user, password)){
           LOG.log(Level.INFO, "Connection status " + conn.isValid(0));
           stmt = conn.createStatement();
           if(stmt != null){
               //Sql result set
               result = stmt.executeQuery(sql);
               //process the result using while loop
               //As long as next returns true the loop will keep running
               while(result.next()){//step through result set with columns in result set
                   String code = result.getString("course_code");//pulls value from course_code column
                   int credit = result.getInt("credit_hours");
                   
                   //constructor call --> course object
                   courses.add(new Course(code, credit));
   
                }
           }
        }                

        catch(SQLInvalidAuthorizationSpecException ex){
            LOG.log(Level.SEVERE, "Access denied for login"+ user);
        }
        catch(SQLNonTransientConnectionException ex){
            LOG.log(Level.SEVERE, "Cannot connect to server" + dbURL + ex.getMessage());
        }
        catch(SQLSyntaxErrorException ex) {
            LOG.log(Level.SEVERE, "SQL Syntax error: " + ex.getClass()+ "-" + ex.getMessage());
        }
        catch(SQLException ex){
            LOG.log(Level.SEVERE, "SQL Exception error: " + ex.getClass()+ "-" + ex.getMessage());
        }
        return courses;
    }//end of sync method
    
    private static Future<ArrayList<Course>> queryDatabaseAsync(String user, String password, String dbURL, String sql){
        CompletableFuture<ArrayList<Course>> result = new CompletableFuture<>();
        new Thread(()->{
            result.complete(queryDatabase(user, password, dbURL, sql));
            
        }).start();
        LOG.log(Level.INFO, "Method Start...Passing user input to method through parameter.");
        return result;
    }//end Async method
    
    public static void main(String[] args) {
   
        //Format the logger in both log file and console
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        
        
        //FileHandler for logger
        try{
            FileHandler fileHandler = new FileHandler("u05A1_JavaFXRegister.log");
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
}//end program

/* ************FUTURE REFERENCE************
 * Remember that the call to start() in main() starts the JavaFX application. 
 * You can set up the logging in main() before calling start(), but the JavaFX user 
 * interface does not exist until the code in start() runs. 
 * You need to fetch the info from the database in start().   
 * The program flow doesn't return to main() until the program ends. 
 */