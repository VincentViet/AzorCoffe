package com.azor;

import com.azor.models.Account;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AzorCoffee extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private double xOffset = 0;
    private double yOffset = 0;

    public static Map<String, FXMLLoader> fxmlMap;
    public static Map<String, Scene> sceneMap;
    public static Stage     primaryStage;

    public static Account currentAuth;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        fxmlMap = new HashMap<>();
        sceneMap = new HashMap<>();

        try {
            fxmlMap.put("login", new FXMLLoader(getClass().getResource("login/.fxml")));
            fxmlMap.put("manage", new FXMLLoader(getClass().getResource("manage/.fxml")));

            for (Map.Entry<String, FXMLLoader> fxml:
                 fxmlMap.entrySet()) {
                Parent root = fxml.getValue().load();
                if (fxml.getKey().equals("login")){
                    root.setOnKeyPressed(event -> {
                        if (event.getCode() == KeyCode.ESCAPE)
                            Platform.exit();
                    });
                }

                root.setOnMousePressed(event -> {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                });

                root.setOnMouseReleased(event -> primaryStage.setOpacity(1.0));

                root.setOnMouseDragged(event -> {
                    primaryStage.setX(event.getScreenX() - xOffset);
                    primaryStage.setY(event.getScreenY() - yOffset);
                    primaryStage.setOpacity(0.7);
                });

                sceneMap.put(fxml.getKey(), new Scene(root));
            }

            primaryStage.setScene(sceneMap.get("login"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
