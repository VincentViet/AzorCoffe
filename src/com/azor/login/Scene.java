package com.azor.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;

import com.azor.AzorCoffee;

public class Scene implements IView{
    @FXML
    private JFXTextField        userField;

    @FXML
    private JFXPasswordField    passwordField;

    @FXML
    private JFXButton           loginButton;

    @FXML
    private Label               statusBar;

    private IPresenter presenter;

    public Scene(){
        presenter = new Presenter(this);
    }

    @FXML
    private void login(ActionEvent event){
        presenter.LoginChecking(userField.getText(), passwordField.getText());
    }

    @FXML
    private void loginWithEnterKey(KeyEvent event){
        if (event.getCode() == KeyCode.ENTER)
            presenter.LoginChecking(userField.getText(), passwordField.getText());
    }

    @Override
    public void successful() {
        statusBar.setText("Success!");
        statusBar.setTextFill(Paint.valueOf("#28a745"));

        AzorCoffee.primaryStage.setScene(AzorCoffee.managerScene);
        AzorCoffee.primaryStage.centerOnScreen();
    }

    @Override
    public void failure() {
        statusBar.setText("Your username/email or password is wrong!");
    }
}
