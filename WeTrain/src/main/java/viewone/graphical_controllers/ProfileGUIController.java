package viewone.graphical_controllers;

import controllers.ProfileManagementController;
import engineering.AlertGenerator;
import exceptions.DBUnreachableException;
import exceptions.invalid_data_exception.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import engineering.MainPane;
import viewone.PageSwitchSizeChange;

import java.sql.SQLException;
import java.util.List;

public abstract class ProfileGUIController extends GUIController {
    @FXML protected ImageView usrImage;
    @FXML protected Label emailLabel;
    @FXML protected Label firstNameLabel;
    @FXML protected Label fiscalCodeLabel;
    @FXML protected Label lastNameLabel;
    @FXML protected Button editButton;
    @FXML protected Pane editPane;
    @FXML protected Label paymentMethodLabel;
    @FXML protected Button deleteButton;

    private final ProfileManagementController profileManagementController = new ProfileManagementController();

    @FXML protected void editConfirmation() {
        try{
            editAction();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (InvalidDataException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        } catch (DBUnreachableException e) {
            ((Stage) editButton.getScene().getWindow()).close();
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        }
    }

    protected abstract void editAction() throws InvalidCardInfoException, EmptyFieldsException, DBUnreachableException, ExpiredCardException, SQLException, InvalidIbanException;

    @FXML protected void editAbort(){
        editPane.setDisable(true);
        editPane.setVisible(false);
        editButton.setDisable(false);
        editButton.setVisible(true);
        paymentMethodLabel.setVisible(true);
    }

    @FXML protected void editPaymentMethodButtonAction(){
        editButton.setDisable(true);
        editButton.setVisible(false);
        paymentMethodLabel.setVisible(false);
        editPane.setDisable(false);
        editPane.setVisible(true);
    }

    @FXML protected void deleteButtonAction() {
        try {
            if(AlertGenerator.newConfirmationAlert(
                    "ATTENTION!",
                    "CONFIRMATION",
                    "If you click 'ok' your account will be deleted!\n " +
                            "This operation can't be undone.")) {
                profileManagementController.deleteUser();
                PageSwitchSizeChange.logOff();
                ((Stage)editButton.getScene().getWindow()).close();
            }
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

    @FXML protected void closeAction(MouseEvent event){
        ((Stage) ((ImageView)event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    protected void setVisible(Node node, boolean bool) {
        node.setDisable(!bool);
        node.setVisible(bool);
    }
}