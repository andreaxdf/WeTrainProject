package engeneering.manage_list.list_cell_factories;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import viewone.bean.NotificationBean;

import java.io.File;
import java.io.IOException;

public class NotificationListCellFactory extends ListCell<NotificationBean> {
    private final boolean small;
    public NotificationListCellFactory(boolean small){
        this.small = small;
    }
    private Parent parentNode = null ;
    @Override public void updateItem(NotificationBean notificationBean, boolean empty){
        updateNotificationListWithParameters(notificationBean, empty);
    }

    private void updateNotificationListWithParameters(NotificationBean notificationBean, boolean empty) {
        super.updateItem(notificationBean, empty);
        if(notificationBean != null){
            try {
                if (parentNode == null)parentNode = new FXMLLoader(new File("src/main/resources/ListItem.fxml").toURI().toURL()).load();
                ((Label)parentNode.lookup("#itemName")).setText(notificationBean.getSender().getUsername());
                ((Label)parentNode.lookup("#itemCode")).setText(Integer.toString(notificationBean.getId()));
                ((Label)parentNode.lookup("#itemOwner")).setText(notificationBean.getDateTime().toString());
                ((ImageView)parentNode.lookup("#itemIcon")).setImage(new Image(new File("src/main/resources/viewone/images/" + notificationBean.getType() + ".png").toURI().toString()));
                if(small){
                    parentNode.scaleXProperty().setValue(0.8);
                    parentNode.scaleYProperty().setValue(0.8);
                }
                setGraphic(parentNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            setGraphic(null);
        }
    }
}

