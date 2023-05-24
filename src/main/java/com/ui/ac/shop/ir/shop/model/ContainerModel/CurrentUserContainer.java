package com.ui.ac.shop.ir.shop.model.ContainerModel;


import com.ui.ac.shop.ir.shop.model.User;

public class CurrentUserContainer {
    private static User user;
    public static void setUser(User user) {
        CurrentUserContainer.user = user;
    }
    public static User getUser() {
        return user;
    }
}
