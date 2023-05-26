package com.ui.ac.shop.ir.shop.model.Payment;

import com.ui.ac.shop.ir.shop.model.Enums.Status;
import com.ui.ac.shop.ir.shop.model.User.Customer;
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
public class Payment {
    @Id
    private Long id;
    private String cardNumber;
    private String cvv2;
    private String expireDate;
    private double amount;
    @ManyToOne
    private Customer customer;

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.PENDING;

    public Payment(String cardNumber, String cvv2, String expireDate, double amount, Customer customer) {
        this.cardNumber = cardNumber;
        this.cvv2 = cvv2;
        this.expireDate = expireDate;
        this.amount = amount;
        this.customer = customer;
    }
}
