package com.azor.models;

import com.azor.login.Presenter;

public class BillInvoice {
    private String description;
    private int quantity;
    private int price;
    private int amount;
    private Presenter presenter;

    public BillInvoice() {
    }

    public BillInvoice(String description, int quantity, int price) {
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.amount = price* quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount() {
        this.amount = this.price * this.quantity;
    }
}
