/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u4a1_javafxregisterforcourse;

import javafx.application.Application;
import javafx.event.ActionEvent;
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
 * @author omora
 */
public class U4A1_JavaFXRegisterForCourse extends Application {
    
    
    GridPane grid = new GridPane();
    Label selectPromptLabel = new Label("Please select a course for which you want to register");
    ComboBox<Course> coursesComboBox = new ComboBox<>();   
    Label confirmPromptLabel = new Label("");
    Label registeredCoursesPromptLabel = new Label("You are currently registered for");
    Label creditHourPromptLabel = new Label("Current total Credit Hours");            
    Label registeredCoursesLabel = new Label("");
    Label creditHoursLabel = new Label("0");

    Course choice;
    int totalCredit = 0;
       
           
    @Override
    public void start(Stage primaryStage) {

    /*
        grid.setHgap(0);
        grid.setVgap(500);

        grid.setGridLinesVisible(true);
    */
        RowConstraints row0 = new RowConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        RowConstraints row4 = new RowConstraints();
        
        
        row0.setPercentHeight(5);
        row1.setPercentHeight(50);
        row2.setPercentHeight(10);
        row3.setPercentHeight(5);
        row4.setPercentHeight(15);
         
        grid.getRowConstraints().addAll(row0, row1,row2, row3, row4);

        grid.setAlignment(Pos.CENTER);

        grid.setHgap(5);
        grid.setVgap(5);
 
        
        grid.add(selectPromptLabel, 0, 0);
        grid.setHalignment(selectPromptLabel, HPos.LEFT);
        

        coursesComboBox.getItems().addAll(
                new Course("IT4782"), 
                new Course("IT4784"),
                new Course("IT4786"),
                new Course("IT4789"),
                new Course("IT2230"),
                new Course("IT3345"),
                new Course("IT3349") );
        coursesComboBox.setMaxWidth(Double.MAX_VALUE);

        grid.add(coursesComboBox, 0, 1);
        grid.setValignment(coursesComboBox, VPos.TOP);

        confirmPromptLabel.setTextFill(Color.RED);
        grid.add(confirmPromptLabel, 0, 2);  
        grid.setHalignment(confirmPromptLabel, HPos.LEFT);
        grid.setValignment(confirmPromptLabel, VPos.TOP);


        grid.add(registeredCoursesPromptLabel, 0, 3);  
        grid.setHalignment(registeredCoursesPromptLabel, HPos.LEFT);
        grid.setValignment(registeredCoursesPromptLabel, VPos.TOP);
        
 
        grid.add(creditHourPromptLabel, 1, 3);  
        grid.setHalignment(creditHourPromptLabel, HPos.LEFT);   
        grid.setValignment(creditHourPromptLabel, VPos.TOP);
       
        
        grid.add(registeredCoursesLabel, 0, 4);
        grid.setHalignment(registeredCoursesLabel, HPos.LEFT);   
        grid.setValignment(registeredCoursesLabel, VPos.TOP);
        registeredCoursesLabel.setStyle("-fx-background-color: #fff600;");
  
        grid.add(creditHoursLabel, 1, 4); 
        grid.setHalignment(creditHoursLabel, HPos.LEFT);   
        grid.setValignment(creditHoursLabel, VPos.TOP);
        creditHoursLabel.setStyle("-fx-background-color: #fff600;");
         
        Scene scene = new Scene(grid, 500, 500, Color.RED);
        
        primaryStage.setTitle("JavaFX Register for Course");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Validate user selection (the choice Course object)
        coursesComboBox.setOnAction((ActionEvent e) -> {
            choice = coursesComboBox.getValue();
            
            //Creates cases to be used based upon selection of user
            switch (ValidateChoice(choice, totalCredit)){
                case -1:
                        confirmPromptLabel.setText("**Invalid** - You have already "
                                + "registerd  \n for course " + choice.getCode()+ ".");
                        break;
                case -2:
                    confirmPromptLabel.setText("**Invalid** - You can not register "
                            + " \n for more than 9 credit hours.");
                        break;
                case -0:
                    choice.setCode(coursesComboBox.getValue() + "");
                    choice.setIsRegisteredFor(true);
                    confirmPromptLabel.setText("Registration Confirmed for "
                            + "course " + choice.getCode());
                    creditHoursLabel.setText(Integer.toString(totalCredit +=3));
                    registeredCoursesLabel.setText(registeredCoursesLabel.getText() 
                            + "\n" + choice.getCode());
                    break;
                        
            }
        });
    }
    
    //This method validates the user menu selection
    //against the given registration business rules
    //it returns the following code based on the validation result
    // -1 = invalid, alredy registered for the course
    // -2 = invalid, No more than 9 credit hours allowed
    //  0 = menu selection is valid
    public static int ValidateChoice(Course choice, int totalCredit){
                
            choice.getIsRegisteredFor();
            if (choice.getIsRegisteredFor())
                return -1;
            else if (totalCredit >=9)
                return -2;
            return 0;
            }        
        
    public static void main(String[] args) {
        launch(args);
    }
    
}
