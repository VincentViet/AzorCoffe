package com.azor.manage.manageContent;

import com.azor.AzorCoffee;
import com.azor.models.Account;
import com.azor.models.Category;
import com.azor.models.Drink;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class drawerFoodController implements Initializable {

    @FXML
    private JFXTextField tfDrinkName;

    @FXML
    private JFXTextField tfPrice;

    @FXML
    private JFXTextField tfCategoryName;

    @FXML
    private JFXComboBox<Category> comboBoxCategory;

    private ObservableList<Category> categoryList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Presenter presenter = new Presenter();
        RequiredFieldValidator validator = new RequiredFieldValidator();
        tfDrinkName.getValidators().add(validator);
        validator.setMessage("No input given");
        tfDrinkName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){
                    tfDrinkName.validate();
                }
            }
        });

        NumberValidator numberValidator = new NumberValidator();
        tfPrice.getValidators().add(numberValidator);
        numberValidator.setMessage("Only allow number");
        tfPrice.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){
                    tfPrice.validate();
                }
            }
        });

        categoryList = presenter.loadCategory();
        comboBoxCategory.setItems(categoryList);
    }

    @FXML
    private void resetTextField(){
        tfDrinkName.setText("");
        tfPrice.setText("");

    }

    @FXML
    private void addCategory(){
        Presenter presenter= new Presenter();
        presenter.createCategory(tfCategoryName.getText());
    }

    @FXML
    private void addDrink(){
        com.azor.manage.View view = AzorCoffee.fxmlMap.get("manage").getController();
        String name = tfDrinkName.getText().toString();
        String price = tfPrice.getText().toString();
        int categoryID = comboBoxCategory.getSelectionModel().getSelectedItem().getId();
        Drink drink = new Drink(name, price, categoryID);
        if ((name.length()!=0) && (price.length()!=0))
        {
            Presenter presenter = new Presenter();
            try {
                presenter.createDrink(drink);
                view.addDrink(drink);
            }catch (SQLException e){
                showAlert();
            }
        }
        else {
            showAlert();
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Missing input or input not match requirement !");
        alert.setContentText("Please check your input");
        alert.showAndWait();
    }
}
