package viewtwo.graphical_controllers.trainers;

import controller.ManageCoursesController;
import engeneering.AlertGenerator;
import engeneering.manage_list.list_cell_factories.CourseListCellFactory;
import exception.DBUnreachableException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import viewone.bean.CourseBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ChooseCourse implements Initializable {

    @FXML private VBox courseActions;
    @FXML private ListView<CourseBean> courseList;

    private CourseBean selectedCourse;

    private final ManageCoursesController manageCoursesController = new ManageCoursesController();

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("TrainersHome", "trainers");
    }

    @FXML void createNewCourseButtonAction() throws IOException {
        PageSwitchSimple.switchPage("CreateCourse", "trainers");
    }

    @FXML void deleteCourseButtonAction() {
        try {
            manageCoursesController.deleteCourse(selectedCourse);
            updateList();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateList() throws DBUnreachableException, SQLException {
        ObservableList<CourseBean> courseObservableList = FXCollections.observableList(manageCoursesController.getCourseList());
        courseList.setItems(FXCollections.observableList(courseObservableList));
    }

    @FXML void modifyCourseButtonAction() {

    }

    @FXML void sendCommunicationButtonAction() {

    }

    @FXML void startLessonButtonAction() throws IOException {
        StartLessonGUIController startLessonGUIController = (StartLessonGUIController) PageSwitchSimple.switchPage("StartLesson", "trainers");
        if(startLessonGUIController != null) {
            startLessonGUIController.setCourseBean(selectedCourse);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            courseList.setCellFactory(nodeListView -> new CourseListCellFactory());
            updateList();
            courseList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends CourseBean> observableValue, CourseBean oldItem, CourseBean newItem) {
                    selectedCourse = newItem;
                    courseActions.setDisable(false);
                }
            });
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
