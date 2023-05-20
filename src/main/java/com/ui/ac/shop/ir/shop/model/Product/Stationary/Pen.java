package com.ui.ac.shop.ir.shop.model.Product.Stationary;

import model.Product.ProductCondition;

public class Pen extends BaseStationery {
    private String color;


    public Pen(String name, int price, ProductCondition productCondition, String producerCountry, String color, int count) {
        super(name, price, productCondition, producerCountry, count);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return super.toString() +
                "color=" + color + "\n";
    }
}
