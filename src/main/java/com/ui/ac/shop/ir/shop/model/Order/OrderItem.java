package com.ui.ac.shop.ir.shop.model.Order;


import com.ui.ac.shop.ir.shop.model.Discount;
import com.ui.ac.shop.ir.shop.model.Product.Product;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    private Product product;

    private int quantity;


    @Nullable
    @ManyToOne
    private Discount discount;


    @Transient
    private double totalPrice;

    public double getTotalPrice() {
        if (discount == null)
            return product.getPrice() * quantity;
        return product.getPrice() * quantity * (((100 - discount.getPercent()) / 100D));
    }

    public OrderItem(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderItem(Order order, Product product, int quantity, double totalPrice) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
