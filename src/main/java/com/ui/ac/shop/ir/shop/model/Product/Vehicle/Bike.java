package com.ui.ac.shop.ir.shop.model.Product.Vehicle;

import model.Product.ProductCondition;

public class Bike extends BaseVehicle {
    private BikeType bikeType;

    public Bike(String name, int price, ProductCondition productCondition, String companyName, BikeType bikeType, int count) {
        super(name, price, productCondition, companyName, count);
        this.bikeType = bikeType;
    }


    public BikeType getBikeType() {
        return bikeType;
    }

    public void setBikeType(BikeType bikeType) {
        this.bikeType = bikeType;
    }

    @Override
    public String toString() {
        return super.toString() +
                "bikeType=" + bikeType + "\n";

    }
}
