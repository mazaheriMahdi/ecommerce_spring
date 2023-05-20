package com.ui.ac.shop.ir.shop.model.Product.Vehicle;

import model.Product.ProductCondition;

public class Car extends BaseVehicle {
    private int engineCapacity;
    private boolean isAutomatic;

    public Car(String name, int price, ProductCondition productCondition, String producerCountry, int engineCapacity, boolean isAutomatic, int count) {
        super(name, price, productCondition, producerCountry, count);
        this.engineCapacity = engineCapacity;
        this.isAutomatic = isAutomatic;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public boolean isAutomatic() {
        return isAutomatic;
    }

    public void setAutomatic(boolean automatic) {
        isAutomatic = automatic;
    }

    @Override
    public String toString() {
        return super.toString() +
                "engineCapacity=" + engineCapacity + "\n" +
                "isAutomatic=" + isAutomatic + "\n"
                ;
    }
}
