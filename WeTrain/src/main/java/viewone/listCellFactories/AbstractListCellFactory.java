package viewone.listCellFactories;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import viewone.WeTrain;
import viewone.bean.CourseEssentialBean;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public abstract class AbstractListCellFactory extends ListCell<CourseEssentialBean> {
    private Parent parentNode = null ;

    public void updateWithParameter(CourseEssentialBean courseBean, boolean empty, String str) {
        super.updateItem(courseBean, empty);
        if(courseBean != null){
            try {
                if (parentNode == null)parentNode = new FXMLLoader(WeTrain.class.getResource("ListItem.fxml")).load();
                ((Label)parentNode.lookup("#itemName")).setText(courseBean.getName());
                ((Label)parentNode.lookup("#itemCode")).setText(Integer.toString(courseBean.getId()));
                ((Label)parentNode.lookup("#itemOwner")).setText("\uD83D\uDC68 "+courseBean.getOwner());
                ((ImageView)parentNode.lookup("#itemIcon")).setImage(new Image(Objects.requireNonNull(WeTrain.class.getResource("images/" + str + ".png)")).toURI().toString()));
                setGraphic(parentNode);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }else{
            setGraphic(null);
        }
    }

}
