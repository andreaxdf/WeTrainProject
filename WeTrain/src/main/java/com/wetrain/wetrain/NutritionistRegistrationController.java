package com.wetrain.wetrain;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class NutritionistRegistrationController {
    @FXML
    private Button attachButt;
    @FXML
    private Text homeButt;
    @FXML
    private Button infoButt;
    @FXML
    private ImageView logo;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button submitButt;
    @FXML
    protected void attachButtonEntered(){
        attachButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 10");
    }
    @FXML
    protected void attachButtonExited() {
        attachButt.setStyle("-fx-background-color: rgba(24, 147, 21, 1); -fx-background-radius: 10");
    }
    @FXML
    void attachButtonAction() {
        System.out.println("attach effettuato!");
    }
    @FXML
    void infoButtonAction() {
        System.out.println("info");
    }
    @FXML
    protected void homeButtonAction() throws IOException {
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("WeTrainGUI");
        mainPane.setCenter(view);
    }
    @FXML
    protected void submitButtonAction() throws IOException {
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("NutritionistsHome");
        mainPane.setCenter(view);
    }
    @FXML
    protected void submitButtonEntered(){
        submitButt.setStyle("-fx-background-color: rgb(20, 130, 17); -fx-background-radius: 50");
    }
    @FXML
    protected void submitButtonExited() {
        submitButt.setStyle("-fx-background-color: rgba(24, 147, 21, 1); -fx-background-radius: 50");
    }
}
