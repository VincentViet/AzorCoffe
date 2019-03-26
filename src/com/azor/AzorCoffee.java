package com.azor;

import com.azor.utils.ConfigsReader;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Properties;

public class AzorCoffee extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryState) throws Exception {
        Properties properties = ConfigsReader.getInstance().load("dbConfig.properties");
        System.out.println(properties.getProperty("dbname"));
        System.out.println(properties.getProperty("user"));
        System.out.println(properties.getProperty("password"));
        System.exit(0);
    }
}
