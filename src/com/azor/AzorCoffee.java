package com.azor;

import com.azor.utils.Database;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.StageStyle;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


public class AzorCoffee extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private double xOffset = 0;
    private double yOffset = 0;

    public static Scene     loginScene;
    public static Scene     managerScene;
    public static Stage     primaryStage;
    public static Scene     dashboardUIBetaScene;


    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        try {
            Parent login = FXMLLoader.load(getClass().getResource("login/login.fxml"));
            login.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            login.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });
            login.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ESCAPE){
                    Platform.exit();
                }
            });
            loginScene = new Scene(login);

            Parent manager = FXMLLoader.load(getClass().getResource("manager/manager.fxml"));
            manager.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            manager.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });
            managerScene = new Scene(manager);
            managerScene.getStylesheets().add(getClass().getResource("css/tablink.css").toExternalForm());

            Parent dashboard = FXMLLoader.load(getClass().getResource("DashboardUIBeta/DashboardUIBeta.fxml"));
            dashboardUIBetaScene= new Scene(dashboard);

            primaryStage.setScene(dashboardUIBetaScene);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.centerOnScreen();
        primaryStage.show();

    }

    @Override
    public void stop() throws Exception{
        Database.getInstance().close();
    }
}
