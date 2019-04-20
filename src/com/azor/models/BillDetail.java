package com.azor.models;

public class BillDetail {
    private int tablefood_id;
    private String name;
    private int status;

    BillDetail(){}

    BillDetail(int tablefood_id, String name, int status){
        this.tablefood_id= tablefood_id;
        this.name= name;
        this.status= status;
    }

    public int getTablefood_id() {
        return tablefood_id;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }
}