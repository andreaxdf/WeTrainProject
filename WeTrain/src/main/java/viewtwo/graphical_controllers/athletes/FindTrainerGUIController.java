package viewtwo.graphical_controllers.athletes;

import controller.SubscribeToTrainerController;
import engeneering.AlertGenerator;
import engeneering.manage_list.list_cell_factories.PersonListCellFactory;
import exception.AlreadySubscribedException;
import exception.DBUnreachableException;
import exception.PaymentFailedException;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import viewone.bean.SearchBean;
import viewone.bean.UserBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class FindTrainerGUIController implements Initializable {

    @FXML private TextArea trainerDataTextArea;
    @FXML private ListView<UserBean> trainerList;
    @FXML private TextField trainerNameText;
    @FXML private Button subscribeButton;
    @FXML private Text adviceText;

    private UserBean selectedTrainer;
    private final SubscribeToTrainerController subscribeToTrainerController = new SubscribeToTrainerController();

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("TrainerPage", "athletes");
    }

    @FXML void searchButtonAction() {
        try {
            List<UserBean> userBeanList = subscribeToTrainerController.searchTrainers(new SearchBean(trainerNameText.getText()));
            trainerList.setItems(FXCollections.observableList(userBeanList));
            adviceText.setVisible(false);
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

    @FXML void subscribeButtonAction() {
        try {
            if(AlertGenerator.newConfirmationAlert("PURCHASE CONFIRMATION",
                    "Trainer subscription fee is 5$",
                    "if you click ok a payment will be sent from your selected payment method")) {
                subscribeToTrainerController.subscribeToTrainer(selectedTrainer.getFiscalCode());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        } catch (PaymentFailedException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        } catch (AlreadySubscribedException e) {
            AlertGenerator.newWarningAlert(
                    "OOPS, SOMETHING WENT WRONG!",
                    "Couldn't subscribe.",
                    "You are already subscribed to this Trainer."
            );
        }
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        trainerList.setCellFactory(nodeListView -> new PersonListCellFactory(true));
        trainerList.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<>() {
                    @Override public void changed(ObservableValue<? extends UserBean> observableValue, UserBean oldItem, UserBean newItem) {
                        if(newItem != null) {
                            selectedTrainer = newItem;
                            trainerDataTextArea.setText(String.format(
                                    """
                                    Name: %s
                                                        
                                    Surname: %s
                                                        
                                    Username: %s
                                                        
                                    Birth: %s
                                                        
                                    Email: %s
                                                        
                                    FC: %s
                                    """,
                                    selectedTrainer.getName(),
                                    selectedTrainer.getSurname(),
                                    selectedTrainer.getUsername(),
                                    selectedTrainer.getBirth().toString(),
                                    selectedTrainer.getEmail(),
                                    selectedTrainer.getFiscalCode())
                            );
                            subscribeButton.setDisable(false);
                            Platform.runLater(() -> trainerList.getSelectionModel().clearSelection());
                        }
                    }
                });
    }
}