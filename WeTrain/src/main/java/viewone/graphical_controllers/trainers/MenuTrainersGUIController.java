package viewone.graphical_controllers.trainers;

import viewone.ButtonBehavior;
import engineering.MainPane;
import viewone.PageSwitchSimple;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuTrainersGUIController {
    private static final ButtonBehavior buttonBehavior = new ButtonBehavior();
    private static final String HOME = "trainers";

    @FXML private Button createCourseButton;
    @FXML private Button workoutRequestsButton;
    @FXML private Button manageCoursesButton;
    @FXML private Button yourCollaboratorButton;

    @FXML void manageCoursesButtonAction() throws IOException {
        buttonBehavior.setBehavior(manageCoursesButton,"ManageCourses",HOME);
    }

    @FXML void createCourseButtonAction() throws IOException {
        buttonBehavior.setBehavior(createCourseButton,"NewCourse",HOME);
    }

    @FXML void workoutRequestsButtonAction() throws IOException {
        buttonBehavior.setBehavior(workoutRequestsButton,"WorkoutRequests",HOME);
    }

    @FXML protected void yourCollaboratorButtonAction() throws IOException {
        buttonBehavior.setBehavior(yourCollaboratorButton,"YourSubscribers",HOME);
    }

    @FXML void logoAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"TrainersHome",HOME);
        buttonBehavior.resetSelectedButton();
    }

    public static void resetSelectedButton(){
        buttonBehavior.resetSelectedButton();
    }

}
