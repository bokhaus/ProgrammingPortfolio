/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotatetransitionfx;

import javafx.animation.RotateTransition;
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
public class RotateTransitionFX extends Application {
    
  @Override
  public void start(Stage primaryStage) {
    Button btn = new Button("Genuine Coder");
    Group group = new Group(btn);
    Scene scene = new Scene(group, 600, 600);
 
    //Duration = 2.5 seconds
    Duration duration = Duration.millis(2500);
    //Create new rotate transition
    RotateTransition rotateTransition = new RotateTransition(duration, btn);
    //Rotate by 200 degree
    rotateTransition.setByAngle(360);
    rotateTransition.play();
 
    primaryStage.setScene(scene);
    primaryStage.show();
  }
 
  public static void main(String[] args) {
    Application.launch(args);
  }
}
