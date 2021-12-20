package com.wetrain.wetrain.controllers.trainers;

import com.wetrain.wetrain.ButtonBehavior;
import com.wetrain.wetrain.controllers.TimeSchedulerController;
import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class NewCourseController implements Initializable {
    public Boolean[] toggled = new Boolean[7];
    @FXML
    private ListView<?> exercisesSelectedList;
    @FXML
    private Button logoutButton;
    @FXML
    private Button mondayButton;
    @FXML
    private Button createButton;
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
    private TextArea infoTextArea;
    @FXML
    private TextArea equipmentTextArea;
    @FXML
    void createButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"TrainersHome", "trainers");
        MenuTrainersController.resetSelectedButton();
        System.out.println("Created");
    }
    @FXML
    void fitnessLevelSelection(){

    }
    @FXML
    void editButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "YourProfileTrainersNutritionists", "", false);
    }
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"TrainersHome", "trainers");
    }
    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "WeTrainGUI", "launcher", true);
    }
    @FXML
    protected void closeAction(){
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    void dayButtonAction(ActionEvent event) {
        String sourceId = ((Node) event.getSource()).getId();
        switch(sourceId){
            case "mondayButton" -> toggledDayButtonAction(mondayTimeSchedulerController,mondayButton,0);
            case "tuesdayButton" -> toggledDayButtonAction(tuesdayTimeSchedulerController,tuesdayButton,1);
            case "wednesdayButton" -> toggledDayButtonAction(wednesdayTimeSchedulerController,wednesdayButton,2);
            case "thursdayButton" -> toggledDayButtonAction(thursdayTimeSchedulerController,thursdayButton,3);
            case "fridayButton" -> toggledDayButtonAction(fridayTimeSchedulerController,fridayButton,4);
            case "saturdayButton" -> toggledDayButtonAction(saturdayTimeSchedulerController,saturdayButton,5);
            case "sundayButton" -> toggledDayButtonAction(sundayTimeSchedulerController,sundayButton,6);
        }
    }
    private void toggledDayButtonAction(TimeSchedulerController controller,Button button, int i){
        controller.toggleVisibility(toggled[i]);
        if(!toggled[i]) {
            button.setStyle("-fx-background-color: white;" +
                    " -fx-text-fill: rgb(24,147,21);" +
                    " -fx-border-color: rgb(24,147,21);" +
                    " -fx-border-radius: 50");
        }else{
            button.setStyle(null);
        }
        toggled[i]=!toggled[i];
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Arrays.fill(toggled, Boolean.FALSE);
    }
}
