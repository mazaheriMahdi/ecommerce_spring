package com.ui.ac.shop.ir.shop.model.Product.Food;

import model.Product.BaseProduct;
import model.Product.Category;
import model.Product.ProductCondition;

public class Food extends BaseProduct {
    private String expireDate;
    private String manufactureDate;

    public Food(String name, int price, ProductCondition productCondition, String expireDate, String manufactureDate, int count) {
        super(name, price, productCondition, Category.FOOD, count);
        this.expireDate = expireDate;
        this.manufactureDate = manufactureDate;
    }


    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    @Override
    public String toString() {
        return super.toString() +
                "expireDate=" + expireDate + "\n" +
                "manufactureDate=" + manufactureDate + "\n";
    }
}
