package com.wetrain.wetrain.Controllers.Trainers;

import com.wetrain.wetrain.Controllers.ListPopulate;
import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class YourCollaboratorTrainersController{
    @FXML
    private ImageView logo;
    @FXML
    private Button logoutButton;
    @FXML
    private Button editButton;
    @FXML
    private Button certificationsButton;
    @FXML
    private Button sendEmailButton;
    @FXML
    private Button changeCollaboratorButton;
    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "WeTrainGUI", "Launcher", true);
    }
    @FXML
    protected void closeAction(){
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    void editButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "YourProfileTrainersNutritionists", "", false);
    }
    //TODO implementare metodi
    @FXML
    void certificationsButtonAction() {

    }
    @FXML
    void sendEmailButtonAction() {

    }
    @FXML
    void changeCollaboratorButtonAction() {

    }
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"TrainersHome", "Trainers");
    }
}