package com.ui.ac.shop.ir.shop.model.Product.Digital.Storage;

import model.Product.ProductCondition;

public class USB extends BaseStorage{
    private double version;

    public USB(String name, int price, ProductCondition productCondition, int weight, int height, int width, int length, int capacity, double version,  int count) {
        super(name, price, productCondition, weight, height, width, length, capacity , count);
        this.version = version;
    }


    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return super.toString() +
                "version=" + version + "\n";
    }
}
