package com.azor.manage.manageContent;

import com.azor.AzorCoffee;
import com.azor.models.Account;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class drawerView {
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
        Account account = new Account(username, email, password, fullname, address, telphone);
        view.addCurrentData(account);
        presenter = new Presenter();
        presenter.addToDatabase(account);
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
}
