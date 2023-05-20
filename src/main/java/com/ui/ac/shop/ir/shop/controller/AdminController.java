package com.ui.ac.shop.ir.shop.controller;

import controller.Exeptions.ObjectDoesNotExist;
import model.Admin.Admin;
import model.Application.Application;
import model.Condition.Condition;
import model.Costomer.Customer;
import model.Invoice.Payment;
import model.Point.Point;
import model.comment.Comment;

import java.util.ArrayList;
import java.util.Objects;

public class AdminController {
    private static final ArrayList<Admin> admins;

    static {
        admins = new ArrayList<>();
    }


    public static void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public static ArrayList<Application> getApplications() {
        return ApplicationController.getApplications();
    }


    public static void acceptApplication(int applicationId) throws ObjectDoesNotExist {
        Application application = ApplicationController.getById(applicationId);
        application.setCondition(Condition.ACCEPTED);
        if (application.getObject() instanceof Point) {
            ((Point) application.getObject()).setCondition(Condition.ACCEPTED);
        } else if (application.getObject() instanceof Comment) {
            ((Comment) application.getObject()).setCondition(Condition.ACCEPTED);
        } else if (application.getObject() instanceof Payment payment) {
            payment.setCondition(Condition.ACCEPTED);
            payment.getCustomer().setCredit(payment.getCustomer().getCredit() + payment.getAmount());
        } else if (application.getObject() instanceof Customer) {
            CustomerController.addCustomer((Customer) application.getObject());
        }
    }

    public static void rejectApplication(int applicationId) throws ObjectDoesNotExist {
        Application application = ApplicationController.getById(applicationId);
        application.setCondition(Condition.REJECTED);
        if (application.getObject() instanceof Point) {
            ((Point) application.getObject()).setCondition(Condition.REJECTED);
        } else if (application.getObject() instanceof Comment) {
            ((Comment) application.getObject()).setCondition(Condition.REJECTED);
        } else if (application.getObject() instanceof Payment) {
            ((Payment) application.getObject()).setCondition(Condition.REJECTED);
        }
    }

    public static Admin getByUserName(String username) throws ObjectDoesNotExist {
        for (Admin admin : admins) {
            if (Objects.equals(admin.getUserName(), username)) {
                return admin;
            }
        }
        throw new ObjectDoesNotExist("Admin");
    }

    public static ArrayList<Customer> getUserList() {
        return CustomerController.getCustomers();
    }


}
