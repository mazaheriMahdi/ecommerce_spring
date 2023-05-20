package com.ui.ac.shop.ir.shop.controller;

import controller.Exeptions.ObjectDoesNotExist;
import model.Invoice.Invoice;

import java.util.ArrayList;

public class InvoiceController {

    private static ArrayList<Invoice> invoices;

    static {
        invoices = new ArrayList<>();
    }

    public static Invoice addInvoice(ArrayList<Integer> cartItems) throws ObjectDoesNotExist {
        Invoice invoice = new Invoice(cartItems);
        invoices.add(invoice);
        return invoice;
    }

    public static ArrayList<Invoice> getInvoices() {
        return invoices;
    }

    public static void setInvoices(ArrayList<Invoice> invoices) {
        InvoiceController.invoices = invoices;
    }

    public static Invoice getById(int id) throws ObjectDoesNotExist {
        for (Invoice invoice : invoices) {
            if (invoice.getId() == id) {

                return invoice;
            }
        }
        throw new ObjectDoesNotExist("invoice");
    }


}
