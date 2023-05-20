package com.ui.ac.shop.ir.shop.model.Product.Digital;

import model.Product.BaseProduct;
import model.Product.Category;
import model.Product.ProductCondition;

public abstract class BaseDigital extends BaseProduct {
    private int weight;
    private int height;
    private int width;
    private int length;

    public BaseDigital(String name, int price, ProductCondition productCondition, int weight, int height, int width, int length, int count) {
        super(name, price, productCondition, Category.DIGITAL, count);
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return super.toString() +
                "weight=" + weight + "\n" +
                "height=" + height + "\n" +
                "width=" + width + "\n" +
                "length=" + length + "\n";

    }
}
