package com.azor.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
    private int id;
    private String username;
    private String email;
    private String fullname;
    private String address;
    private String telphone;
    private int type;

    public Account(ResultSet set){
        try {
            id = set.getInt("id");
            username = set.getString("username");
            email = set.getString("email");
            fullname = set.getString("fullname");
            address = set.getString("address");
            telphone = set.getString("telphone");
            type = set.getInt("type");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFullname() {
        return fullname;
    }

    public String getAddress() {
        return address;
    }

    public String getTelphone() {
        return telphone;
    }

    public int getType() {
        return type;
    }
}
