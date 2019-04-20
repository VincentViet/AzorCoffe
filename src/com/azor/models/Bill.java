package com.azor.models;

import java.util.GregorianCalendar;

public class Bill {
    private int bill_id;
    private int sub_total;
    private GregorianCalendar date_check_in;
    private int tablefood_id;

    public Bill(){}

    public Bill(int bill_id, int sub_total, GregorianCalendar date_check_in, int tablefood_id){
        this.bill_id= bill_id;
        this.sub_total= sub_total;
        this.date_check_in= date_check_in;
        this.tablefood_id= tablefood_id;
    }

    public int getBill_id() {
        return bill_id;
    }

    public int getSub_total() {
        return sub_total;
    }

    public GregorianCalendar getDate_check_in() {
        return date_check_in;
    }

    public int getTablefood_id() {
        return tablefood_id;
    }
}
