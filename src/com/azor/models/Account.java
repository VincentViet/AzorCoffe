package com.azor.models;

public class Account {
    private int account_id;
    private String username;
    private String password;
    private String email;
    private String full_name;

    public Account(int accout_id, String username, String email, String password, String full_name) {
        this.account_id=accout_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.full_name = full_name;
    }

    public Account() {
    }

    public int getAccount_id() {
        return account_id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFull_name() {
        return full_name;
    }

    public String toString(){
        return String.format(
                        "account_id:   %s\n" +
                        "username:  %s\n" +
                        "email:     %s\n" +
                        "password:  %s\n" +
                        "full_name:  %s\n", account_id, username, email, password, full_name);
    }
}
