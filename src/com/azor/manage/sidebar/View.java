package com.azor.manage.sidebar;

import animatefx.animation.Bounce;
import com.azor.AzorCoffee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class View implements Initializable {
    @FXML
    private Button homeButton;

    @FXML
    private Button accountButton;

    @FXML
    private Button manageButton;

    @FXML
    private Button chartButton;

    private List<Button> buttons;

    @FXML
    private void clicked(ActionEvent event){
        int index = 0;
        com.azor.manage.View view = AzorCoffee.fxmlMap.get("manage").getController();
        for (Button button:
             buttons) {
            if (button != event.getSource()){
                button.getStyleClass().clear();
                button.getStyleClass().add("button");
            }else {
                new Bounce(button).setSpeed(1.5).play();
                button.getStyleClass().add("button-selected");

                view.changeContent(index);
            }
            index++;
        }

        if (event.getSource() == accountButton){
            view.loadCurrentAuthInformation();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons = new ArrayList<>();
        buttons.add(homeButton);
        buttons.add(accountButton);
        buttons.add(manageButton);
        buttons.add(chartButton);
    }
}
