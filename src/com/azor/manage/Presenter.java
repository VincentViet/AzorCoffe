package com.azor.manage;

import com.azor.AzorCoffee;
import javafx.application.Platform;

public class Presenter implements IPresenter{
    private IView manageView;

    Presenter(IView manageView){
        this.manageView = manageView;
    }
}
