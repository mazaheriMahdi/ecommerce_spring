package com.ui.ac.shop.ir.shop.controller;

import model.Invoice.Payment;

import java.util.ArrayList;

public class PaymentController {
    private static final ArrayList<Payment> payments;

    static {
        payments = new ArrayList<>();
    }

    public static Payment addPayment(Payment payment) {
        payments.add(payment);
        return payment;
    }

    public static ArrayList<Payment> getPayments() {
        return payments;
    }

}
