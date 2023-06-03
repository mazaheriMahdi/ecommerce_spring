package com.ui.ac.shop.ir.shop.model;

import com.ui.ac.shop.ir.shop.model.Product.Product;
import com.ui.ac.shop.ir.shop.model.User.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;




    @OneToOne
    private Application application;

    public Review(String comment, Product product, User user, Application application) {
        this.comment = comment;
        this.product = product;
        this.user = user;
        this.application = application;
    }
}
