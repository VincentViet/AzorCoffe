package com.azor.manage.homeContent;

import com.azor.models.BillInfo;
import com.azor.models.Category;
import com.azor.models.Drink;
import com.azor.models.Table;
import com.azor.utils.Database;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class View implements Initializable {

    @FXML
    private JFXListView<Table> tables;

    @FXML
    private JFXTreeTableView<BillInfo> billInfo;

    @FXML
    private JFXComboBox<Category> categories;

    @FXML
    private JFXComboBox<Drink> drinks;

    @FXML
    private JFXComboBox<Integer> counts;

    @FXML
    private JFXButton addButton;

    @FXML
    private Label total;

    public View(){}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //initialize table list
        showTableInfo();

        // initialize bill info
        JFXTreeTableColumn<BillInfo, String> name = new JFXTreeTableColumn<>("Name");
        name.setCellValueFactory(param -> param.getValue().getValue().nameProperty());
        name.setPrefWidth(500);
        name.setResizable(false);

        JFXTreeTableColumn<BillInfo, String> count = new JFXTreeTableColumn<>("Count");
        count.setCellValueFactory(param -> param.getValue().getValue().countProperty());
        count.setResizable(false);
        count.setPrefWidth(200);
        count.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<BillInfo, String> price = new JFXTreeTableColumn<>("Price");
        price.setCellValueFactory(param -> param.getValue().getValue().priceProperty());
        price.setResizable(false);
        price.setPrefWidth(204);
        price.setStyle("-fx-alignment: center");


        billInfo.getColumns().add(name);
        billInfo.getColumns().add(count);
        billInfo.getColumns().add(price);
        billInfo.setShowRoot(false);
        billInfo.setPlaceholder(new Label("This table is blank"));

        ContextMenu billInfoContextMenu = new ContextMenu();
        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(event -> {
            int index = billInfo.getSelectionModel().getSelectedIndex();
            billInfo.getRoot().getChildren().remove(index);
            total.setText("Total: $" + calcTotal());

            //update db
            BillInfo item = billInfo.getSelectionModel().getSelectedItem().getValue();
            System.out.println(item);
            String query = "DELETE FROM BillInfo WHERE main.BillInfo.id = ?";
            PreparedStatement statement = null;
            try{
                statement = Database.getInstance().getConnection().prepareStatement(query);
                statement.setInt(1, item.getId());
//                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    assert statement != null;
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        billInfoContextMenu.getItems().add(deleteItem);
        billInfo.setContextMenu(billInfoContextMenu);

        tables.getSelectionModel().selectedIndexProperty()
                .addListener((observable, oldValue, newValue) -> showBillInfo(newValue.intValue() + 1));
        tables.getSelectionModel().select(0);

        // initialize count combobox
        for (int i = 0; i <= 100; i++) {
            counts.getItems().add(i);
        }

        // initialize category combobox
        showCategories();
        categories.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showDrinks(newValue.getId()));

        //initialize add button
        addButton.setOnAction(event -> {
            Drink drink = drinks.getValue();
            Integer sl = counts.getValue();

            if (drink != null && sl != null)
            {
                billInfo.getRoot()
                        .getChildren()
                        .add(new TreeItem<>(
                                new BillInfo(drink.getName(), drink.getPrice(), sl.toString())));

                String query = "INSERT INTO BillInfo(name, price, count, billID) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = null;
                try {
                    statement = Database.getInstance().getConnection().prepareStatement(query);
                    statement.setString(1, drink.getName());
                    statement.setFloat(2, Float.parseFloat(drink.getPrice()));
                    statement.setInt(3, sl);
                    statement.setInt(4, tables.getSelectionModel().getSelectedIndex() + 1);
                    statement.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        assert statement != null;
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            total.setText("Total: $" + calcTotal());
        });
    }

    /** <b>Show bill information of n<sup>th</sup> table</b>
    * @param index index of table
    * */
    private void showBillInfo(int index){
        String billQuery = "SELECT * FROM BillInfo WHERE billID = ?";
        ResultSet billResult = null;

//        boolean isBlank = true;

        PreparedStatement statement = null;
        ObservableList<BillInfo> infos = FXCollections.observableArrayList();
        try {
            statement = Database.getInstance().getConnection().prepareStatement(billQuery);
            statement.setInt(1, index);
            billResult = statement.executeQuery();

            while (billResult.next()){
                BillInfo info = new BillInfo(billResult);
                infos.add(info);

//                isBlank = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert statement != null;
                statement.close();
                assert billResult != null;
                billResult.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        final TreeItem<BillInfo> root = new RecursiveTreeItem<>(infos, RecursiveTreeObject::getChildren);
        billInfo.setRoot(root);

        total.setText("Total: $" + calcTotal());

//        return isBlank;
    }

    private void showTableInfo(){
        String query = "SELECT * FROM CoffeeTable";
        ResultSet tableResult = null;
        PreparedStatement statement = null;
        try {
            statement = Database.getInstance().getConnection().prepareStatement(query);
            tableResult = statement.executeQuery();

            while (tableResult.next())
                tables.getItems().add(new Table(tableResult));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert statement != null;
                statement.close();
                assert tableResult != null;
                tableResult.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void showCategories(){
        String query = "SELECT * FROM Categories";
        ResultSet set = null;
        PreparedStatement statement = null;
        try{
            statement = Database.getInstance().getConnection().prepareStatement(query);
            set = statement.executeQuery();

            while (set.next())
                categories.getItems().add(new Category(set));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert statement != null;
                statement.close();
                assert set != null;
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void showDrinks(int index){
        String query = "SELECT * FROM FoodAndDrink WHERE categoryID = ?";
        ResultSet set = null;
        PreparedStatement statement = null;
        drinks.getItems().clear();
        try {
            statement = Database.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, index);
            set = statement.executeQuery();

            while (set.next())
                drinks.getItems().add(new Drink(set));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert statement != null;
                statement.close();
                assert set != null;
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private float calcTotal(){
        float total = 0.0f;

        for (TreeItem<BillInfo> info :
                billInfo.getRoot().getChildren()) {
            total += Float.parseFloat(info.getValue().getPrice())
                    * Float.parseFloat(info.getValue().getCount());

        }
        return total;
    }
}
