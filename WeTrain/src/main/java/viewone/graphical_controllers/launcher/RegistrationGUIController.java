package viewone.graphical_controllers.launcher;

import exceptions.invalid_data_exception.InvalidDataException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import engineering.MainPane;
import viewone.PageSwitchSimple;
import beans.CredentialsBean;
import engineering.AlertGenerator;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class RegistrationGUIController extends LauncherGUIController{
    private static final String HOME = "launcher";
    private String selectedProfile;

    @FXML private TextField emailField;
    @FXML private TextField passwField;
    @FXML private TextField passwSField;

    @FXML private void homeTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"WeTrainGUI", HOME);
    }

    @FXML private void continueButtonAction() throws IOException {
        try {
            CredentialsBean bean = CredentialsBean.ctorWithSyntaxCheck(emailField.getText(), passwField.getText());
            MoreInfoGUIController moreInfoGUIController = (MoreInfoGUIController) Objects.requireNonNull(PageSwitchSimple.switchPage(MainPane.getInstance(), "MoreInfo", HOME));
            moreInfoGUIController.setCredentialInfo(bean);
            moreInfoGUIController.setSelectedProfileString(selectedProfile);
        } catch (InvalidDataException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        }
    }

    @FXML private void profileTextAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"ProfileSelection", HOME);
    }

    @FXML void keyHandler(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER){
            continueButtonAction();
        }
        if(event.getCode() == KeyCode.ESCAPE) {
            profileTextAction();
        }
        if(event.getCode() == KeyCode.F1){
            if(emailField.isFocused()){
                if(passwField.isVisible()){
                    passwField.requestFocus();
                }else{
                    passwSField.requestFocus();
                }
            }else {
                emailField.requestFocus();
            }
        }
    }

    public void setSelectedProfile(String selectedProfile) {
        this.selectedProfile = selectedProfile;
    }
}
