package com.ui.ac.shop.ir.shop.model.comment;

import model.Condition.Condition;
import model.Costomer.Customer;

public class Comment {
    private Customer customer;
    private int productId;
    private String text;
    private Condition condition;
    private boolean isBuyer;

    public Comment(Customer customer, int productId, String text, boolean isBuyer) {
        this.customer = customer;
        this.productId = productId;
        this.text = text;
        this.isBuyer = isBuyer;
        this.condition = Condition.AWAITING_CONFORMATION;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public boolean isBuyer() {
        return isBuyer;
    }

    public void setBuyer(boolean buyer) {
        isBuyer = buyer;
    }

    @Override
    public String toString() {
        return "customer=" + customer.getUserName() + "\n" +
                "text=" + text + "\n" +
                "isBuyer=" + isBuyer;
    }
}

