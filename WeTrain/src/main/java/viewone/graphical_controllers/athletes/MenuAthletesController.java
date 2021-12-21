package viewone.graphical_controllers.athletes;

import viewone.ButtonBehavior;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuAthletesController {
    private static final ButtonBehavior buttonBehavior = new ButtonBehavior();
    private static final String HOME = "athletes";
    @FXML
    void staffButtonAction(ActionEvent event) throws IOException {
        buttonBehavior.setBehavior(((Button) event.getSource()),"YourPersonalStaff",HOME);
    }
    @FXML
    void findCourseButtonAction(ActionEvent event) throws IOException {
        buttonBehavior.setBehavior(((Button) event.getSource()),"FindCourse",HOME);
    }
    @FXML
    void dietButtonAction(ActionEvent event) throws IOException {
        buttonBehavior.setBehavior(((Button) event.getSource()),"YourDietPlan",HOME);
    }
    @FXML
    void workoutButtonAction(ActionEvent event) throws IOException {
        buttonBehavior.setBehavior(((Button) event.getSource()),"YourWorkoutPlan",HOME);
    }
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"AthletesHome", HOME);
        buttonBehavior.resetSelectedButton();
    }
}
