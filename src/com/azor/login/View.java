package com.azor.login;

import com.azor.AzorCoffee;
import com.azor.utils.Configurator;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class View implements IView, Initializable {
    @FXML
    private JFXTextField        identifier;

    @FXML
    private JFXPasswordField    passwordField;

    @FXML
    private Label statusBar;

    @FXML
    private JFXCheckBox         checkBox;

    private IPresenter presenter;
    public View(){
        presenter = new Presenter(this);
    }

    @FXML
    private void onLogin(ActionEvent event) throws SQLException {
        onChecked(null);
        presenter.loginValidate(identifier.getText(), passwordField.getText());
    }

    @FXML
    private void onLoginWithEnterKey(KeyEvent event) throws SQLException {
        onChecked(null);
        if (event.getCode() == KeyCode.ENTER)
            presenter.loginValidate(identifier.getText(), passwordField.getText());
    }

    @FXML
    private void onChecked(ActionEvent event){
        if (checkBox.isSelected()){
            Configurator.getInstance().setProperties("lastIdentifier", identifier.getText());
            Configurator.getInstance().setProperties("lastPassword", passwordField.getText());
            Configurator.getInstance().setProperties("remember", "true");
        }else {
            Configurator.getInstance().setProperties("lastIdentifier", "");
            Configurator.getInstance().setProperties("lastPassword", "");
            Configurator.getInstance().setProperties("remember", "false");
        }
    }

    @Override
    public void successful() {
        AzorCoffee.primaryStage.setScene(AzorCoffee.sceneMap.get("manage"));
        AzorCoffee.primaryStage.centerOnScreen();
    }

    @Override
    public void failure() {
        statusBar.setTextFill(Paint.valueOf("#ff4444"));
        statusBar.setText("Your username/email/phone number or password is wrong!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String remember = Configurator.getInstance().getProperty("remember");
        if (remember.equals("true")){
            checkBox.setSelected(true);
            identifier.setText(Configurator.getInstance().getProperty("lastIdentifier"));
            passwordField.setText(Configurator.getInstance().getProperty("lastPassword"));
        }else{
            checkBox.setSelected(false);
            identifier.setText("");
            passwordField.setText("");
        }
    }
}
