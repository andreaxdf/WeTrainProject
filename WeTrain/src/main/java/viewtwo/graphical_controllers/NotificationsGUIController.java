package viewtwo.graphical_controllers;

import controllers.NotificationsController;
import engineering.AlertGenerator;
import engineering.manage_list.list_cell_factories.NotificationListCellFactory;
import exceptions.DBUnreachableException;
import exceptions.UserNotFoundException;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import beans.NotificationBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class NotificationsGUIController implements Initializable {

    @FXML private ListView<NotificationBean> notificationList;

    private final NotificationsController notificationsController = new NotificationsController();
    private String filename;
    private String path;

    public void setBackPath(String filename, String pathString) {
        this.filename = filename;
        path = pathString;
    }

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage(filename, path);
    }

    @FXML void updateNotificationList() {
        try {
            notificationList.setCellFactory(nodeListView -> new NotificationListCellFactory(true));
            List<NotificationBean> notificationBeanList = notificationsController.getMyNotification();
            notificationList.setItems(FXCollections.observableList(notificationBeanList));
            notificationList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends NotificationBean> observableValue, NotificationBean oldItem, NotificationBean newItem) {
                    if (newItem != null) {
                        try {
                            AlertGenerator.newInformationAlert(
                                    "Notification",
                                    newItem.getText());
                            notificationsController.deleteNotification(newItem);
                            Platform.runLater(() -> notificationList.getSelectionModel().clearSelection());
                        } catch (DBUnreachableException e) {
                            AlertGenerator.newWarningAlert(
                                    e.getErrorStrings().get(0),
                                    e.getErrorStrings().get(1),
                                    e.getErrorStrings().get(2));
                            PageSwitchSimple.logOff();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (UserNotFoundException | SQLException e) {
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

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        updateNotificationList();
    }
}
