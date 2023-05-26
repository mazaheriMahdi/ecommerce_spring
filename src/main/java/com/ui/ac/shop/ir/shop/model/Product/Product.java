package com.ui.ac.shop.ir.shop.model.Product;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    private String image;
    private String name;
    private int count;
    private int price;


    private double averagePoint;






    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }




    public Product() {
    }

    public Product(Long id, String name, int count, int price, double averagePoint, Category category) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.averagePoint = averagePoint;

        this.category = category;
    }

    public Product(String name, int count, int price, double averagePoint, Category category) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.averagePoint = averagePoint;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getAveragePoint() {
        return averagePoint;
    }

    public void setAveragePoint(double averagePoint) {
        this.averagePoint = averagePoint;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
