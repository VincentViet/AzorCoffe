package com.azor.models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Account extends RecursiveTreeObject<Account> {
    private int id;
    private StringProperty username;
    private StringProperty email;
    private StringProperty password;
    private StringProperty fullname;
    private StringProperty address;
    private StringProperty telphone;
    private int type;

    public Account(ResultSet set){
        try {
            id = set.getInt("id");
            username = new SimpleStringProperty(set.getString("username"));
            email = new SimpleStringProperty(set.getString("email"));
            fullname = new SimpleStringProperty(set.getString("fullname"));
            address = new SimpleStringProperty(set.getString("address"));
            telphone = new SimpleStringProperty(set.getString("telphone"));
            type = set.getInt("type");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getFullname() {
        return fullname.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getTelphone() {
        return telphone.get();
    }

    public String getPassword(){ return password.get();}

    public StringProperty usernameProperty(){
        return username;
    }

    public StringProperty emailProperty(){
        return email;
    }

    public StringProperty passwordProperty(){
        return password;
    }

    public StringProperty fullnameProperty(){
        return fullname;
    }

    public StringProperty addressProperty(){
        return address;
    }

    public StringProperty telphoneProperty(){
        return telphone;
    }

    public int getType() {
        return type;
    }
}
