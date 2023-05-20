package com.ui.ac.shop.ir.shop.controller;

import controller.Exeptions.ObjectDoesNotExist;
import model.Condition.Condition;
import model.Point.Point;
import model.Product.BaseProduct;

import java.util.ArrayList;

public class ProductController {
    private static final ArrayList<Object> products;
    private static Object currentProduct;

    static {
        products = new ArrayList<>();
    }

    public static Object getCurrentProduct() {
        return currentProduct;
    }

    public static void setCurrentProduct(Object currentProduct) {
        ProductController.currentProduct = currentProduct;
    }

    public static ArrayList<Object> getProducts() {
        return products;
    }


    public static void addProduct(Object object) {
        products.add(object);
    }

    public static void removeProduct(Object object) {
        products.remove(object);
    }

    public static void changeProperties(Object object, Object newObject) {
        int index = products.indexOf(object);
        products.set(index, newObject);
    }

    public static float getPoint(int id) throws ObjectDoesNotExist {
        float pointNumber = 0;
        int number = 0;
        for (Point point : PointController.getPoints()) {

            if (point.getProductId() == id && point.getCondition().equals(Condition.ACCEPTED)) {
                number++;
                pointNumber += point.getPoint();
            }
        }
        return pointNumber / number;
    }

    public static Object getProductById(int id) throws ObjectDoesNotExist {
        for (Object obj : products) {
            if (((BaseProduct) obj).getId() == id) {
                return obj;
            }
        }
        throw new ObjectDoesNotExist("product");
    }


}
