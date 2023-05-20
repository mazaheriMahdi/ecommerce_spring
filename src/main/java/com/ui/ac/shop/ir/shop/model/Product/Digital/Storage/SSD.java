package com.ui.ac.shop.ir.shop.model.Product.Digital.Storage;

import model.Product.ProductCondition;

public class SSD extends BaseStorage {
    private int writeSpeed , readSpeed;

    public SSD(String name, int price, ProductCondition productCondition, int weight, int height, int width, int length, int capacity, int writeSpeed, int readSpeed , int count) {
        super(name, price, productCondition, weight, height, width, length, capacity  , count);
        this.writeSpeed = writeSpeed;
        this.readSpeed = readSpeed;
    }

    public int getWriteSpeed() {
        return writeSpeed;
    }

    public void setWriteSpeed(int writeSpeed) {
        this.writeSpeed = writeSpeed;
    }

    public int getReadSpeed() {
        return readSpeed;
    }

    public void setReadSpeed(int readSpeed) {
        this.readSpeed = readSpeed;
    }

    @Override
    public String toString() {
        return super.toString() +
                "writeSpeed=" + writeSpeed + "\n" +
                "readSpeed=" + readSpeed + "\n";
    }
}
