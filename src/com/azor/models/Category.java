package com.azor.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Category {
    private int id;
    private String name;

    public Category(ResultSet set){
        try {
            id = set.getInt("id");
            name = set.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
