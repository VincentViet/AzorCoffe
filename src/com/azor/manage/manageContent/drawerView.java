package com.azor.manage.manageContent;

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

    @FXML
    private void addData(){
        String username = tfUsername.getText().toString();
        String email = tfEmail.getText().toString();
        String password = tfPassword.getText().toString();
        String fullname = tfFullname.getText().toString();
        String address = tfAddress.getText().toString();
        String telphone = tfPhoneNumber.getText().toString();
        Account temp = new Account(username, email, password, fullname, address, telphone);

    }
}
