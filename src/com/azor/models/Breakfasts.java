package com.azor.models;

public class Breakfasts {
    private int     id;
    private String  name;
    private int     price;
    private int     category_id;

    public Breakfasts() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public Breakfasts(int id, String name, int price, int category_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category_id = category_id;
    }
}
