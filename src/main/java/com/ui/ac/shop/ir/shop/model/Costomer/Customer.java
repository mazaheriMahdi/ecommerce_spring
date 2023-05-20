package com.ui.ac.shop.ir.shop.model.Costomer;

import model.Invoice.Invoice;
import model.User.AccountRole;
import model.User.User;

import java.util.ArrayList;

public class Customer extends User {
    private long phone;
    private ArrayList<Integer> cart;
    private ArrayList<Integer> history;
    private float credit;
    private String address;

    public Customer(String email, String userName, String password, String name, long phone, String address) {
        super(email, userName, name, AccountRole.Customer, password);
        this.phone = phone;
        this.address = address;
        this.cart = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public ArrayList<Integer> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Integer> cart) {
        this.cart = cart;
    }

    public ArrayList<Integer> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Integer> history) {
        this.history = history;
    }

    public void addToHistory(Invoice invoice) {
        history.add(invoice.getId());
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {

        return super.toString() +
                "phone=" + phone + "\n" +
                "cart=" + cart + "\n" +
                "history=" + history + "\n" +
                "credit=" + credit + "\n" +
                "address='" + address + "\n"
                ;
    }
}
