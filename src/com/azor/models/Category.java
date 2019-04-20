package com.azor.models;

public class Category {
    private int category_id;
    private String name;

    public Category(){}

    public Category(int category_id, String name){
        this.category_id=category_id;
        this.name= name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getName() {
        return name;
    }
}


