/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spinnerdemo2;


/**
 *
 * @author User
 */
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
 
public class SpinnerDemo2 extends Application {
 
   @Override
   public void start(Stage stage) {
 
       Label label = new Label("Select Day of Week:");
 
       ObservableList<String> daysOfWeek = FXCollections.observableArrayList(//
               "Monday", "Tuesday", "Wednesday", "Thursday", //
               "Friday", "Saturday", "Sunday");
 
       final Spinner<String> spinner = new Spinner<String>();
 
       // Value factory.
       SpinnerValueFactory<String> valueFactory = //
               new SpinnerValueFactory.ListSpinnerValueFactory<String>(daysOfWeek);
      
       // Default value
       valueFactory.setValue("Sunday");
 
       spinner.setValueFactory(valueFactory);
 
       FlowPane root = new FlowPane();
       /*root.setHgap(10);
       root.setVgap(10);
       root.setPadding(new Insets(10));*/
       root.getChildren().addAll(label, spinner);
       
 
       Scene scene = new Scene(root, 400, 200);
 
       stage.setTitle("JavaFX Spinner Example");
       stage.setScene(scene);
       stage.show();
   }
 
   public static void main(String[] args) {
       Application.launch(args);
   }
 
}