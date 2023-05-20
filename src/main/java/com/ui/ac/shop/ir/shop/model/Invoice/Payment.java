package com.ui.ac.shop.ir.shop.model.Invoice;

import controller.CustomerController;
import model.Condition.Condition;
import model.Costomer.Customer;

public class Payment {
    private final String cardNumber;
    private final String CVV2;
    private final String password;
    private final float amount;
    private final Customer customer;
    private Condition condition;

    public Payment(String cardNumber, String CVV2, String password, float amount) {
        this.cardNumber = cardNumber;
        this.CVV2 = CVV2;
        this.password = password;
        this.amount = amount;
        this.customer = CustomerController.getCurrentCustomer();
        this.condition = Condition.AWAITING_CONFORMATION;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCVV2() {
        return CVV2;
    }

    public String getPassword() {
        return password;
    }

    public float getAmount() {
        return amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "cardNumber='" + cardNumber + "\n" +
                "CVV2='" + CVV2 + '\n' +
                "password='" + password + '\n' +
                "amount=" + amount + "\n" +
                "customer=" + customer.getUserName() + '\n' +
                "condition=" + condition + '\n';
    }
}
