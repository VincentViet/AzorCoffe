package com.azor.manage.manageContent;

import com.azor.models.Account;
import com.azor.utils.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Presenter {
    public ObservableList<Account> initItem(){
        ResultSet result = null;
        String query = "Select * from account where type = 1";
        PreparedStatement statement = null;
        ObservableList<Account> accounts=  FXCollections.observableArrayList();
        try {
            statement = Database.getInstance().getConnection().prepareStatement(query);
            result= statement.executeQuery();
            while (result.next()){
                Account account= new Account(result);
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

//    public void addToDatabase(Account account){
//        String query
//
//    }

    public void deleteRowInDatabase(Account account){
        String query = "Delete from account where username = ?";
        PreparedStatement temp = null;
        try {
            temp = Database.getInstance().getConnection().prepareStatement(query);
            temp.setString(1,account.getUsername());
            temp.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRowInTable(Account account){

    }
}
