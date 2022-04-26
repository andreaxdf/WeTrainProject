package viewone.graphical_controllers;

import exception.DBConnectionFailedException;
import exception.ExpiredCardException;
import exception.InvalidCardInfoException;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import viewone.WeTrain;
import viewone.engeneering.LoggedUserSingleton;
import viewone.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.bean.UserBean;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Objects;

public abstract class HomeGUIController {
    @FXML protected abstract void editButtonAction(ActionEvent event) throws IOException;
    @FXML protected Text usernameText1;
    @FXML protected ImageView userImage;

    protected void setUserInfoTab() {
        UserBean user = getLoggedUser();
        usernameText1.setText(user.getUsername());
        char gender = user.getGender();
        String type = user.getType();
        if(Objects.equals(type, "Trainer")){
            if(gender == 'm') {
                setImage("TrainerM");
            }else{
                setImage("TrainerF");
            }
        }else{
            if(gender == 'm') {
                setImage("AthleteM");
            }else{
                setImage("AthleteF");
            }
        }

    }

    private void setImage(String str){
        try {
            userImage.setImage(new Image(Objects.requireNonNull(WeTrain.class.getResource("images/"+str+".png")).toURI().toString()));
        } catch (URISyntaxException e) {
            //TODO
            throw new RuntimeException(e);
        }
    }

    protected UserBean getLoggedUser(){
        try{
            return Objects.requireNonNull(LoggedUserSingleton.getInstance());
        } catch (ExpiredCardException | InvalidCardInfoException e){
            e.alert();
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBConnectionFailedException e) {
            e.alert();
        }
        return null;
    }

    @FXML void logoutButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "WeTrainGUI", "launcher", true);
    }

    @FXML protected void closeAction(MouseEvent event){
        ((Stage) ((ImageView)event.getSource()).getScene().getWindow()).close();
    }
}
