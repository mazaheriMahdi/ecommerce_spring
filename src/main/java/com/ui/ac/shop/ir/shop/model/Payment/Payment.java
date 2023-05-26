package com.ui.ac.shop.ir.shop.model.Payment;

import com.ui.ac.shop.ir.shop.model.Enums.Status;
import com.ui.ac.shop.ir.shop.model.User.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private String cvv2;

    private double amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate localDate = LocalDate.now();
    @ManyToOne
    private Customer customer;

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.PENDING;

    public Payment(String cardNumber, String cvv2, double amount, Customer customer) {
        this.cardNumber = cardNumber;
        this.cvv2 = cvv2;
        this.amount = amount;
        this.customer = customer;
    }
}
