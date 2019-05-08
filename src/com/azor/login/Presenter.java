package com.azor.login;

import com.azor.AzorCoffee;
import com.azor.models.Account;
import com.azor.utils.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Presenter implements IPresenter {
    private IView loginView;

    Presenter(IView loginView){
        this.loginView = loginView;
    }

    @Override
    public void loginValidate(String identifier, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        String query = "select * from Account where username = ? or email = ? or telphone = ? and password = ?;";
        try{
            preparedStatement = Database.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, identifier);
            preparedStatement.setString(2, identifier);
            preparedStatement.setString(3, identifier);
            preparedStatement.setString(4, password);

            result = preparedStatement.executeQuery();

            if (result.next()){
                AzorCoffee.currentAuth = new Account(result);
                loginView.successful();
            }else {
                loginView.failure();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            assert preparedStatement != null;
            preparedStatement.close();
            assert result != null;
            result.close();
        }
    }
}
