package com.wetrain.wetrain.Controllers.Nutritionist;

import com.wetrain.wetrain.Controllers.ListPopulate;
import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageDietsNutritionistsController implements Initializable{
    @FXML
    private Button createDietButton;
    @FXML
    private Button editButton;
    @FXML
    private Button logoutButton;
    @FXML
    private ListView<?> dietsList;
    @FXML
    private Button manageAppointmentsButton;
    @FXML
    private Button manageDietsButton;
    @FXML
    private Button yourCollaboratorButton;
    @FXML
    void createDietButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"NewDiet", "Nutritionists");
    }
    @FXML
    void editButtonAction() {

    }
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"NutritionistsHome", "Nutritionists");
    }
    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "WeTrainGUI", "Launcher", true);
    }
    @FXML
    protected void closeAction(){
        ((Stage) logoutButton.getScene().getWindow()).close();
    }
    @FXML
    void manageAppointmentsButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ManageAppointmentsNutritionists", "Nutritionists");
    }
    @FXML
    void manageDietsButtonAction()throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"NutritionistsHome", "Nutritionists");
    }
    @FXML
    protected void yourCollaboratorButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"YourCollaboratorTrainers", "Trainers");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(10,dietsList,true);
    }
}
