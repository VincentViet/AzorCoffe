package com.azor.manage.accountContent;

import com.azor.AzorCoffee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class View implements Initializable {
    @FXML
    private Rectangle pane;

    @FXML
    private Label fullname;

    @FXML
    private Label email;

    @FXML
    private Label address;

    @FXML
    private Label phone;

    @FXML
    private Label type;

    public View(){}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(1);
        dropShadow.setOffsetY(1);
        dropShadow.setColor(Color.BLACK);

        pane.setEffect(dropShadow);
    }

    public void loadAuthInformation(){
        fullname.setText(AzorCoffee.currentAuth.getFullname());
        email.setText(AzorCoffee.currentAuth.getEmail());
        address.setText(AzorCoffee.currentAuth.getAddress());
        phone.setText(AzorCoffee.currentAuth.getTelphone());
        type.setText(AzorCoffee.currentAuth.getType() == 0 ? "Administrator" : "Normal Account");
    }
}
