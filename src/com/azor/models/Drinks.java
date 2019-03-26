package com.azor.models;

public class Drinks {
    private int     id;
    private String  name;
    private int     price;
    private int     category_id;

    public Drinks() {
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

    public Drinks(int id, String name, int price, int category_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category_id = category_id;
    }
}
