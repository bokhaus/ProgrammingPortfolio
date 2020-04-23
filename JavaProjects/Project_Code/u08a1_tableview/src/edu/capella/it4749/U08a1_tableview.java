/*
 * This program uses two TableViews to first call all available courses and 
 * then display courses in another TableView as the user registers them 
 * to the database. The program tracks total credits and completes validation.
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
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author BrianBok
 */

public class U08a1_tableview extends Application {
    
    // ObservableList declaration
    private ObservableList<Course> registered;
    private ObservableList<Course> courses;
    
    /*Initialize FX class objects*/
    private final Label selectPromptLabel = new Label("Select a course");
    private final TableView<Course> tableAvail = new TableView<>();
    private final Label registeredCoursesPromptLabel = new Label("You are registering for these courses");    
    private final TableView<Course> tableReg = new TableView<>();
    private final Button cancel = new Button("Cancel");        
    private final TextField studentID = new TextField();
    private final Label confirmPromptLabel = new Label("");
    private final Label creditHourPromptLabel = new Label("Total Credit Hours");
    private final Label creditHoursLabel = new Label("");
    private final Button cfirmButton = new Button("Confirm");    
    private final Button exitNormal = new Button("Exit"); 
    private final HBox hbox = new HBox();
    
    //MariaDB login info CONSTANTS. Order NEEDED for constructor (USERNAME, PASSWORD, DB_URL, SQL)
    private static final String USERNAME = "registrar";
    private static final String PASSWORD = "P@ssword";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/registration";    
    
    //Creates logger CONSTANT.   
    static final Logger LOG = Logger.getLogger(U08a1_tableview.class.getName()); 
    
    //Variables
    private Course choice;
    private final int MAX_CREDIT_LOAD = 9;
    private int totalCredit = 0; 
    
    // Query to get list of available courses
    private final String courseSQL = "select course_code, credit_hours from course_offerings order by course_code";
        
    // Query to add registration for a learner in a course
    private final String regSQL = "select learner_registration.course_code, credit_hours " +
                                  "from learner_registration inner join course_offerings "+
                                  "using(course_code) where learner_id =?";
    
    // Query to get current registration for a given learner 
    private final String insertSQL = "insert into learner_registration(course_code, learner_id) "+ 
                                     "values(?, ?)";
        
    // Clear learner_registration table
    private final String deleteSQL = "delete from learner_registration where learner_id = ?";       
            
    @Override
    public void start(Stage primaryStage) {
         
        //GridPane
        GridPane grid = new GridPane();
        
        //Row constraints
        RowConstraints row0 = new RowConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        RowConstraints row4 = new RowConstraints();
        RowConstraints row5 = new RowConstraints();
        
        // Configure row heights
        row0.setPercentHeight(5);
        row1.setPercentHeight(50);
        row2.setPercentHeight(10);
        row3.setPercentHeight(25);
        row4.setPercentHeight(5);
        row5.setPercentHeight(5);
         
        grid.getRowConstraints().addAll(row0, row1,row2, row3, row4, row5);
        
        //Set grid layout
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        
        //column to take FULL width available
        ColumnConstraints constraints = new ColumnConstraints();
        constraints.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().add(constraints);
        
            /*FX objects*/
        //Select Course prompt
        selectPromptLabel.setFont(new Font("Arial", 20));
        GridPane.setConstraints(selectPromptLabel, 0, 0);//column 1, row 1
        
        /* To delete third column use .setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
           Extra space in table column header will be distributed among the columns. 
           In this distribution the columns' max and min widths are taken into account.
           By default the TableView.UNCONSTRAINED_RESIZE_POLICY is used where the tablecolumns 
           will take their preferred width initially.
        */
        
        //New TableView tableAvail
        GridPane.setConstraints(tableAvail,  0, 1);//column 1, row 2
        GridPane.setHalignment(tableAvail, HPos.CENTER); // To align horizontally in the cell
        GridPane.setValignment(tableAvail, VPos.CENTER); // To align vertically in the cell
        tableAvail.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        //Reg courses prompt label -> Header label for courses
        GridPane.setConstraints(registeredCoursesPromptLabel, 0, 2);//column 1, row 3
        GridPane.setHalignment(registeredCoursesPromptLabel, HPos.CENTER); // To align horizontally in the cell
        GridPane.setValignment(registeredCoursesPromptLabel, VPos.CENTER); // To align vertically in the cell
        
        //New TableView tableReg
        GridPane.setConstraints(tableReg,  0, 3);//column 1, row  4
        GridPane.setHalignment(tableReg, HPos.CENTER); // To align horizontally in the cell
        GridPane.setValignment(tableReg, VPos.CENTER); // To align vertically in the cell
        tableReg.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        //TextField for StudentID
        GridPane.setConstraints(studentID, 1, 0);//column 2, row 1
        GridPane.setHalignment(studentID, HPos.CENTER); // To align horizontally in the cell
        GridPane.setValignment(studentID, VPos.CENTER); // To align vertically in the cell
        studentID.setPromptText("Student ID");
        
        //Courses Prompt label * Error message or confirm message
        GridPane.setConstraints(confirmPromptLabel, 1, 1);//column 2 ,row 2
        GridPane.setHalignment(confirmPromptLabel, HPos.CENTER); // To align horizontally in the cell
        GridPane.setValignment(confirmPromptLabel, VPos.CENTER); // To align vertically in the cell
        
        //Labels header for credit hours
        GridPane.setConstraints(creditHourPromptLabel, 1, 2);//column 2, row 3
        GridPane.setHalignment(creditHourPromptLabel, HPos.CENTER); // To align horizontally in the cell
        GridPane.setValignment(creditHourPromptLabel, VPos.CENTER); // To align vertically in the cell
        
        
        //Label stores -> credit hours
        GridPane.setConstraints(creditHoursLabel, 1, 3);//column 2, row 4
        GridPane.setHalignment(creditHoursLabel, HPos.CENTER); // To align horizontally in the cell
        GridPane.setValignment(creditHoursLabel, VPos.CENTER); // To align vertically in the cell
        //creditHoursLabel.setStyle("-fx-background-color: #F8F9F9");
        creditHoursLabel.setFont(new Font("Arial", 20));
        
        
        //Setting the space between the nodes of a HBox pane 
        hbox.setSpacing(10);    
      
        //Setting the margin to the nodes 
        HBox.setMargin(cancel, new Insets(20, 20, 20, 20)); 
        HBox.setMargin(cfirmButton, new Insets(20, 20, 20, 20));  
        GridPane.setConstraints(hbox, 1, 5);//column 1, row 5
        hbox.getChildren().addAll(cancel,cfirmButton);
        
        //Set grid
        grid.getChildren().addAll(selectPromptLabel, tableAvail, registeredCoursesPromptLabel, 
                tableReg, studentID, confirmPromptLabel, creditHourPromptLabel, creditHoursLabel, hbox);
        

        Scene scene = new Scene(grid, 500, 500);
        
        //Apply Style Sheet
        String css = U08a1_tableview.class.getResource("CourseRegistrationCSS.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        primaryStage.setTitle("JavaFX Register for Courses");
        primaryStage.setScene(scene);
        primaryStage.show(); 
        LOG.log(Level.INFO, "Create GUI and show stage");
        
        /******************************************
        * Method call to create TableViews in GUI
        ******************************************/
        //Populates TableView tableAvail
        isAvailableTable();
        LOG.log(Level.INFO,"Query course_offerings table for available courses and displays in TableView tableAvail");
                
        //Populates TableView tableReg
        isRegisteredTable();  
        LOG.log(Level.INFO,"Query learner_registration table for current registered courses displays in tableAvail");
                
        
        // ****************************************************
        // StudentID listener event handler
        // ****************************************************
        
        studentID.setOnAction((ActionEvent e) -> {
            
            //Clear labels
            registeredCoursesPromptLabel.setText("");
            creditHoursLabel.setText("");
            confirmPromptLabel.setText("");
            tableReg.getColumns().clear();//Clears columns so no duplicate columns
            //Populates TableView tableReg
            isRegisteredTable();
            // Clear current registration - delete from DB.
            deleteRegistrations(DB_URL, USERNAME, PASSWORD, studentID.getText());
            
            
            LOG.log(Level.INFO, "CLEAR STUDENT REGISTRATION FOR NEW \tThe parameter: studentID = {0}", studentID.getText());
            
        }); 
        
        // ******************************************************************
        // Confirmation button event handler. Displays registered courses 
        // ******************************************************************
        cfirmButton.setOnAction((ActionEvent e) -> {
            
            //Clear the textLabels
            confirmPromptLabel.setText("");
            registeredCoursesPromptLabel.setText("");
            tableReg.getColumns().clear();
            

            //Change text String to affirm courses are verified.
            registeredCoursesPromptLabel.setText("The following course are CONFIRMED!");
            
            //***Key feature ***
            //Calls method to confirm registered courses
            //Populates TableView tableReg
            isRegisteredTable();
            LOG.log(Level.INFO,"Confirm Button Selected, query registration table for verification of courses");
                
        }); 
        
        // ***************************************
        // Cancel button event handler. 
        // ***************************************
        cancel.setOnMouseClicked(e -> {
            
            registeredCoursesPromptLabel.setText("");
            creditHoursLabel.setText("");
            confirmPromptLabel.setText("");
            
            confirmPromptLabel.setText("Cancelling Registration for: " + studentID.getText());
            
            
            // Clear current registration - delete from DB.
            deleteRegistrations(DB_URL, USERNAME, PASSWORD, studentID.getText());
            
            
            
            //EXIT PROGRAM
            Platform.exit();
            try {
                TimeUnit.SECONDS.sleep(2);
                
                LOG.log(Level.CONFIG,"Exit system - Delete Records");

            } 
            catch (InterruptedException ex) {
                LOG.log(Level.SEVERE, "InterruptedException{0}{1}", new Object[]{ex.getMessage(), ex.getClass()});
            }
            System.exit(0);

        }); 

        // ****************************************************
        // Course combobox event handler
        // ****************************************************
        tableAvail.setOnMouseClicked(e ->{
            //Method for validation of course selection
            validation();
            LOG.log(Level.INFO,"Validate Course Selection");
        });
        
        //***Key feature ***
        //Clears sort and removes duplicate selection
        tableAvail.setOnSort((SortEvent<TableView<Course>> event) -> {
            tableAvail.getSelectionModel().clearSelection();
            LOG.log(Level.INFO,"Clear sort selection");
        });
    }//end JavaFX start method
    
    private  ObservableList<Course> getAvailableCourses(String dbURL, String user, String password) throws SQLException {
        
        //ObservableList for TableView
        ObservableList<Course> courseList = FXCollections.observableArrayList();
        
        //Run query to retrieve course_code and credit_hours
        try(Connection conn = DriverManager.getConnection(dbURL, user, password)){
            Statement stmt = conn.createStatement();
            LOG.log(Level.INFO, " Connection is valid: {0}", conn.isValid(0));
            
            // Display insert statement in log - useful for tracing/debugging
            LOG.log(Level.INFO, "JDBC Insert SQL: {0}", stmt.toString());
            
            if(stmt != null) {
                ResultSet result = stmt.executeQuery(courseSQL);
                while(result.next()) {
                    String code = result.getString("course_code");
                    int credits = result.getInt("credit_hours");
                    courseList.add(new Course(code, credits));
                }
            }
            else {
                LOG.log(Level.WARNING, "Statement in getAvailableCourses cannot be null.");
            } 
            return courseList;
        }
    } // end of getAvailableCourses();

    private void isAvailableTable(){
        /***********************************************************************
        *   Get list of available courses
        *   creating courseList ObservableList object called courses and passing 
        *   parameters for method call. 
        *   Set columns in TableView tableAvail
        ***********************************************************************/
        try{
            courses = getAvailableCourses(DB_URL, USERNAME, PASSWORD);
            LOG.log(Level.INFO,"Call getAvailableCourses method for query");
            
            tableAvail.setEditable(false);
 
            TableColumn courseCol = new TableColumn("Course");
            courseCol.setMinWidth(100);
            courseCol.setCellValueFactory(
                    new PropertyValueFactory<Course, String>("code"));
            
            //Set first column data in cell alignment 
            //Can also be "-fx-alignment: CENTER-RIGHT;" or "CENTER-LEFT" OR LEFT BLANK
            courseCol.setStyle( "-fx-alignment: CENTER;");

            TableColumn creditCol = new TableColumn("Credit Hours");
            creditCol.setMinWidth(100);
            creditCol.setCellValueFactory(
                    new PropertyValueFactory<Course, String>("credits"));
            
            //Set second column data in cell alignment
            creditCol.setStyle( "-fx-alignment: CENTER;");

            tableAvail.setItems(courses);
            tableAvail.getColumns().addAll(courseCol, creditCol);
 
            LOG.log(Level.INFO,"Populate TableView \t Two columns 'Course' and 'Credit Hours'");
            
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
    }//End available method    
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
    
    private void validation(){
        int index = tableAvail.getSelectionModel().getSelectedIndex();
        if(index > -1 ){
            if(totalCredit < MAX_CREDIT_LOAD) {
                    choice = tableAvail.getSelectionModel().getSelectedItem();
                    if(!choice.getIsRegisteredFor()) {
                        try {
                            choice.setIsRegisteredFor(true);
                            totalCredit += choice.getCredits();
                            creditHoursLabel.setText(Integer.toString(totalCredit));
                            confirmPromptLabel.setText("You have selected...\ncourse " + choice.getCode());
                            registered.add(new Course(choice.getCode(), choice.getCredits()));    
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
        }
       
    }//End Validation Method
    
    // Method to return list of registered courses for an ID in registration table
    private  ObservableList<Course> getRegisteredCourses(String dbURL, String user, String password, String id) throws SQLException {
        
        //Convert Arraylist to ObservableList
        ObservableList<Course> registered = FXCollections.observableArrayList();
        try(Connection conn = DriverManager.getConnection(dbURL, user, password)){
            PreparedStatement stmt = conn.prepareStatement(regSQL);
            stmt.setString(1, id);
            ResultSet result = stmt.executeQuery();
            
            // Display insert statement in log - useful for tracing/debugging
            LOG.log(Level.INFO, "JDBC Insert SQL: {0}", stmt.toString());

            LOG.log(Level.INFO, "Results of registration query ready: {0}", result.isBeforeFirst());
            
            while(result.next()) {
                String code = result.getString("course_code");
                int credits = result.getInt("credit_hours");
                registered.add(new Course(code, credits));

            }
            return registered;
        }
    }//END getRegisteredCourses()

    private void isRegisteredTable(){
        /***********************************************************************
        *   Get list of available courses
        *   creating courseList ObservableList object called registered and passing 
        *   parameters for method call. 
        *   Set columns in TableView tableReg
        ***********************************************************************/   
        try{
            // Get list of registered courses for display
            registered = getRegisteredCourses(DB_URL, USERNAME, PASSWORD, studentID.getText());

            //Populate registered courses TableView
            tableReg.setEditable(false);

            TableColumn courseRegCol = new TableColumn("Registered Course");
            courseRegCol.setMinWidth(100);
            courseRegCol.setCellValueFactory(
                    new PropertyValueFactory<Course, String>("code"));

            //Set first column data in cell alignment 
            //Can also be "-fx-alignment: CENTER-RIGHT;" or "CENTER-LEFT" OR LEFT BLANK
            courseRegCol.setStyle( "-fx-alignment: CENTER;");

            TableColumn creditRegCol = new TableColumn("Registered Credit Hours");
            creditRegCol.setMinWidth(100);
            creditRegCol.setCellValueFactory(
                    new PropertyValueFactory<Course, String>("credits"));

            //Set second column data in cell alignment
            creditRegCol.setStyle( "-fx-alignment: CENTER;");

            tableReg.setItems(registered);
            tableReg.getColumns().addAll(courseRegCol, creditRegCol);

            LOG.log(Level.INFO,"Populate TableView \t Two columns 'Course' and 'Credit Hours'");                

        }
        catch (SQLException ex){
            LOG.log(Level.SEVERE, "SQLException{0}{1}{2}", new Object[]{ex.getMessage(), ex.getClass(), ex.getCause()});
        }
    }//end registered method

    // Method to clear registration table. Called when program starts and through a button to EXIT system
    private void deleteRegistrations(String dbURL, String user, String password, String userID)  {
        
        try(Connection conn = DriverManager.getConnection(dbURL, user, password)){
           LOG.log(Level.INFO, "Connection status {0}", conn.isValid(0));
           
           PreparedStatement stmt = conn.prepareStatement(deleteSQL);
           
           // Display insert statement in log - useful for tracing/debugging
           LOG.log(Level.INFO, "JDBC Insert SQL: {0}", stmt.toString());
            
           stmt.setString(1, userID);
           int deletedRows = stmt.executeUpdate();
           LOG.log(Level.INFO, "{0} registrations deleted.", deletedRows);
        } catch (SQLException ex) {
            Logger.getLogger(U08a1_tableview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//End deletRegistration Method

        
    public static void main(String[] args) {
        
        //Format the logger in both log file and console
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        
        //FileHandler for logger
        try{
            FileHandler fileHandler = new FileHandler("Unit8_TableView_fileLog.log");
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
        LOG.log(Level.INFO, "****** Program Start ******");
        LOG.log(Level.INFO, "Creating logger for program.");
        
        launch(args);
    }//end of main method    
}//end program

/* ************REFERENCE************
 * Remember that the call to start() in main() starts the JavaFX application. 
 * You can set up the logging in main() before calling start(), but the JavaFX user 
 * interface does not exist until the code in start() runs. 
 * You need to fetch the info from the database in start().   
 * The program flow doesn't return to main() until the program ends. 
 */