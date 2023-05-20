package com.ui.ac.shop.ir.shop.model.User;

import view.Color.ConsoleColors;

public abstract class User {
    private static int lastId;

    static {
        lastId = 0;
    }

    private final int id;
    private String userName;
    private String name;
    private AccountRole accountRole;
    private String password;
    private String email;

    public User(String email, String userName, String name, AccountRole accountRole, String password) {
        lastId++;
        id = lastId;
        this.email = email;
        this.name = name;
        this.accountRole = accountRole;
        this.password = password;
        this.userName = userName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountRole getAccountRole() {
        return accountRole;
    }

    public void setAccountRole(AccountRole accountRole) {
        this.accountRole = accountRole;
    }

    @Override
    public String toString() {
        return "id=" + id + "\n" +
                "userName=" + userName + '\n' +
                "name=" + name + '\n' +
                "password=" + ConsoleColors.RED + "*********" + ConsoleColors.RESET + '\n';
    }
}
