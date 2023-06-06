package com.ui.ac.shop.ir.shop.model.Cart;


import com.ui.ac.shop.ir.shop.model.Discount;
import com.ui.ac.shop.ir.shop.model.Product.Product;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private int quantity;

    @ManyToOne
    private Cart cart;

    @Transient
    private double totalPrice;


    @Nullable
    @ManyToOne
    private Discount discount;

    public double getTotalPrice() {
        if (discount == null)
            return product.getPrice() * quantity;
        return product.getPrice() * quantity * (((100 - discount.getPercent()) / 100D));
    }

    public CartItem(Product product, int quantity, Cart cart) {
        this.product = product;
        this.quantity = quantity;
        this.cart = cart;
    }
}
