package viewone.graphical_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import engineering.MainPane;

public abstract class AbstractFormGUIController{
    @FXML protected Button sendButton;

    @FXML protected void closeAction(){
        close();
    }

    protected void close() {
        ((Stage) sendButton.getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    @FXML protected void sendButtonAction() {
        sendAction();
    }

    protected abstract void sendAction();
}
