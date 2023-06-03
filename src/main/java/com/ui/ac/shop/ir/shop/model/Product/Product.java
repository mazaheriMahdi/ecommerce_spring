package com.ui.ac.shop.ir.shop.model.Product;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String image;
    private String name;
    private int count;
    private int price;

    private double averagePoint;



    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public Product(Long id, String name, int count, int price, double averagePoint, Category category , String image) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.averagePoint = averagePoint;
        this.category = category;
        this.image = image;
    }

    public Product(String name, int count, int price, double averagePoint, Category category , String image) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.averagePoint = averagePoint;
        this.category = category;
        this.image = image;
    }





    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", averagePoint=" + averagePoint +
                ", category=" + category +
                '}';
    }
}
