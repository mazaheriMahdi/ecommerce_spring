package com.ui.ac.shop.ir.shop.model.Cart;

import controller.Exeptions.ObjectDoesNotExist;
import controller.ProductController;
import model.Product.BaseProduct;

public class CartItem {
    private static int lastId;

    static {
        lastId = 0;
    }

    private final int id;
    private final float unitPrice;
    private int productId;
    private int quantity;

    public CartItem(int productId, int quantity) throws ObjectDoesNotExist {
        lastId++;
        id = lastId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = ((BaseProduct) ProductController.getProductById(productId)).getPrice();
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    @Override
    public String toString() {
        String product;
        try {
            product = "[" + ProductController.getProductById(productId).toString() + "]";
        } catch (ObjectDoesNotExist e) {
            throw new RuntimeException(e);
        }
        return "product=\n" + product + "\n" +
                "quantity=" + quantity + "\n";

    }
}
