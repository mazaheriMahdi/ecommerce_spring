package com.ui.ac.shop.ir.shop.model;


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
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double credit=0;
    private String avatarUrl;
    @OneToOne
    private User user;
    public Customer(User user) {
        this.user = user;
    }
}
