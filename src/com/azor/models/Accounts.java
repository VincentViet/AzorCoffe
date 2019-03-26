package com.azor.models;

public class Accounts {
    private String username;
    private String email;
    private String password;
    private String full_name;
    private String phone;

    public Accounts(String user_name, String email, String password, String full_name, String phone) {
        this.username = user_name;
        this.email = email;
        this.password = password;
        this.full_name = full_name;
        this.phone = phone;
    }

    public Accounts() {
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

    public String getPhone() {
        return phone;
    }

    public String toString(){
        return String.format(
                        "username:  %s\n" +
                        "email:     %s\n" +
                        "password:  %s\n" +
                        "fullname:  %s\n" +
                                "phone: %s\n", username, email, password, full_name, phone);
    }
}
