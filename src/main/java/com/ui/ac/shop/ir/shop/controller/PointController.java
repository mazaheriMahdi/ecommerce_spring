package com.ui.ac.shop.ir.shop.controller;

import model.Point.Point;
import model.Product.BaseProduct;

import java.util.ArrayList;

public class PointController {
    private static final ArrayList<Point> points;

    static {
        points = new ArrayList<>();
    }

    public static ArrayList<Point> getPoints() {
        return points;
    }

    public static Point addPoint(int point) {
        Point point1 = new Point(CustomerController.getCurrentCustomer(), point, ((BaseProduct) ProductController.getCurrentProduct()).getId());
        points.add(point1);
        return point1;
    }

}
