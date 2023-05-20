package com.ui.ac.shop.ir.shop.model.Product.Vehicle;

import model.Product.BaseProduct;
import model.Product.Category;
import model.Product.ProductCondition;

public class BaseVehicle extends BaseProduct {
    private String companyName;

    public BaseVehicle(String name, int price, ProductCondition productCondition, String companyName, int count) {
        super(name, price, productCondition, Category.VEHICLES, count);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return super.toString() +
                "companyName=" + companyName + "\n";

    }
}
