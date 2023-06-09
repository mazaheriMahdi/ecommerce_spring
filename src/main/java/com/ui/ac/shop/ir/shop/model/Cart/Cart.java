package com.ui.ac.shop.ir.shop.model.Cart;


import com.ui.ac.shop.ir.shop.model.Discount;
import com.ui.ac.shop.ir.shop.model.User.Customer;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Cart {
    @Id
    private UUID id = UUID.randomUUID();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate place_date = LocalDate.now();

    @OneToOne(cascade = CascadeType.REMOVE)
    private Customer customer;


    @Nullable
    @ManyToOne
    private Discount discount;
    public Cart(Customer customer) {
        this.customer = customer;
    }
}
