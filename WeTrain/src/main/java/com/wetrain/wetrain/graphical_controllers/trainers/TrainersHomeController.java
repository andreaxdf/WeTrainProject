package com.wetrain.wetrain.graphical_controllers.trainers;

import com.wetrain.wetrain.PageSwitchSizeChange;
import com.wetrain.wetrain.graphical_controllers.HomeControllerTrainersNutritionists;
import com.wetrain.wetrain.graphical_controllers.ListPopulate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TrainersHomeController extends HomeControllerTrainersNutritionists implements Initializable {
    @FXML
    private ListView<Node> coursesList;
    @FXML
    private ListView<Node> requestsList;
    @FXML
    private Button mondayButton;
    @FXML
    private Button tuesdayButton;
    @FXML
    private Button wednesdayButton;
    @FXML
    private Button thursdayButton;
    @FXML
    private Button fridayButton;
    @FXML
    private Button saturdayButton;
    @FXML
    private Button sundayButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListPopulate.populateList(10,coursesList,true);
        ListPopulate.populateList(10,requestsList,false);
    }

}