package viewone.graphical_controllers.athletes;

import beans.*;
import controllers.RequestWorkoutPlanController;
import controllers.SubscribeToCourseController;
import exceptions.DBUnreachableException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;
import viewone.PageSwitchSizeChange;
import engineering.AlertGenerator;

import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.*;

public class YourWeeklyScheduleGUIController extends HomeGUIControllerAthletes implements Initializable {

    @FXML private Label infoLabel;
    @FXML private Button mondayButton;
    @FXML private Text mondayText;
    @FXML private Button tuesdayButton;
    @FXML private Text tuesdayText;
    @FXML private Button wednesdayButton;
    @FXML private Text wednesdayText;
    @FXML private Button thursdayButton;
    @FXML private Text thursdayText;
    @FXML private Button fridayButton;
    @FXML private Text fridayText;
    @FXML private Button saturdayButton;
    @FXML private Text saturdayText;
    @FXML private Button sundayButton;
    @FXML private Text sundayText;

    private Button previousButton;
    private Text previousText;
    private List<Button> buttonList;
    private List<CourseBean> courseBeanList;
    private WorkoutPlanBean workoutPlanBean;

    private final SubscribeToCourseController subscribeToCourseController;
    private final RequestWorkoutPlanController requestWorkoutPlanController;

    public YourWeeklyScheduleGUIController() throws DBUnreachableException, SQLException {
        subscribeToCourseController = new SubscribeToCourseController();
        requestWorkoutPlanController = new RequestWorkoutPlanController();
    }

    private void colorShift(Button button, Text text){
        if(previousButton!=null){
            previousButton.setStyle(null);
            previousText.setStyle("-fx-fill: white");
        }
        button.setStyle("-fx-background-color: white;" +
                "-fx-border-color:  rgb(24, 147, 21);" +
                "-fx-border-radius: 50");
        text.setStyle("-fx-fill: rgb(24, 147, 21)");
        previousButton = button;
        previousText = text;
    }

    public DayOfWeek getDay(ActionEvent event) {
        for(int i = 0; i < 7; i++) {
            if(Objects.equals(buttonList.get(i), event.getSource())) {
                return DayOfWeek.of(i+1);
            }
        }
        return null;
    }

    @FXML void dayButtonAction(ActionEvent event) throws SQLException {
        colorShift((Button) event.getSource(), ((Text)((Button) event.getSource()).getChildrenUnmodifiable().get(0)));

        if(courseBeanList == null && workoutPlanBean == null) {
            try {
                courseBeanList = subscribeToCourseController.getLoggedAthleteCourseList();
                workoutPlanBean = requestWorkoutPlanController.getWorkoutPlan();
            } catch (DBUnreachableException e) {
                List<String> errorStrings = e.getErrorStrings();
                AlertGenerator.newWarningAlert(
                        errorStrings.get(0),
                        errorStrings.get(1),
                        errorStrings.get(2));
                PageSwitchSizeChange.logOff();
                return;
            }
        }
        StringBuilder infoText = getStringBuilder(event);
        infoLabel.setText(infoText.toString());
    }

    @NotNull
    private StringBuilder getStringBuilder(ActionEvent event) {
        StringBuilder infoText = new StringBuilder();
        String day = Objects.requireNonNull(getDay(event)).name();
        boolean busyDay = false;
        if(!courseBeanList.isEmpty()){
            infoText.append("You have this lessons:\n");
            busyDay = isBusyDay(infoText, day);
        }
        if(!busyDay){
            infoText = new StringBuilder().append("You are free for this day!\n" +
                    "check out our popular courses from the homepage and let's train!");
        }
        infoText.append("\n\n\n");
        appendWorkoutPlanString(infoText, day);
        return infoText;
    }

    private boolean isBusyDay(StringBuilder infoText, String day) {
        boolean busyDay = false;
        for(CourseBean course: courseBeanList){
            for(LessonBean lesson: course.getLessonBeanList()){
                if(Objects.equals(lesson.getLessonDay(), day)) {
                    busyDay = true;
                    infoText.append("-" + course.getName() + " " + lesson.getLessonStartTime() + "/" + lesson.getLessonEndTime() + "\n");
                }
            }
        }
        return busyDay;
    }

    private void appendWorkoutPlanString(StringBuilder infoText, String day) {
        if(workoutPlanBean != null) {
            for(WorkoutDayBean workoutDayBean: workoutPlanBean.getWorkoutDayList()){
                if(Objects.equals(workoutDayBean.getDay(), day)){
                    infoText.append("You have this exercise in your workout plan:\n");
                    for(ExerciseBean exercise: workoutDayBean.getExerciseBeanList()){
                        infoText.append("-" + exercise.getName() + "\n");
                    }
                }
            }
        }
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        setUserInfoTab();
        buttonList = new ArrayList<>(Arrays.asList(
                mondayButton,
                tuesdayButton,
                wednesdayButton,
                thursdayButton,
                fridayButton,
                saturdayButton,
                sundayButton));
    }

}
