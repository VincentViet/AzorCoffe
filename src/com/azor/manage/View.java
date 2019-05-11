package com.azor.manage;

import com.azor.models.Account;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class View implements IView, Initializable {

    @FXML
    private BorderPane borderPane;

    private List<Parent> contents;

    private Presenter presenter;
    public View(){
        presenter = new Presenter(this);
    }

    private FXMLLoader accountFXML;

    private FXMLLoader manageFXML;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contents = new ArrayList<>();
        try{
            Parent sidebar = FXMLLoader.load(getClass().getResource("sidebar/.fxml"));
            Parent titlebar = FXMLLoader.load(getClass().getResource("titlebar/.fxml"));

            accountFXML = new FXMLLoader(getClass().getResource("accountContent/.fxml"));
            manageFXML = new FXMLLoader(getClass().getResource("manageContent/.fxml"));

            contents.add(FXMLLoader.load(getClass().getResource("homeContent/.fxml")));
            contents.add(accountFXML.load());
            contents.add(manageFXML.load());
            contents.add(FXMLLoader.load(getClass().getResource("chartContent/.fxml")));

            borderPane.setTop(titlebar);
            borderPane.setLeft(sidebar);
        } catch (IOException e) {
            e.printStackTrace();
        }

        borderPane.setCenter(contents.get(0));
    }

    public void changeContent(int index){
        borderPane.setCenter(contents.get(index));
    }

    public void loadCurrentAuthInformation(){
        com.azor.manage.accountContent.View view = accountFXML.getController();
        view.loadAuthInformation();
    }

    public void addCurrentData(Account account){
        com.azor.manage.manageContent.View view = manageFXML.getController();
        view.addData(account);
    }
}
