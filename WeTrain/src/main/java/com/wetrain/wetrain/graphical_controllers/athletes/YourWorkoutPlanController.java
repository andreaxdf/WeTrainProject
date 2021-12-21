package com.wetrain.wetrain.graphical_controllers.athletes;

import com.wetrain.wetrain.DaysOfTheWeekController;
import com.wetrain.wetrain.PageSwitchSizeChange;
import com.wetrain.wetrain.graphical_controllers.HomeControllerAthletes;
import com.wetrain.wetrain.graphical_controllers.ListPopulate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class YourWorkoutPlanController extends HomeControllerAthletes implements Initializable {

    private final DaysOfTheWeekController daysController = new DaysOfTheWeekController();
    @FXML
    public Button mondayButton;
    @FXML
    public Button tuesdayButton;
    @FXML
    public Button wednesdayButton;
    @FXML
    public Button thursdayButton;
    @FXML
    public Button fridayButton;
    @FXML
    public Button saturdayButton;
    @FXML
    public Button sundayButton;
    @FXML
    private ListView<Node> exercisesList;
    @FXML
    void dayButtonAction(ActionEvent event) {
        daysController.dayButtonAction(event);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(15,exercisesList,false);
    }
}
