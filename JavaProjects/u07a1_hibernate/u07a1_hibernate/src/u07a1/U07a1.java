package u07a1;

import java.io.IOException;
import java.util.List;
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author BrianBok
 */
public class U07a1 extends Application {
    
    
    //Creates logger CONSTANT.   
    static final Logger LOG = Logger.getLogger(U07a1.class.getName()); 
    
    final Button exitButton = new Button("EXIT");
     
   
    // *******************************************
    // Set up Hibernate access
    // *******************************************
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CourseRegistrationService");
    EntityManager em = emf.createEntityManager();
    CourseRegistrationService service = new CourseRegistrationService(em);
    
   // Code for JavaFX application 
    GridPane grid = new GridPane();
    Label learnerIDLabel = new Label("Learner ID: ");
    TextField learnerIDField = new TextField();
    ComboBox<Course> coursesComboBox = new ComboBox<>();   
    Label confirmPromptLabel = new Label("");
    Label registeredCoursesPromptLabel = new Label("You are currently registered for");
    Label creditHourPromptLabel = new Label("Current total Credit Hours");            
    Label registeredCoursesLabel = new Label("");
    Label creditHoursLabel = new Label("0");
    Label dataFilePathLabel = new Label("");
        
           
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
        
        // add button for Exit
        grid.add(exitButton,0 , 5);
        
        grid.add(learnerIDLabel, 0, 0);
        GridPane.setHalignment(learnerIDLabel, HPos.LEFT);
        grid.add(learnerIDField, 1, 0);
        
        // configure & add combobox for available courses
        coursesComboBox.setMaxWidth(Double.MAX_VALUE);
        grid.add(coursesComboBox, 0, 2);
        GridPane.setValignment(coursesComboBox, VPos.TOP);
        
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
        
        //Exit button to leave system
        exitButton.setOnAction((ActionEvent e) -> {
            
            registeredCoursesLabel.setText("");
            creditHoursLabel.setText("");
            confirmPromptLabel.setText("");
            
            //EXIT PROGRAM
            Platform.exit();
            try {
                TimeUnit.SECONDS.sleep(1);
                LOG.log(Level.CONFIG,"Exiting system");

            } catch (InterruptedException ex) {
                LOG.log(Level.SEVERE, "InterruptedException{0}{1}", new Object[]{ex.getMessage(), ex.getClass()});
            }
            System.exit(0);
        });         
        
        //*****************************************************
        //  Add code to load course data into ComboBox
        //*****************************************************
        
        List<Course> availableCourses = service.getAllCourses();
        coursesComboBox.getItems().addAll(availableCourses);
        
        // ****************************************************
        // Course combobox event handler 
        //  Add code to enroll learner in course & update 
        //  list of registered courses. 
        // ****************************************************
        coursesComboBox.setOnAction(e -> {
            
            creditHoursLabel.setText("");
            confirmPromptLabel.setText("");
            creditHourPromptLabel.setText("");
            
            service.createCourseRegistration(learnerIDField.getText(), coursesComboBox.getValue().getCourseCode());
            List<CourseRegistration> reg = service.getAllCourseRegistrations(learnerIDField.getText());
            
            LOG.log(Level.INFO, "call service getAllCourseRegistration method");
            
            //StringBuilder to iterate over CourseRegistration List
            StringBuilder out = new StringBuilder();
                
            for(CourseRegistration r : reg){
                                   out.append(r.getLearnerID()).append("\t(").append(r.getCourseCode()).append(")\n");
                                  
                                   
                }
                //Displays registered courses to GUI
                registeredCoursesLabel.setText(out.toString());
                
                
                LOG.log(Level.INFO, "Iterate List \tparameter: Learner ID = {0}", learnerIDField.getText());
             
            System.out.println();
        });    
    }
    @Override
    public void stop() {
        em.close();
        emf.close();
        LOG.log(Level.INFO, "Clean up and close entity manager and factory ");
    }

    public static void main(String[] args) {
        
        
        
        //Format the logger in both log file and console
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        
        //FileHandler for logger
        try{
            FileHandler fileHandler = new FileHandler("u07a1_fileLog.log");
            fileHandler.setFormatter(simpleFormatter);
            LOG.addHandler(fileHandler);
            
            // Add same file handler to the Hibernate logger - writes to same log file
            Logger.getLogger("org.hibernate").addHandler(fileHandler);
            
            // Set Hibernate's logger to WARNING level and above
            Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
            
            //Logger for log file --> u07a1.log. Records all levels
            LOG.setLevel(Level.ALL);
            
            //Logger for console --> ONLY records Level.SEVERE
            Logger.getLogger("").getHandlers()[0].setLevel(Level.SEVERE);
            
        //multi-catch block   
        } catch (IOException | SecurityException ex) {
            LOG.log(Level.SEVERE,ex.getMessage());
            System.exit(1);
        }
        LOG.log(Level.INFO, "Starting Program");
        LOG.log(Level.INFO, "Creating logger for program.");
                
        
        launch(args);
    } 
}
