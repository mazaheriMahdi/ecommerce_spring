package com.ui.ac.shop.ir.shop.model.Admin;

import model.User.AccountRole;
import model.User.User;

import java.util.ArrayList;

public class Admin extends User {
    private static int lastId;

    static {
        lastId = 0;
    }

    private final int id;
    private long phone;
    private String address;
    private ArrayList<Integer> products;
    private ArrayList<Integer> applications;


    public Admin(String userName, String name, String password, long phone, String address) {
        super(userName, name, password, AccountRole.Admin, password);
        this.phone = phone;
        this.address = address;
        lastId++;
        this.id = lastId;
        this.products = new ArrayList<>();
        this.applications = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Integer> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Integer> products) {
        this.products = products;
    }

    public ArrayList<Integer> getApplications() {
        return applications;
    }

    public void setApplications(ArrayList<Integer> applications) {
        this.applications = applications;
    }
}
