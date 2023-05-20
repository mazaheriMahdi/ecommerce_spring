package com.ui.ac.shop.ir.shop.model.Point;

import model.Condition.Condition;
import model.Costomer.Customer;

public class Point {
    private Customer customer;
    private int point;
    private int productId;
    private Condition condition;

    public Point(Customer customer, int point, int productId) {
        this.customer = customer;
        this.point = point;
        this.productId = productId;
        condition = Condition.AWAITING_CONFORMATION;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
