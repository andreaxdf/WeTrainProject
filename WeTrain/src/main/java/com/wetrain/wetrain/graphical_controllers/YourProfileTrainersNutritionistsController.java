package com.wetrain.wetrain.graphical_controllers;

import com.wetrain.wetrain.MainPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class YourProfileTrainersNutritionistsController {
    @FXML
    private Label emailLabel;
    @FXML
    private Label ibanLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label fiscalCodeLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    protected void closeAction(MouseEvent e){
        ((Stage) ((ImageView)e.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }
    @FXML
    protected void editButtonAction() throws IOException {

    }
}