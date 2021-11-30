package com.wetrain.wetrain.Controllers.Trainers;

import com.wetrain.wetrain.Controllers.TimeSchedulerController;
import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class NewCourseController implements Initializable {
    private Boolean[] toggle = new Boolean[7];
    @FXML
    private Button createCourseButt;
    @FXML
    private Button createWorkoutButt;
    @FXML
    private Button editButt;
    @FXML
    private ListView<?> exercisesSelectedList;
    @FXML
    private Button logoutButton;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button manageLessonsButt;
    @FXML
    private Button manageRequestsButt;
    @FXML
    private Button mondayButton;
    @FXML
    private TimeSchedulerController mondayTimeSchedulerController;
    @FXML
    private Parent mondayTimeScheduler;
    @FXML
    private Button tuesdayButton;
    @FXML
    private TimeSchedulerController tuesdayTimeSchedulerController;
    @FXML
    private Parent tuesdayTimeScheduler;
    @FXML
    private Button wednesdayButton;
    @FXML
    private TimeSchedulerController wednesdayTimeSchedulerController;
    @FXML
    private Parent wednesdayTimeScheduler;
    @FXML
    private Button thursdayButton;
    @FXML
    private TimeSchedulerController thursdayTimeSchedulerController;
    @FXML
    private Parent thursdayTimeScheduler;
    @FXML
    private Button fridayButton;
    @FXML
    private TimeSchedulerController fridayTimeSchedulerController;
    @FXML
    private Parent fridayTimeScheduler;
    @FXML
    private Button saturdayButton;
    @FXML
    private TimeSchedulerController saturdayTimeSchedulerController;
    @FXML
    private Parent saturdayTimeScheduler;
    @FXML
    private Button sundayButton;
    @FXML
    private TimeSchedulerController sundayTimeSchedulerController;
    @FXML
    private Parent sundayTimeScheduler;
    @FXML
    private TextField workoutNameText;
    @FXML
    void createCourseButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("TrainersHome", "Trainers");
        mainPane.setCenter(view);
    }
    @FXML
    void createWorkoutButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("NewWorkoutPlan", "Trainers");
        mainPane.setCenter(view);
    }
    @FXML
    void editButtonAction() {System.out.println("Edit");}
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("TrainersHome", "Trainers");
        mainPane.setCenter(view);
    }
    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "Launcher/WeTrainGUI", true);
    }
    @FXML
    void manageLessonsButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("ManageLessonsTrainers", "Trainers");
        mainPane.setCenter(view);
    }
    @FXML
    void manageRequestsButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("ManageRequestsTrainers", "Trainers");
        mainPane.setCenter(view);
    }
    @FXML
    void dayButtonAction(ActionEvent event) {
        String sourceId = ((Node) event.getSource()).getId();
        switch(sourceId){
            case "mondayButton" -> toggleDayButtonAction(mondayTimeSchedulerController,mondayButton,0);
            case "tuesdayButton" -> toggleDayButtonAction(tuesdayTimeSchedulerController,tuesdayButton,1);
            case "wednesdayButton" -> toggleDayButtonAction(wednesdayTimeSchedulerController,wednesdayButton,2);
            case "thursdayButton" -> toggleDayButtonAction(thursdayTimeSchedulerController,thursdayButton,3);
            case "fridayButton" -> toggleDayButtonAction(fridayTimeSchedulerController,fridayButton,4);
            case "saturdayButton" -> toggleDayButtonAction(saturdayTimeSchedulerController,saturdayButton,5);
            case "sundayButton" -> toggleDayButtonAction(sundayTimeSchedulerController,sundayButton,6);
        }
    }
    private void toggleDayButtonAction(TimeSchedulerController controller,Button button, int i){
        controller.toggleVisibility(toggle[i]);
        if(!toggle[i]) {
            button.setStyle("-fx-background-color: white; -fx-text-fill: rgb(24,147,21); -fx-border-color: rgb(24,147,21); -fx-border-radius: 50");
        }else{
            button.setStyle(null);
        }
        toggle[i]=!toggle[i];
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Arrays.fill(toggle, Boolean.FALSE);
    }
}
