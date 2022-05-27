package viewtwo.graphical_controllers.launcher;

import controller.LoginController;
import engeneering.AlertGenerator;
import engeneering.LoggedUserSingleton;
import exception.DBUnreachableException;
import exception.UserNotFoundException;
import exception.invalid_data_exception.InvalidDataException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import viewone.bean.AthleteBean;
import viewone.bean.CredentialsBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginGUIController {

    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordField;

    private final LoginController loginController = new LoginController();

    @FXML void backAction() throws IOException {
        PageSwitchSimple.switchPage("WeTrainGUI", "launcher");
    }

    @FXML void loginAction() {
        try {
            loginController.login(CredentialsBean.ctorWithSyntaxCheck(emailTextField.getText(), passwordField.getText()));
            if(LoggedUserSingleton.getInstance() instanceof AthleteBean){
                PageSwitchSimple.switchPage("AthletesHome", "athletes");
            } else {
                PageSwitchSimple.switchPage("TrainersHome", "trainers");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } catch (DBUnreachableException | InvalidDataException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        } catch (UserNotFoundException e) {
            AlertGenerator.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                    "User not found.",
                    "Be sure that you have an account on WeTrain.");
        }
    }

}