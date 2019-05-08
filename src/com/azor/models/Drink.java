package com.azor.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Drink {
    private String name;
    private String price;

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public Drink(ResultSet set){
        try {
            name = set.getString("name");
            price = set.getString("price");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
