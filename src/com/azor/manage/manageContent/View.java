package com.azor.manage.manageContent;

import com.azor.models.Account;
import com.azor.utils.Database;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class View implements Initializable {

    @FXML
    private JFXTreeTableView<Account> treeTableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        JFXTreeTableColumn<Account, String> username = new JFXTreeTableColumn<>("Username");
        username.setPrefWidth(175);
        username.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Account, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Account, String> param) {
                return param.getValue().getValue().usernameProperty();
            }
        });
        username.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Account, String> email = new JFXTreeTableColumn<>("Email");
        email.setPrefWidth(250);
        email.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Account, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Account, String> param) {
                return param.getValue().getValue().emailProperty();
            }
        });
        email.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Account, String> fullname = new JFXTreeTableColumn<>("Full name");
        fullname.setPrefWidth(175);
        fullname.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Account, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Account, String> param) {
                return param.getValue().getValue().fullnameProperty();
            }
        });
        fullname.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Account, String> address = new JFXTreeTableColumn<>("Address");
        address.setPrefWidth(250);
        address.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Account, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Account, String> param) {
                return param.getValue().getValue().addressProperty();
            }
        });
        address.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Account, String> telphone = new JFXTreeTableColumn<>("Telephone Number");
        telphone.setPrefWidth(175);
        telphone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Account, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Account, String> param) {
                return param.getValue().getValue().telphoneProperty();
            }
        });
        telphone.setStyle("-fx-alignment: center");

        ObservableList<Account> listItem = initItem();

        final TreeItem<Account> root = new RecursiveTreeItem<Account>(listItem, RecursiveTreeObject::getChildren);
        treeTableView.getColumns().setAll(username, email, fullname, address, telphone);
        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);
    }

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
}
