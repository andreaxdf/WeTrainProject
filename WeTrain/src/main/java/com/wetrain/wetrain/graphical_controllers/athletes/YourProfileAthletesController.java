package com.wetrain.wetrain.graphical_controllers.athletes;

import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.graphical_controllers.HomeControllerAthletes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class YourProfileAthletesController extends HomeControllerAthletes {
    @FXML
    private Label emailLabel;
    @FXML
    private Label paymentMethodLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label fiscalCodeLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    protected void closeAction(MouseEvent event){
        ((Stage) ((ImageView)event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }
    @FXML
    protected void editButtonAction() throws IOException {

    }
}
