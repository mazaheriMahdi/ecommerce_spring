package com.ui.ac.shop.ir.shop.controller.Exeptions;

public class ObjectDoesNotExist extends Exception {
    public ObjectDoesNotExist(String objectType) {
        super("There is no " + objectType + " with this id");
    }
}
