package com.azor.models;

public class Table {
    private int id;
    private int number;
    private String status;

    public Table(){

    }

    public Table(int id, int number, String status){
        this.id=id;
        this.number=number;
        this.status=status;
    }

    public int getId(){
        return id;
    }

    public int getNumber() {
        return number;
    }

    public String getStatus() {
        return status;
    }
}
