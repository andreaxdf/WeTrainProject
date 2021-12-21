package com.wetrain.wetrain.graphical_controllers.athletes;

import com.wetrain.wetrain.PageSwitchSizeChange;
import com.wetrain.wetrain.graphical_controllers.HomeControllerAthletes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class YourPersonalStaffController extends HomeControllerAthletes {
    private static final String HOME = "athletes";
    @FXML
    private Label nutritionistName;
    @FXML
    private Label trainerName;
    //TODO Implementare metodi
    @FXML
    void workoutRequestButtonAction() {

    }
    @FXML
    void dietRequestButtonAction() {

    }
    @FXML
    void appointmentRequestButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "RequestForm", HOME, false);
    }
    @FXML
    void consultationRequestButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "RequestForm", HOME, false);
    }
}
