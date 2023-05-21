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


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
