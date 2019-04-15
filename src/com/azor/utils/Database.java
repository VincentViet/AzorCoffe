package com.azor.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database {
    private static Database database;
    private Connection connection;

    private Database()
            throws SQLException, ClassNotFoundException, IOException {

        Properties properties = ConfigsReader.getInstance().load("com/azor/database_config/dbConfig.properties");
        String dbName = properties.getProperty("dbname");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/" + dbName;

        this.connection = DriverManager.getConnection(url, user, password);
    }

    public static Database getInstance()
            throws SQLException, ClassNotFoundException, IOException {

        if (database == null){
            database = new Database();
        }

        return database;
    }

    public void close() throws SQLException {
        this.connection.close();
    }

    public <T> List<T> fillData (Class<T> model_class)
            throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String query = "SELECT * FROM " + model_class.getSimpleName();
        Field[] fields = model_class.getDeclaredFields();
        for (Field field :
                fields) {
            field.setAccessible(true);
        }

        ResultSet result = this.connection.createStatement().executeQuery(query);

        List<T> list = new ArrayList<>();
        while (result.next()){
            T dto = model_class.getConstructor().newInstance();

            for (Field field: fields){
                String name = field.getName();

                try{
                    String value = result.getString(name);
                    field.set(dto, field.getType().getConstructor(String.class).newInstance(value));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            list.add(dto);
        }

        return list;
    }

    public ResultSet query(String sql) throws SQLException {
        return this.connection.createStatement().executeQuery(sql);
    }
}
