package com.ui.ac.shop.ir.shop.model;

import java.util.List;

public class ProductWithProperty {
    private Product product;
    private List<ProductProperty> productPropertyList;

    public ProductWithProperty(Product product, List<ProductProperty> productPropertyList) {
        this.product = product;
        this.productPropertyList = productPropertyList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<ProductProperty> getProductPropertyList() {
        return productPropertyList;
    }

    public void setProductPropertyList(List<ProductProperty> productPropertyList) {
        this.productPropertyList = productPropertyList;
    }
}
