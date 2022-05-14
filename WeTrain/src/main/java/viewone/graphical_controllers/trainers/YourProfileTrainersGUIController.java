package viewone.graphical_controllers.trainers;

import controller.ProfileManagementController;
import database.dao_classes.TrainerDAO;
import exception.*;
import exception.invalid_data_exception.InvalidDataException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import viewone.PageSwitchSizeChange;
import viewone.WeTrain;
import viewone.bean.*;
import viewone.engeneering.AlertFactory;
import viewone.engeneering.LoggedUserSingleton;
import viewone.graphical_controllers.ProfileGUIController;

import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class YourProfileTrainersGUIController extends ProfileGUIController implements Initializable {
    @FXML private TextField newIban;
    @FXML private Text subscribersText;

    TrainerBean trainer;

    private final ProfileManagementController profileManagementController = new ProfileManagementController();

    @FXML private void editConfirmation() {
        if(!Objects.equals(newIban.getText(), "")) {
            try{
                IbanBean ibanBean = IbanBean.ctorWithSyntaxCheck(newIban.getText());
                profileManagementController.updateTrainerIban(ibanBean);
                trainer = (TrainerBean) LoggedUserSingleton.getInstance();
                setVisible(editPane, false);
                setIbanLabel();
                setVisible(editButton, true);
                paymentMethodLabel.setVisible(true);
            } catch (SQLException e){
                throw new RuntimeException();
            } catch (InvalidDataException e) {
                List<String> errorStrings = e.getErrorStrings();
                AlertFactory.newWarningAlert(
                        errorStrings.get(0),
                        errorStrings.get(1),
                        errorStrings.get(2));
            } catch (DBUnreachableException e) {
                ((Stage) editButton.getScene().getWindow()).close();
                List<String> errorStrings = e.getErrorStrings();
                AlertFactory.newWarningAlert(
                        errorStrings.get(0),
                        errorStrings.get(1),
                        errorStrings.get(2));
                PageSwitchSizeChange.logOff();
            }
        }
    }

    private void setIbanLabel() {
        if(trainer.getIban() == null){
            paymentMethodLabel.setText("Iban: Not Inserted yet!");
        }else {
            paymentMethodLabel.setText("Iban: " + trainer.getIban());
        }
    }

    @FXML protected void editIbanButtonAction(){
        editButton.setDisable(true);
        editButton.setVisible(false);
        paymentMethodLabel.setVisible(false);
        editPane.setDisable(false);
        editPane.setVisible(true);
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        trainer = (TrainerBean) getLoggedUser();
        emailLabel.setText("Email: " + trainer.getEmail());
        firstNameLabel.setText(trainer.getName());
        lastNameLabel.setText(trainer.getSurname());
        fiscalCodeLabel.setText("FiscalCode: " + trainer.getFiscalCode());
        try{
            subscribersText.setText(String.valueOf(new TrainerDAO().getNumberOfSubscribers(trainer.getFiscalCode())));
            if(trainer.getGender() == 'm') {
                usrImage.setImage(new Image(Objects.requireNonNull(WeTrain.class.getResource("images/TrainerM.png")).toURI().toString()));
            }else{
                usrImage.setImage(new Image(Objects.requireNonNull(WeTrain.class.getResource("images/TrainerF.png")).toURI().toString()));
            }
            setIbanLabel();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertFactory.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
