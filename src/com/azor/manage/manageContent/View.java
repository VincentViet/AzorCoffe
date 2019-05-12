package com.azor.manage.manageContent;

import com.azor.models.Account;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class View implements Initializable {

    ObservableList<Account> listItem;

    @FXML
    private JFXTreeTableView<Account> treeTableView;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXDrawer drawerAdd;

    @FXML
    private JFXTextField tfSearchBar;

    Presenter presenter = new Presenter();

    @FXML
    private void toggleDrawer() {
        drawerAdd.toggle();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Initialize tree table view collumns
        JFXTreeTableColumn<Account, String> username = new JFXTreeTableColumn<>("Username");
        username.setPrefWidth(150);
        username.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Account, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Account, String> param) {
                return param.getValue().getValue().usernameProperty();
            }
        });
        username.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Account, String> password = new JFXTreeTableColumn<>("Password");
        password.setPrefWidth(150);
        password.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Account, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Account, String> param) {
                return param.getValue().getValue().passwordProperty();
            }
        });
        password.setStyle("-fx-alignment: center");

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
        fullname.setPrefWidth(150);
        fullname.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Account, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Account, String> param) {
                return param.getValue().getValue().fullnameProperty();
            }
        });
        fullname.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Account, String> address = new JFXTreeTableColumn<>("Address");
        address.setPrefWidth(253);
        address.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Account, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Account, String> param) {
                return param.getValue().getValue().addressProperty();
            }
        });
        address.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Account, String> telphone = new JFXTreeTableColumn<>("Telephone Number");
        telphone.setPrefWidth(150);
        telphone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Account, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Account, String> param) {
                return param.getValue().getValue().telphoneProperty();
            }
        });
        telphone.setStyle("-fx-alignment: center");

        // Initialize tree table view items
        listItem = presenter.initItem();

        // Create tree
        final TreeItem<Account> root = new RecursiveTreeItem<Account>(listItem, RecursiveTreeObject::getChildren);
        treeTableView.getColumns().setAll(username, password, email, fullname, address, telphone);
        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);

        // Load UI drawer.fxml to drawerAdd
        try {
            VBox drawerContent = FXMLLoader.load(getClass().getResource("drawer.fxml"));
            drawerAdd.setSidePane(drawerContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tfSearchBar.textProperty().addListener(setupSearchField(treeTableView));
    }


    @FXML
    private void deleteRow() {
        Account currentSelected = treeTableView.getSelectionModel().selectedItemProperty().get().getValue();
        presenter.deleteRowInDatabase(currentSelected);
        deleteRowInTreeTableView(currentSelected);

    }

    private void deleteRowInTreeTableView(Account account) {
        listItem.remove(account);
        final IntegerProperty currCountProp = treeTableView.currentItemsCountProperty();
        currCountProp.set(currCountProp.get() - 1);
    }

    public void addData(Account account) {
        listItem.add(account);
    }

    private ChangeListener<String> setupSearchField(final JFXTreeTableView<Account> treeTableView) {
        return (o, oldVal, newVal) ->
                treeTableView.setPredicate(personProp -> {
                    final Account account = personProp.getValue();
                    return account.getUsername().contains(newVal)
                            || account.getFullname().contains(newVal)
                            || account.getEmail().contains(newVal)
                            || account.getTelphone().contains(newVal)
                            || account.getAddress().contains(newVal);
                });
    }
}
