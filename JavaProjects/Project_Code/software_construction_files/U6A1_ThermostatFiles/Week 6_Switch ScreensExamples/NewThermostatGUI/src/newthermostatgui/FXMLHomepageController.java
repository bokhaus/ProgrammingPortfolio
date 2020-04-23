/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newthermostatgui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLHomepageController implements Initializable {

    @FXML
    private AnchorPane homePane;
    @FXML
    private Button button;
    @FXML
    private Label label;
    @FXML
    private Label label1;
    @FXML
    private Label label3;
    @FXML
    private TextField currTemp;
    @FXML
    private TextField currHumid;
    @FXML
    private TextField currSett;
    @FXML
    private TextField currTime;
    @FXML
    private Button loadProgPane;
    @FXML
    private Button upBtn;
    @FXML
    private Button dwnBtn;

    /**
     * Initializes the controller class.
     */
    

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    @FXML
    private void increaseTemp(ActionEvent event) {
    }

    @FXML
    private void decreaseTemp(ActionEvent event) {
    }

    @FXML
    private void resetSetting(ActionEvent event) {
    }

    @FXML
    private void coolSetting(ActionEvent event) {
    }

    @FXML
    private void handleButtonAction(DragEvent event) {
    }

    @FXML
    private void exitSystem(ActionEvent event) {
        Platform.exit();
        System.exit(0); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    @FXML
    private void handleButtonAction2(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLProgramGUI.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
}
