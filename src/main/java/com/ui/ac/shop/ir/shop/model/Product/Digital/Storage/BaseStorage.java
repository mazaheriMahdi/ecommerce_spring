package com.ui.ac.shop.ir.shop.model.Product.Digital.Storage;

import model.Product.Digital.BaseDigital;
import model.Product.ProductCondition;

public abstract class BaseStorage extends BaseDigital {
    private int capacity;

    public BaseStorage(String name, int price, ProductCondition productCondition, int weight, int height, int width, int length, int capacity , int count) {
        super(name, price, productCondition, weight, height, width, length , count);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return super.toString() +
                "capacity=" + capacity + "\n";
    }
}
