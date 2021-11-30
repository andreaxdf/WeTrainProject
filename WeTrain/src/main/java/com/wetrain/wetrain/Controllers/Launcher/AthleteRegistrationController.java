package com.wetrain.wetrain.Controllers.Launcher;

import com.wetrain.wetrain.PageSwitchSimple;
import com.wetrain.wetrain.PasswordBehaviorActivation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AthleteRegistrationController implements Initializable {
    @FXML
    private Text homeText;
    @FXML
    private Text profileText;
    @FXML
    private ImageView logo;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button continueButton;
    @FXML
    private Button eyeButton;
    @FXML
    private TextField passwSField;
    @FXML
    private PasswordField passwField;
    @FXML
    private CheckBox checkVisible;
    @FXML
    void eyeButtonAction() {
        checkVisible.fire();
    }
    @FXML
    protected void profileButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("ProfileSelection", "Launcher");
        mainPane.setCenter(view);
    }
    @FXML
    protected void homeButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("WeTrainGUI", "Launcher");
        mainPane.setCenter(view);
    }
    @FXML
    protected void continueButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("MoreInfo", "Launcher");
        MoreInfoController.string = "Athlete";
        mainPane.setCenter(view);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PasswordBehaviorActivation.passwordFieldBind(passwSField, passwField, checkVisible);
    }
}
