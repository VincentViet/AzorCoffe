package com.azor.manager;

import com.azor.AzorCoffee;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;

class Presenter implements IPresenter {
    IView view;

    Presenter(IView view){
        this.view = view;
    }

    @Override
    public void minimize() {
        AzorCoffee.primaryStage.setIconified(true);
    }

    @Override
    public void maximize() {
        AzorCoffee.primaryStage.setFullScreenExitHint("Press ESC to exit fullscreen mode.");
        AzorCoffee.primaryStage.setFullScreen(true);
    }

    @Override
    public void close() {
        Platform.exit();
    }

    @Override
    public void invoice(JFXListView<String> Invoice ) {
        
    }

}
