package com.ui.ac.shop.ir.shop.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class ProductProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
    @JoinColumn(name = "propertiesKey_id")
    PropertiesKey propertiesKey;

    private String value;

    public ProductProperty() {
    }

    public ProductProperty(Product product, PropertiesKey propertiesKey, String value) {
        this.product = product;
        this.propertiesKey = propertiesKey;
        this.value = value;
    }

    public ProductProperty(Long id, Product product, PropertiesKey propertiesKey, String value) {
        this.id = id;
        this.product = product;
        this.propertiesKey = propertiesKey;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PropertiesKey getPropertiesKey() {
        return propertiesKey;
    }

    public void setPropertiesKey(PropertiesKey propertiesKey) {
        this.propertiesKey = propertiesKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
