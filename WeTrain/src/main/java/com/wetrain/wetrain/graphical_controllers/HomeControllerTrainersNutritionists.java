package com.wetrain.wetrain.graphical_controllers;

import com.wetrain.wetrain.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public abstract class HomeControllerTrainersNutritionists extends HomeController {

    @Override
    void editButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "YourProfileTrainersNutritionists", "", false);
    }
}