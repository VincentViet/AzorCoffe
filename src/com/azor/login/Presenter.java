package com.azor.login;

import com.azor.utils.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Presenter implements IPresenter {
    private IView loginView;

    Presenter(IView loginView){
        this.loginView = loginView;
    }

    @Override
    public void LoginChecking(String username, String password) {
        try {
            ResultSet result = Database.getInstance()
                                .query(String.format("SELECT password " +
                                        "FROM Accounts " +
                                        "WHERE username = '%s' " +
                                        "OR email = '%s';", username, username));
            if (result.first() &&
                    result.getString(1).equals(password)){
                loginView.successful();
            }else
                loginView.failure();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
