package com.azor.utils;

import java.sql.*;
import java.util.Objects;

public class Database {
    private static Database ourInstance;

    static {
        try {
            ourInstance = new Database();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connection;

    public static Database getInstance() {
        return ourInstance;
    }

    private Database() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        String sqlitePath = "jdbc:sqlite:" + Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(""))
                .getPath() + ".db";
        connection = DriverManager.getConnection(sqlitePath);
    }
    
    public Connection getConnection(){
        return connection;
    }
}
