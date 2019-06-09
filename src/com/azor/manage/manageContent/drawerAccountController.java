package com.azor.manage.manageContent;

import com.azor.AzorCoffee;
import com.azor.models.Account;
import com.azor.utils.CodeGenerator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class drawerAccountController implements Initializable {
    @FXML
    private JFXTextField tfUsername;

    @FXML
    private JFXTextField tfFullname;

    @FXML
    private JFXTextField tfPassword;

    @FXML
    private JFXTextField tfPhoneNumber;

    @FXML
    private JFXButton btnConfirm;

    @FXML
    private JFXButton btnReset;

    @FXML
    private JFXTextField tfEmail;

    @FXML
    private JFXTextField tfAddress;

    Presenter presenter;

    @FXML
    private void addData(){
        com.azor.manage.View view = AzorCoffee.fxmlMap.get("manage").getController();
        String username = tfUsername.getText().toString();
        String email = tfEmail.getText().toString();
        String password = tfPassword.getText().toString();
        String fullname = tfFullname.getText().toString();
        String address = tfAddress.getText().toString();
        String telphone = tfPhoneNumber.getText().toString();
        if ((username.length()!=0) && (email.length()!=0) && (password.length() !=0) && (fullname.length()!=0) && (address.length()!=0) && (telphone.length()!=0))
        {
            Account account = new Account(username, email, password, fullname, address, telphone);
            presenter = new Presenter();
            try {
                presenter.addToDatabase(account);
                view.addAccount(account);
            }catch (SQLException e){
                showAlert();
            }
        }
        else {
            showAlert();
        }
    }

    @FXML
    private void resetTextField(){
        tfUsername.setText("");
        tfEmail.setText("");
        tfPassword.setText("");
        tfFullname.setText("");
        tfAddress.setText("");
        tfPhoneNumber.setText("");
    }

    @FXML
    private void generateIDPassword(){
        tfUsername.setText(CodeGenerator.getInstance().nextCode());
        tfPassword.setText("12345");
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Missing input or input not match requirement !");
        alert.setContentText("Please check your input");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        tfFullname.getValidators().add(validator);
        validator.setMessage("No input given");
        tfFullname.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){
                    tfFullname.validate();
                }
            }
        });

        NumberValidator numberValidator = new NumberValidator();
        tfPhoneNumber.getValidators().add(numberValidator);
        numberValidator.setMessage("Not correct phone number format");
        tfPhoneNumber.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){
                    tfPhoneNumber.validate();
                }
            }
        });

        tfEmail.getValidators().add(validator);
        tfEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){
                    tfEmail.validate();
                }
            }
        });

        tfAddress.getValidators().add(validator);
        tfAddress.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){
                    tfAddress.validate();
                }
            }
        });
    }
}
