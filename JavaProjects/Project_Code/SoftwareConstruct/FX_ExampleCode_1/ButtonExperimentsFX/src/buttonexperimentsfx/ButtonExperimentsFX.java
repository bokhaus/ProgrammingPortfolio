/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buttonexperimentsfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;

/**
 *
 * @author User
 */
public class ButtonExperimentsFX extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("HBox Experiment 1");

        //FileInputStream input = new FileInputStream("images/houses_silhouette.jpg");
        //Image image = new Image(input);
        //ImageView imageView = new ImageView(image);
        //Create image 
        ImageView house = new ImageView ("images/houses_silhouette.jpg");
        
        Button button = new Button("", house);

        Scene scene = new Scene(button, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}