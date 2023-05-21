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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public PropertiesKey(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public PropertiesKey(String name) {
        this.name = name;
    }

    public PropertiesKey() {
    }
}
