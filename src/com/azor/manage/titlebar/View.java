package com.azor.manage.titlebar;

import com.azor.AzorCoffee;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class View {
    @FXML
    private void close(MouseEvent event){
        Platform.exit();
    }

    @FXML
    private void minimize(MouseEvent event) {
        AzorCoffee.primaryStage.setIconified(true);
    }
}
