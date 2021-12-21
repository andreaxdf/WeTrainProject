package viewone.graphical_controllers.nutritionists;

import viewone.DaysOfTheWeekController;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import viewone.graphical_controllers.HomeControllerTrainersNutritionists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NewDietController extends HomeControllerTrainersNutritionists {

    private final DaysOfTheWeekController daysController = new DaysOfTheWeekController();
    @FXML
    private Button mondayButton;
    @FXML
    private Button tuesdayButton;
    @FXML
    private Button thursdayButton;
    @FXML
    private Button wednesdayButton;
    @FXML
    private Button fridayButton;
    @FXML
    private Button saturdayButton;
    @FXML
    private Button sundayButton;
    @FXML
    private TextField workoutNameText;
    @FXML
    private TextArea dietForSelectedDayTextArea;
    @FXML
    private TextArea generalInfoTextArea;
    @FXML
    void createButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"NutritionistsHome", "nutritionists");
        System.out.println("Created");
     }
    @FXML
    void dayButtonAction(ActionEvent event) {
        daysController.dayButtonAction(event);
    }
}