/*
 * This program is designed to provide information about the author.
 * It uses a Group pane to accomplish the task and displays two labels and two 
 * .jpg pictures.
 *
 * @author Brian Bok
 *
 */
package u3a1_u3a1_aboutmebyjavafx;

import javafx.application.Application; 
import javafx.geometry.HPos;
import javafx.geometry.Insets; 
import javafx.geometry.Pos;
import javafx.scene.Scene; 
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane; 
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class U3A1_U3A1_AboutMeByJavaFX extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        
        //Create White box in Upper left corner for name
        //Rectangle rcUpLeft = new Rectangle();
       
        Label textUpLeft = new Label("Brian Bok");
        textUpLeft.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        
        
        //Create image box in Upper right corner
        ImageView person = new ImageView ("images/Brian.jpg");
        
        //Create box in lower right corner
        Label textLowRight = new Label("Pizza");
        textLowRight.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        
        
        //Create image box in lower left corner
        ImageView sport =  new ImageView ("images/baseball.jpg");
        
        
        //Creating a Grid Pane 
        GridPane gridPane = new GridPane(); 
        
        
        //Setting size for the pane  
        gridPane.setMinSize(500, 500);
        gridPane.setMaxSize(500, 550);
       
        //Setting the padding  
        gridPane.setPadding(new Insets(5, 5, 5, 5)); 
        
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
      
        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.CENTER);
       
        //Arranging all the nodes in the grid 
        gridPane.add(textUpLeft, 0, 0);
        gridPane.add(person, 1, 0);
        gridPane.add(sport, 0,2);
        gridPane.add(textLowRight, 1, 2); 
        
        //Center Upper Right and Lower Left labels in each pane. 
        gridPane.setHalignment(textLowRight,HPos.CENTER);
        gridPane.setHalignment(textUpLeft,HPos.CENTER);
        
        //Creating a scene object 
         Scene scene = new Scene(gridPane);  
      
        //Setting title to the Stage 
        primaryStage.setTitle("About Me by JavaFX"); 
         
        //Adding scene to the stage 
        primaryStage.setScene(scene); 
         
        //Displaying the contents of the stage 
        primaryStage.show(); 
    } 
    public static void main(String args[]){ 
      launch(args); 
   } 
} 