package com.ui.ac.shop.ir.shop.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
public class PropertiesKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
