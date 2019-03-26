package com.azor.manager;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;


public class Scene implements IView, Initializable {
    @FXML
    private FontAwesomeIconView minimizeButton;
    @FXML
    private FontAwesomeIconView maximizeButton;
    @FXML
    private FontAwesomeIconView closeButton;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private BorderPane borderPane;

    private IPresenter presenter;

    public Scene(){presenter = new Presenter(this);}

    @FXML
    private void maximize(MouseEvent event){
        presenter.maximize();
    }

    @FXML
    private void close(MouseEvent event){
        presenter.close();
    }

    @FXML
    private void minimize(MouseEvent event) {
        presenter.minimize();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            VBox vBox = FXMLLoader.load(getClass().getResource("menu.fxml"));
            drawer.setSidePane(vBox);


            HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(hamburger);
            transition.setRate(-1);

            hamburger.setOnMouseClicked(event -> {
                transition.setRate(-transition.getRate());
                transition.play();
                if (drawer.isOpened())
                    drawer.close();
                else
                    drawer.open();
            });

            borderPane.setOnMousePressed(event -> {
                if (drawer.isOpened()) {
                    transition.setRate(-transition.getRate());
                    transition.play();
                    drawer.close();
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
