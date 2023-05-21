package com.ui.ac.shop.ir.shop.model;

import jakarta.persistence.*;

@Entity
@Table
public class ProductProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "propertiesKey_id")
    PropertiesKey propertiesKey;

    private Object value;


    public ProductProperty(Long id, PropertiesKey propertiesKey, Object value) {
        this.id = id;
        this.propertiesKey = propertiesKey;
        this.value = value;
    }

    public ProductProperty(PropertiesKey propertiesKey, Object value) {
        this.propertiesKey = propertiesKey;
        this.value = value;
    }

    public ProductProperty() {
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public PropertiesKey getPropertiesKey() {
        return propertiesKey;
    }

    public void setPropertiesKey(PropertiesKey propertiesKey) {
        this.propertiesKey = propertiesKey;
    }

}
