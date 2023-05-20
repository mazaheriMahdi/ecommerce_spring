package com.ui.ac.shop.ir.shop.model.Product.Stationary;

import model.Product.ProductCondition;

public class Pencil extends BaseStationery {
    private PencilType pencilType;

    public Pencil(String name, int price, ProductCondition productCondition, String producerCountry, PencilType pencilType, int count) {
        super(name, price, productCondition, producerCountry, count);
        this.pencilType = pencilType;
    }


    public PencilType getPencilType() {
        return pencilType;
    }

    public void setPencilType(PencilType pencilType) {
        this.pencilType = pencilType;
    }

    @Override
    public String toString() {
        return super.toString() +
                "pencilType=" + pencilType + "\n";
    }
}
