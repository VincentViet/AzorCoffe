package com.azor.models;

public class Food {
    private int     food_id;
    private String  name;
    private int     category_id;
    private int     price;

    public Food() {
    }

    public int getId() {
        return food_id;
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

    public Food(int food_id, String name, int price, int category_id) {
        this.food_id = food_id;
        this.name = name;
        this.price = price;
        this.category_id = category_id;
    }
}
