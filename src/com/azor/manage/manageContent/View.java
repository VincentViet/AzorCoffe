package com.azor.manage.manageContent;

import com.azor.models.Account;
import com.azor.models.Bill;
import com.azor.models.BillInfo;
import com.azor.models.Drink;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.effects.JFXDepthManager;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;

import java.util.*;

public class View implements Initializable {

    ObservableList<Account> listAccount;
    ObservableList<Drink> listDrink;
    ObservableList<Bill> listBill;
    ObservableList<BillInfo> listBillInfo;

    @FXML
    private JFXTreeTableView<Account> treeviewAccount;

    @FXML
    private JFXTreeTableView<Drink> treeviewDrink;

    @FXML
    private JFXTreeTableView<Bill> treeviewBill;

    @FXML
    private JFXTreeTableView<BillInfo> treeviewBillInfo;

    @FXML
    private JFXDrawer drawerAddAccount;

    @FXML
    private JFXDrawer drawerAddFood;

    @FXML
    private JFXTextField tfSearchBarAccount;

    @FXML
    private JFXTextField tfSearchBarDrink;

    @FXML
    private JFXTextField tfSearchBarBill;

    Presenter presenter = new Presenter();

    @FXML
    private void toggleDrawerAccount() {
        try {
            VBox drawerContent = FXMLLoader.load(getClass().getResource("drawerAccount.fxml"));
            drawerAddAccount.setSidePane(drawerContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawerAddAccount.toggle();
    }

    @FXML
    private void toggleDrawerFood() {
        try {
            VBox drawerContent = FXMLLoader.load(getClass().getResource("drawerFood.fxml"));
            drawerAddFood.setSidePane(drawerContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawerAddFood.toggle();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initAccountTable();
        initDrinkTable();
        initBillTable();
        initBillInfoTable();

        JFXDepthManager depthManager = new JFXDepthManager();
        depthManager.setDepth(drawerAddAccount, 4);
        depthManager.setDepth(drawerAddFood, 4);

        tfSearchBarAccount.textProperty().addListener(setupSearchFieldAccount(treeviewAccount));
        tfSearchBarDrink.textProperty().addListener(setupSearchFieldDrink(treeviewDrink));
        tfSearchBarBill.textProperty().addListener(setupSearchFieldBill(treeviewBill));
    }

    void initAccountTable() {
        // Initialize tree table view collumns
        JFXTreeTableColumn<Account, String> username = new JFXTreeTableColumn<>("Username");
        username.setPrefWidth(125);
        username.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Account, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Account, String> param) {
                return param.getValue().getValue().usernameProperty();
            }
        });
        username.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Account, String> password = new JFXTreeTableColumn<>("Password");
        password.setPrefWidth(125);
        password.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Account, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Account, String> param) {
                return param.getValue().getValue().passwordProperty();
            }
        });
        password.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Account, String> email = new JFXTreeTableColumn<>("Email");
        email.setPrefWidth(225);
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
        address.setPrefWidth(205);
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

        // load data from database
        listAccount = presenter.loadAccount();

        // allow multiple select
        treeviewAccount.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Create tree
        final TreeItem<Account> root = new RecursiveTreeItem<Account>(listAccount, RecursiveTreeObject::getChildren);
        treeviewAccount.getColumns().setAll(username, password, email, fullname, address, telphone);
        treeviewAccount.setRoot(root);
        treeviewAccount.setShowRoot(false);
    }

    void initDrinkTable() {
        JFXTreeTableColumn<Drink, String> drinkName = new JFXTreeTableColumn<>("Name");
        drinkName.setPrefWidth(325);
        drinkName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Drink, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Drink, String> param) {
                return param.getValue().getValue().nameProperty();
            }
        });
        drinkName.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Drink, String> drinkPrice = new JFXTreeTableColumn<>("Price");
        drinkPrice.setPrefWidth(180);
        drinkPrice.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Drink, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Drink, String> param) {
                return param.getValue().getValue().priceProperty();
            }
        });
        drinkPrice.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Drink, String> categoryID = new JFXTreeTableColumn<>("Category ID");
        categoryID.setPrefWidth(175);
        categoryID.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Drink, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Drink, String> param) {
                return param.getValue().getValue().categoryIDProperty();
            }
        });
        categoryID.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Drink, String> categoryName = new JFXTreeTableColumn<>("Category Name");
        categoryName.setPrefWidth(325);
        categoryName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Drink, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Drink, String> param) {
                return param.getValue().getValue().categoryNameProperty();
            }
        });
        categoryName.setStyle("-fx-alignment: center");

        listDrink = presenter.loadDrink();

        // allow multiple select
        treeviewAccount.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        final TreeItem<Drink> root = new RecursiveTreeItem<Drink>(listDrink, RecursiveTreeObject::getChildren);
        treeviewDrink.getColumns().setAll(drinkName, drinkPrice, categoryID, categoryName);
        treeviewDrink.setRoot(root);
        treeviewDrink.setShowRoot(false);
    }

    void initBillTable() {
        JFXTreeTableColumn<Bill, String> billID = new JFXTreeTableColumn<>("ID");
        billID.setPrefWidth(125);
        billID.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Bill, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Bill, String> param) {
                return param.getValue().getValue().IDProperty();
            }
        });
        billID.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Bill, String> billDate = new JFXTreeTableColumn<>("Date");
        billDate.setPrefWidth(275);
        billDate.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Bill, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Bill, String> param) {
                return param.getValue().getValue().dateProperty();
            }
        });
        billDate.setStyle("-fx-alignment: center");

        // load data from database
        listBill = presenter.loadBill();

        // allow multiple select
        treeviewBill.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Create tree
        final TreeItem<Bill> root = new RecursiveTreeItem<Bill>(listBill, RecursiveTreeObject::getChildren);
        treeviewBill.getColumns().setAll(billID, billDate);
        treeviewBill.setRoot(root);
        treeviewBill.setShowRoot(false);

        treeviewBill.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            listBillInfo= presenter.loadBillInfo(treeviewBill.getSelectionModel().getSelectedItem().getValue());
            final TreeItem<BillInfo> rootBillInfo = new RecursiveTreeItem<BillInfo>(listBillInfo, RecursiveTreeObject::getChildren);
            treeviewBillInfo.setRoot(rootBillInfo);
        });
    }

    void initBillInfoTable() {
        JFXTreeTableColumn<BillInfo, String> billInfoName = new JFXTreeTableColumn<>("Name");
        billInfoName.setPrefWidth(305);
        billInfoName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<BillInfo, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<BillInfo, String> param) {
                return param.getValue().getValue().nameProperty();
            }
        });
        billInfoName.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<BillInfo, String> billInfoPrice = new JFXTreeTableColumn<>("Price");
        billInfoPrice.setPrefWidth(125);
        billInfoPrice.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<BillInfo, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<BillInfo, String> param) {
                return param.getValue().getValue().priceProperty();
            }
        });
        billInfoPrice.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<BillInfo, String> billInfoCount = new JFXTreeTableColumn<>("Count");
        billInfoCount.setPrefWidth(125);
        billInfoCount.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<BillInfo, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<BillInfo, String> param) {
                return param.getValue().getValue().countProperty();
            }
        });
        billInfoName.setStyle("-fx-alignment: center");

        // load data from database
        // allow multiple select

        // Create tree

        treeviewBillInfo.getColumns().setAll(billInfoName, billInfoPrice, billInfoCount);

        treeviewBillInfo.setShowRoot(false);

    }


    @FXML
    private void deleteRowAccount() {
        Account currentSelected = treeviewAccount.getSelectionModel().selectedItemProperty().get().getValue();
        presenter.deleteAccountInDatabase(currentSelected);
        listAccount.remove(currentSelected);
        final IntegerProperty currCountProp = treeviewAccount.currentItemsCountProperty();
        currCountProp.set(currCountProp.get() - 1);

    }

    @FXML
    private void deleteRowDrink(){
        Drink currentSelected = treeviewDrink.getSelectionModel().selectedItemProperty().get().getValue();
        presenter.deleteDrinkInDatabase(currentSelected);
        listAccount.remove(currentSelected);
        final IntegerProperty currCountProp = treeviewDrink.currentItemsCountProperty();
        currCountProp.set(currCountProp.get() - 1);

    }


    public void addDataToTable(Account account) {
        listAccount.add(account);
    }

    // Search bar logic
    private ChangeListener<String> setupSearchFieldAccount(final JFXTreeTableView<Account> treeTableView) {
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

    // Search bar logic
    private ChangeListener<String> setupSearchFieldDrink(final JFXTreeTableView<Drink> treeTableView) {
        return (o, oldVal, newVal) ->
                treeTableView.setPredicate(personProp -> {
                    final Drink drink = personProp.getValue();
                    return drink.getName().contains(newVal)
                            || drink.getCategoryName().contains(newVal);
                });
    }

    // Search bar logic
    private ChangeListener<String> setupSearchFieldBill(final JFXTreeTableView<Bill> treeTableView) {
        return (o, oldVal, newVal) ->
                treeTableView.setPredicate(personProp -> {
                    final Bill bill = personProp.getValue();
                    return bill.getDate().contains(newVal);
                });
    }
}
