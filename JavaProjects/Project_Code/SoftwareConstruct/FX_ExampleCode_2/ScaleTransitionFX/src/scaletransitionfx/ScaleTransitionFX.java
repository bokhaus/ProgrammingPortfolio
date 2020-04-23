/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaletransitionfx;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
 

/**
 *
 * @author User
 */
public class ScaleTransitionFX extends Application {
    
  @Override
  public void start(Stage primaryStage) throws Exception {
    Button btn = new Button("Genuine Coder");
    Group group = new Group(btn);
    Scene scene = new Scene(group, 600, 600);
 
    //Duration = 2.5 seconds
    Duration duration = Duration.millis(2500);
    //Create new scale transition
    ScaleTransition scaleTransition = new ScaleTransition(duration, btn);
    //Set how much X should enlarge
    scaleTransition.setByX(1.5);
    //Set how much Y should
    scaleTransition.setByY(1.5);
    scaleTransition.play();
 
    primaryStage.setScene(scene);
    primaryStage.show();
  }
 
  public static void main(String[] args) {
    Application.launch(args);
  }
}
