package viewtwo.graphical_controllers.athletes;

import controller.SubscribeToCourseController;
import engeneering.AlertGenerator;
import engeneering.manage_list.list_cell_factories.CourseListCellFactory;
import exception.DBUnreachableException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import viewone.bean.CourseBean;
import viewtwo.PageSwitchSimple;
import viewtwo.graphical_controllers.CourseInfoGUIController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class YourCoursesGUIController implements Initializable {

    public static final String ATHLETES = "athletes";
    @FXML private VBox courseActions;
    @FXML private ListView<CourseBean> courseList;

    private CourseBean selectedCourse;
    private static final SubscribeToCourseController subscribeToCourseController = new SubscribeToCourseController();

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("Courses", ATHLETES);
    }

    @FXML void courseInfoButtonAction() throws IOException {
        CourseInfoGUIController controller = (CourseInfoGUIController) PageSwitchSimple.switchPage("CourseInfo", ATHLETES);
        if(controller!=null) {
            controller.setBackPathAndValues("YourCourses", ATHLETES, selectedCourse);
        }
    }

    @FXML void joinLessonButtonAction(ActionEvent event) {

    }

    @FXML void unsubscribeButtonAction() {
        try {
            subscribeToCourseController.unsubscribeFromCourse(selectedCourse);
            PageSwitchSimple.switchPage("AthletesHome", "athletes");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        courseList.setCellFactory(nodeListView -> new CourseListCellFactory(true));
        courseList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CourseBean>() {
              @Override
              public void changed(ObservableValue<? extends CourseBean> observableValue, CourseBean oldCourse, CourseBean newCourse) {
                  if(newCourse!=null) {
                      selectedCourse = newCourse;
                      courseActions.setDisable(false);
                  }else{
                      courseActions.setDisable(true);
                  }
              }
          }
        );
    }
}