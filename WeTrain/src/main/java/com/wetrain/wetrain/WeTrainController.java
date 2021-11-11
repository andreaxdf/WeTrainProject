package com.wetrain.wetrain;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class WeTrainController {
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button regButt;
    @FXML
    private Text signInText;
    @FXML
    protected void registerButtonAction() throws IOException {
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("ProfileSelection");
        mainPane.setCenter(view);
    }
    @FXML
    protected void signInTextAction() throws IOException {
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("Login");
        mainPane.setCenter(view);
    }
    @FXML
    protected void registerButtonEntered(){
        regButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 50");
    }
    @FXML
    protected void registerButtonExited() {
        regButt.setStyle("-fx-background-color: rgba(24, 147, 21, 1); -fx-background-radius: 50");
    }
    @FXML
    protected void signInButtonEntered(){
        signInText.setStyle("-fx-fill: rgb(20, 130, 17)");
    }
    @FXML
    protected void signInButtonExited(){
        signInText.setStyle("-fx-fill: rgba(24, 147, 21, 1);");
    }
}