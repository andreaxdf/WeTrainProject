package viewone.graphical_controllers.trainers;

import controller.SubscribersManagementController;
import database.dao_classes.TrainerDAO;
import exception.DBUnreachableException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import viewone.PageSwitchSizeChange;
import viewone.bean.UserBean;
import viewone.engeneering.AlertGenerator;
import viewone.engeneering.UserInfoCarrier;
import viewone.graphical_controllers.EmailFormGUIController;
import viewone.list_cell_factories.PersonListCellFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class YourSubscribersGUIController extends HomeGUIControllerTrainers implements Initializable {
    @FXML private Label subscribersCountLabel;
    @FXML private ListView<UserBean> subscribersList;
    @FXML private Label infoBirth;
    @FXML private Label infoEmail;
    @FXML private Label infoFiscalCode;
    @FXML private Label infoName;
    @FXML private VBox infoSubscriberBox;
    @FXML private Label infoSurname;
    @FXML private Label infoUsername;
    private boolean clicked = false;

    private UserBean selectedSubscriber;

    private final SubscribersManagementController subscribersManagementController = new SubscribersManagementController();

    @FXML void writeEmailButtonAction(ActionEvent event) throws IOException {
        EmailFormGUIController emailFormGUIController = (EmailFormGUIController) PageSwitchSizeChange.pageSwitch((Button) event.getSource(),"EmailForm","",false);
        emailFormGUIController.setReceiver(selectedSubscriber);
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            UserInfoCarrier trainerInfo = getUserInfo();
            subscribersCountLabel.setText(String.valueOf( new TrainerDAO().getNumberOfSubscribers(trainerInfo.getFiscalCode())));
            subscribersList.setCellFactory(nodeListView -> new PersonListCellFactory());
            subscribersList.getSelectionModel().selectedItemProperty().
                    addListener(new ChangeListener<>() {
                        @Override
                        public void changed(ObservableValue<? extends UserBean> observableValue, UserBean oldItem, UserBean newItem) {
                            setInfoBox(newItem);
                        }
                    });
            ObservableList<UserBean> requestBeanObservableList = FXCollections.observableList(subscribersManagementController.getSubscriberList(trainerInfo.getFiscalCode()));
            subscribersList.setItems(FXCollections.observableList(requestBeanObservableList));
            setUserInfoTab();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setInfoBox(UserBean selectedSubscriber) {
        this.selectedSubscriber = selectedSubscriber;
        if(!clicked) {
            infoSubscriberBox.setDisable(false);
            infoSubscriberBox.setVisible(true);
            clicked = true;
        }
        infoName.setText(" Name: " + selectedSubscriber.getName());
        infoSurname.setText(" Surname: " + selectedSubscriber.getSurname());
        infoUsername.setText(" Username: " + selectedSubscriber.getUsername());
        infoBirth.setText(" Birth: " + selectedSubscriber.getBirth());
        infoEmail.setText(" Email: " + selectedSubscriber.getEmail());
        infoFiscalCode.setText(" FiscalCode: " + selectedSubscriber.getFiscalCode());
    }
}
