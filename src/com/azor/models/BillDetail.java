package com.azor.models;

public class BillDetail {
    private int billdetail_id;
    private int bill_id;
    private int food_id;
    private int count;

    public BillDetail() {
    }

    public BillDetail(int billdetail_id, int bill_id, int food_id, int count){
        this.billdetail_id= billdetail_id;
        this.bill_id= bill_id;
        this.food_id= food_id;
        this.count= count;
    }

    public int getBilldetail_id() {
        return billdetail_id;
    }

    public int getBill_id() {
        return bill_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public int getCount() {
        return count;
    }

    public String toString(){
        String temp=Integer.toString(billdetail_id)+" "+Integer.toString(bill_id);
        return temp;
    }
}
