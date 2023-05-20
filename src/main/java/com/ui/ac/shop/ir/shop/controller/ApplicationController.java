package com.ui.ac.shop.ir.shop.controller;

import controller.Exeptions.ObjectDoesNotExist;
import model.Application.Application;

import java.util.ArrayList;

public class ApplicationController {
    private static final ArrayList<Application> applications;

    static {
        applications = new ArrayList<>();
    }

    public static void addApplication(Object object) {
        applications.add(new Application(CustomerController.getCurrentCustomer(), object));
    }

    public static ArrayList<Application> getApplications() {
        return applications;
    }

    public static Application getById(int id) throws ObjectDoesNotExist {
        for (Application application : applications) {
            if (application.getId() == id) {
                return application;
            }
        }
        throw new ObjectDoesNotExist("Application");
    }
}
