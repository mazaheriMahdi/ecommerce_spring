package com.ui.ac.shop.ir.shop.model.Application;

import model.Condition.Condition;
import model.Costomer.Customer;
import view.Color.ConsoleColors;

public class Application {
    private static int lastId;

    static {
        lastId = 0;
    }

    private final int id;
    private final Object object;
    private Customer user;
    private Condition condition;

    public Application(Customer customer, Object object) {
        lastId++;
        id = lastId;
        this.user = customer;
        this.condition = Condition.AWAITING_CONFORMATION;
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public int getId() {
        return id;
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "id=" + id + "\n" +
                "user=" + user.getUserName() + "\n" +
                "object= \n" + ConsoleColors.RED + "{" + object.toString() + "\n}" + ConsoleColors.RESET +
                "condition=" + condition + "\n"
                ;
    }
}
