package viewone.graphical_controllers.athletes;

import controllers.SubscribeToCourseController;
import exceptions.DBUnreachableException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import engineering.MainPane;
import viewone.PageSwitchSizeChange;
import beans.CourseBean;
import viewone.beans_viewone.CourseSearchBeanA;
import engineering.AlertGenerator;
import engineering.manage_list.ManageCourseList;
import viewone.graphical_controllers.FitnessLevelFilterGUIController;
import engineering.manage_list.list_cell_factories.CourseListCellFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class FindCourseGUIController extends HomeGUIControllerAthletes implements Initializable {

    @FXML private Button baseFitnessLevelButton;
    @FXML private Button intermediateFitnessLevelButton;
    @FXML private Button advancedFitnessLevelButton;
    @FXML private TextField courseNameText;
    @FXML private Button fridayButton;
    @FXML private Button mondayButton;
    @FXML private ListView<CourseBean> resultList;
    @FXML private Button saturdayButton;
    @FXML private Button searchButton;
    @FXML private Button sundayButton;
    @FXML private Button thursdayButton;
    @FXML private Button tuesdayButton;
    @FXML private Button wednesdayButton;

    private final Boolean[] selectedDays = new Boolean[7];
    private final FitnessLevelFilterGUIController fitnessLevelFilter = new FitnessLevelFilterGUIController();
    private final SubscribeToCourseController subscribeToCourseController = new SubscribeToCourseController();

    public FindCourseGUIController() throws DBUnreachableException, SQLException {
    }

    @FXML void dayButtonAction(ActionEvent event) {
        String sourceId = ((Node) event.getSource()).getId();
        switch(sourceId){
            case "mondayButton" -> selectedDayButtonAction(mondayButton,0);
            case "tuesdayButton" -> selectedDayButtonAction(tuesdayButton,1);
            case "wednesdayButton" -> selectedDayButtonAction(wednesdayButton,2);
            case "thursdayButton" -> selectedDayButtonAction(thursdayButton,3);
            case "fridayButton" -> selectedDayButtonAction(fridayButton,4);
            case "saturdayButton" -> selectedDayButtonAction(saturdayButton,5);
            case "sundayButton" -> selectedDayButtonAction(sundayButton,6);
        }
    }

    private void selectedDayButtonAction(Button button, int i){
        if(!selectedDays[i]) {
            button.setStyle("-fx-background-color: white;" +
                    " -fx-text-fill: rgb(24,147,21);" +
                    " -fx-border-color: rgb(24,147,21);" +
                    " -fx-border-radius: 50");
        }else{
            button.setStyle(null);
        }
        selectedDays[i]=!selectedDays[i];
    }

    @FXML void fitnessLevelSelection(ActionEvent event){
        fitnessLevelFilter.fitnessLevelSelection(event);
    }

    @FXML protected void searchCourseByFilters(){
        String fitnessLevel = fitnessLevelFilter.getSelectedFitnessLevelString();
        String courseName = courseNameText.getText();
        try {
            List<CourseBean> courseBeanList = subscribeToCourseController.searchCourse(new CourseSearchBeanA(
                    courseName,
                    fitnessLevel,
                    selectedDays
            ));
            ManageCourseList.updateList(resultList, courseBeanList);
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
            try {
                PageSwitchSizeChange.pageSwitch((Stage) MainPane.getInstance().getScene().getWindow(), "Login", "Launcher", true);
            } catch (IOException ex) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        baseFitnessLevelButton.fire();
        Arrays.fill(selectedDays, Boolean.FALSE);
        resultList.setCellFactory(nodeListView -> new CourseListCellFactory(false));
        ManageCourseList.setListener(resultList, subscribeToCourseController);
        setUserInfoTab();
    }
}