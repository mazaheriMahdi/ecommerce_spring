package com.ui.ac.shop.ir.shop.model.Product;

import controller.Exeptions.ObjectDoesNotExist;
import controller.ProductController;
import model.comment.Comment;

import java.util.ArrayList;

public abstract class BaseProduct {
    private static int lastId;

    static {
        lastId = 0;
    }

    private final int id;
    private int count;
    private String name;
    private int price;
    private ProductCondition productCondition;
    private double averagePoint;
    private Category category;
    private ArrayList<Comment> comments;


    public BaseProduct(String name, int price, ProductCondition productCondition, Category category, int count) {
        lastId++;
        id = lastId;
        this.name = name;
        this.price = price;
        this.productCondition = productCondition;
        this.category = category;
        this.count = count;
    }

//    public BaseProduct(String name, int price, ProductCondition productCondition, Category category) {
//        this(name , price , productCondition , category , 0);
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductCondition getProductCondition() {
        return productCondition;
    }

    public void setProductCondition(ProductCondition productCondition) {
        this.productCondition = productCondition;
    }

    public double getAveragePoint() {
        return averagePoint;
    }

    public void setAveragePoint(double averagePoint) {
        this.averagePoint = averagePoint;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void reduceCount(int amount) {
        this.count -= amount;
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder("⭐️");
        try {
            for (int i = 0; i < ProductController.getPoint(getId()); i++) {
                temp.append("⭐️");
            }
            return "🆔= " + getId() + "\n" +
                    "name=" + name + "\n" +
                    "price=" + price + "＄\n" +
                    "productCondition=" + productCondition + "\n" +
                    "category=" + category + "\n" +
                    temp + "\n";

        } catch (ObjectDoesNotExist e) {
            return e.getMessage();
        }
    }
}
