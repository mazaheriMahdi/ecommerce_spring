package com.ui.ac.shop.ir.shop.model.Product.Stationary;

import model.Product.BaseProduct;
import model.Product.Category;
import model.Product.ProductCondition;

public class BaseStationery extends BaseProduct {

    private String producerCountry;

    public BaseStationery(String name, int price, ProductCondition productCondition, String producerCountry, int count) {
        super(name, price, productCondition, Category.STATIONERY, count);
        this.producerCountry = producerCountry;
    }

    public String getProducerCountry() {
        return producerCountry;
    }

    public void setProducerCountry(String producerCountry) {
        this.producerCountry = producerCountry;
    }

    @Override
    public String toString() {
        return super.toString() +
                "producerCountry='" + producerCountry + "\n";

    }
}
