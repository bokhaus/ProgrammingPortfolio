/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translatetransitionfx;

import javafx.animation.TranslateTransition;
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
public class TranslateTransitionFX extends Application {
    
     @Override
    public void start(Stage primaryStage) throws Exception {
        Button btn = new Button("Start Thermostat\n" + "  Programming");
        Group group = new Group(btn);
        Scene scene = new Scene(group, 600, 600);
 
        //Duration = 2.5 seconds
        Duration duration = Duration.millis(2500);
        //Create new translate transition
        TranslateTransition transition = new TranslateTransition(duration, btn);
        //Move in X axis by +200
        transition.setByX(275);
        //Move in Y axis by +100
        transition.setByY(275);
        //Go back to previous position after 2.5 seconds
        transition.setAutoReverse(false); //can be set to true
        //Repeat animation twice
        transition.setCycleCount(1);// set to more than one cycle
        transition.play();
 
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}