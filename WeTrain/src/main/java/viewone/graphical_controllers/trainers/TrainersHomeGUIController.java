package viewone.graphical_controllers.trainers;

import controllers.ManageCoursesController;
import controllers.SatisfyWorkoutRequestsController;
import exceptions.DBUnreachableException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import engineering.MainPane;
import viewone.PageSwitchSimple;
import viewone.PageSwitchSizeChange;
import beans.CourseBean;
import beans.RequestBean;
import engineering.AlertGenerator;
import engineering.manage_list.ManageCourseList;
import engineering.manage_list.ManageNotificationList;
import engineering.manage_list.ManageRequestList;
import engineering.manage_list.list_cell_factories.CourseListCellFactory;
import engineering.manage_list.list_cell_factories.NotificationListCellFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class TrainersHomeGUIController extends HomeGUIControllerTrainers implements Initializable {
    @FXML private ListView<CourseBean> courseList;
    @FXML private ListView<RequestBean> requestList;

    private final SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;
    private final ManageCoursesController manageCoursesController;

    public TrainersHomeGUIController() throws DBUnreachableException, SQLException {
        manageCoursesController = new ManageCoursesController();
        satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ManageRequestList.setRequestList(requestList, satisfyWorkoutRequestsController);
            requestList.getSelectionModel().selectedItemProperty().
                    addListener(new ChangeListener<>() {
                        @Override
                        public void changed(ObservableValue<? extends RequestBean> observableValue, RequestBean oldItem, RequestBean newItem) {
                            try {
                                ((WorkoutRequestsGUIController) Objects.requireNonNull(
                                        PageSwitchSimple.switchPage(MainPane.getInstance(), "WorkoutRequests", "trainers")))
                                        .setSelectedRequest(newItem);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            courseList.setCellFactory(nodeListView -> new CourseListCellFactory(false));
            notificationList.setCellFactory(nodeListView -> new NotificationListCellFactory(false));
            updateNotificationList();
            ManageNotificationList.setCourseListener(notificationList);
            ManageCourseList.setListener(courseList, null);
            ManageCourseList.updateList(courseList, manageCoursesController.getCourseList());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        }
        setUserInfoTab();
    }

}
