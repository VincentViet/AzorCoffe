package com.azor.manage.manageContent;

import com.azor.models.Account;
import com.azor.models.Drink;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.effects.JFXDepthManager;
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

    ObservableList<Account> listAccount;
    ObservableList<Drink> listDrink;

    @FXML
    private JFXTreeTableView<Account> treeviewAccount;

    @FXML
    private JFXTreeTableView<Drink> treeviewDrink;

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

        initAccountTable();
        initDrinkTable();

        // Load UI drawer.fxml to drawerAdd
        try {
            VBox drawerContent = FXMLLoader.load(getClass().getResource("drawer.fxml"));
            drawerAdd.setSidePane(drawerContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JFXDepthManager depthManager = new JFXDepthManager();
        depthManager.setDepth(drawerAdd, 4);

        tfSearchBar.textProperty().addListener(setupSearchField(treeviewAccount));
    }

    void initAccountTable(){
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

        // load data from database
        listAccount = presenter.loadAccount();

        // Create tree
        final TreeItem<Account> root = new RecursiveTreeItem<Account>(listAccount, RecursiveTreeObject::getChildren);
        treeviewAccount.getColumns().setAll(username, password, email, fullname, address, telphone);
        treeviewAccount.setRoot(root);
        treeviewAccount.setShowRoot(false);
    }

    void initDrinkTable(){
        JFXTreeTableColumn<Drink, String> drinkName = new JFXTreeTableColumn<>("Name");
        drinkName.setPrefWidth(275);
        drinkName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Drink, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Drink, String> param) {
                return param.getValue().getValue().nameProperty();
            }
        });
        drinkName.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Drink, String> drinkPrice = new JFXTreeTableColumn<>("Price");
        drinkPrice.setPrefWidth(275);
        drinkPrice.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Drink, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Drink, String> param) {
                return param.getValue().getValue().priceProperty();
            }
        });
        drinkPrice.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Drink, String> categoryID = new JFXTreeTableColumn<>("Category ID");
        categoryID.setPrefWidth(275);
        categoryID.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Drink, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Drink, String> param) {
                return param.getValue().getValue().categoryIDProperty();
            }
        });
        categoryID.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Drink, String> categoryName = new JFXTreeTableColumn<>("Category Name");
        categoryName.setPrefWidth(278);
        categoryName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Drink, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Drink, String> param) {
                return param.getValue().getValue().categoryNameProperty();
            }
        });
        categoryName.setStyle("-fx-alignment: center");

        listDrink = presenter.loadDrink();

        final TreeItem<Drink> root = new RecursiveTreeItem<Drink>(listDrink, RecursiveTreeObject::getChildren);
        treeviewDrink.getColumns().setAll(drinkName, drinkPrice, categoryID,categoryName);
        treeviewDrink.setRoot(root);
        treeviewDrink.setShowRoot(false);
    }


    @FXML
    private void deleteRow() {
        Account currentSelected = treeviewAccount.getSelectionModel().selectedItemProperty().get().getValue();
        presenter.deleteRowInDatabase(currentSelected);
        deleteRowInTreeTableView(currentSelected);

    }

    private void deleteRowInTreeTableView(Account account) {
        listAccount.remove(account);
        final IntegerProperty currCountProp = treeviewAccount.currentItemsCountProperty();
        currCountProp.set(currCountProp.get() - 1);
    }



    public void addDataToTable(Account account) {
        listAccount.add(account);
    }

    // Search bar logic
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
