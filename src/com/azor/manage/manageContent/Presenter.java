package com.azor.manage.manageContent;

import com.azor.models.*;
import com.azor.utils.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Presenter {
    public ObservableList<Account> loadAccount() {
        ResultSet result = null;
        String query = "Select * from account where type = 1";
        PreparedStatement statement = null;
        ObservableList<Account> accounts = FXCollections.observableArrayList();
        try {
            statement = Database.getInstance().getConnection().prepareStatement(query);
            result = statement.executeQuery();
            while (result.next()) {
                Account account = new Account(result);
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public ObservableList<Drink> loadDrink() {
        ResultSet result = null;
        String query = "Select name, price, categoryID from main.FoodAndDrink";
        PreparedStatement statement = null;
        ObservableList<Drink> drinks = FXCollections.observableArrayList();
        try {
            statement = Database.getInstance().getConnection().prepareStatement(query);
            result = statement.executeQuery();
            while (result.next()) {
                Drink drink = new Drink(result);
                ResultSet tempResult = null;
                String tempQuery = "Select name from Categories where id = ?";
                PreparedStatement tempStatement = null;
                try {
                    tempStatement = Database.getInstance().getConnection().prepareStatement(tempQuery);
                    tempStatement.setString(1, drink.getCategoryID());
                    tempResult = tempStatement.executeQuery();
                    drink.setCategoryName(tempResult.getString(1));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                drinks.add(drink);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drinks;
    }

    public ObservableList<Bill> loadBill() {
        ResultSet result = null;
        String query = "Select id, creationTime from bill";
        PreparedStatement statement = null;
        ObservableList<Bill> bills = FXCollections.observableArrayList();
        try {
            statement = Database.getInstance().getConnection().prepareStatement(query);
            result = statement.executeQuery();
            while (result.next()) {
                Bill bill = new Bill(result);
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    public ObservableList<Category> loadCategory() {
        ResultSet result = null;
        String query = "Select * from Categories";
        PreparedStatement statement = null;
        ObservableList<Category> categories = FXCollections.observableArrayList();
        try {
            statement = Database.getInstance().getConnection().prepareStatement(query);
            result = statement.executeQuery();
            while (result.next()) {
                Category category = new Category(result);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public ObservableList<BillInfo> loadBillInfo(Bill selectedBill) {
        ResultSet result = null;
        String query = "Select name, price, count from BillInfo where billID = ?";
        PreparedStatement statement = null;
        ObservableList<BillInfo> bills = FXCollections.observableArrayList();
        try {
            statement = Database.getInstance().getConnection().prepareStatement(query);
            statement.setString(1, selectedBill.getID());
            result = statement.executeQuery();
            while (result.next()) {
                BillInfo bill = new BillInfo(result);
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    public void addToDatabase(Account account) throws SQLException {
        String query = "Insert into account(username, email, password, fullname, address, telphone, type) " +
                "values(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement temp = null;
        temp = Database.getInstance().getConnection().prepareStatement(query);
        temp.setString(1, account.getUsername());
        temp.setString(2, account.getEmail());
        temp.setString(3, account.getPassword());
        temp.setString(4, account.getFullname());
        temp.setString(5, account.getAddress());
        temp.setString(6, account.getTelphone());
        temp.setInt(7, account.getType());
        temp.executeUpdate();
    }

    public void deleteAccountInDatabase(Account account) {
        String query = "Delete from account where username = ?";
        PreparedStatement temp = null;
        try {
            temp = Database.getInstance().getConnection().prepareStatement(query);
            temp.setString(1, account.getUsername());
            temp.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDrinkInDatabase(Drink drink) {
        String query = "Delete from drink where name = ?";
        PreparedStatement temp = null;
        try {
            temp = Database.getInstance().getConnection().prepareStatement(query);
            temp.setString(1, drink.getName());
            temp.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCategory(String name){
        String query = "insert into Categories(name) values(?)";
        PreparedStatement temp = null;
        try {
            temp = Database.getInstance().getConnection().prepareStatement(query);
            temp.setString(1, name);
            temp.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
