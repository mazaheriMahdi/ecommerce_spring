package com.ui.ac.shop.ir.shop.controller;

import controller.Exeptions.*;
import model.Cart.CartItem;
import model.Costomer.Customer;
import model.Invoice.Invoice;
import model.Point.Point;
import model.Product.BaseProduct;
import model.comment.Comment;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerController {
    private static ArrayList<Customer> customers;
    private static Customer currentCustomer;
    private static int currentCustomerIndex;

    static {
        customers = new ArrayList<>();
    }

    //------functions---------------
    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public static void removeCustomer(Customer customer) {
        customers.remove(customer);
    }


    public static void changePersonalInfo(Customer customer) {
        customers.set(currentCustomerIndex, customer);

    }


    public static void addToCart(BaseProduct product, int quantity) throws ObjectDoesNotExist, InsufficientInventory {
        boolean isFound = false;
        if (product.getCount() >= quantity) {
            for (Integer cartItem : currentCustomer.getCart()) {
                CartItem currentItem = CartItemController.getById(cartItem);
                if (currentItem.getProductId() == product.getId()) {
                    isFound = true;
                    currentItem.setQuantity(currentItem.getQuantity() + quantity);
                }
            }
            if (!isFound) {
                CartItem cartItem = CartItemController.addCartItem(product, quantity);
                currentCustomer.getCart().add(cartItem.getId());
            }

        } else throw new InsufficientInventory();
    }


    public static Customer getByUserName(String username) throws ObjectDoesNotExist {
        for (Customer customer : customers) {
            if (Objects.equals(customer.getUserName(), username)) {
                return customer;
            }
        }
        throw new ObjectDoesNotExist("Customer");
    }

    public static ArrayList<Invoice> getHistory() throws ObjectDoesNotExist {
        ArrayList<Invoice> history = new ArrayList<>();
        for (int invoiceId : currentCustomer.getHistory()) {
            history.add(InvoiceController.getById(invoiceId));
        }
        return history;
    }

    public static void addComment(BaseProduct baseProduct, String text) throws ObjectDoesNotExist {
        Comment comment1 = new Comment(currentCustomer, baseProduct.getId(), text, isBuyer(baseProduct));
        CommentController.getComments().add(comment1);
    }

    public static void setPoint(BaseProduct baseProduct, int point) throws CustomerIsNotBuyer, ObjectDoesNotExist {
        Point newPoint = new Point(currentCustomer, baseProduct.getId(), point);
        if (isBuyer(baseProduct)) {
            PointController.getPoints().add(newPoint);
        } else {
            throw new CustomerIsNotBuyer();
        }
    }

    public static void addCredit(float amount) {
        currentCustomer.setCredit(currentCustomer.getCredit() + amount);
    }

    public static ArrayList<CartItem> cart() throws ObjectDoesNotExist {
        ArrayList<CartItem> temp = new ArrayList<>();
        for (int cartItemId : currentCustomer.getCart()) {
            temp.add(CartItemController.getById(cartItemId));
        }
        return temp;
    }


    public static boolean isBuyer(BaseProduct baseProduct) throws ObjectDoesNotExist {
        for (int invoiceId : currentCustomer.getHistory()) {
            Invoice invoice = InvoiceController.getById(invoiceId);
            for (int cartItemId : invoice.getCartItems()) {
                CartItem cartItem = CartItemController.getById(cartItemId);
                if (cartItem.getProductId() == baseProduct.getId()) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void login(Customer customer, String password) throws WrongPassWordException {
        if (Objects.equals(customer.getPassword(), password)) {
            currentCustomerIndex = customers.indexOf(customer);
            currentCustomer = customers.get(currentCustomerIndex);
        } else {
            throw new WrongPassWordException();
        }
    }


    public static void buy() throws ObjectDoesNotExist, CreditNotEnoughException {
        ArrayList<Integer> cartItems = new ArrayList<>();
        float totalAmount = 0F;
        for (CartItem cartItem : cart()) {
            cartItems.add(cartItem.getId());
            totalAmount += cartItem.getUnitPrice() * cartItem.getQuantity();
            ((BaseProduct) ProductController.getProductById(cartItem.getProductId())).reduceCount(cartItem.getQuantity());
        }

        if (currentCustomer.getCredit() >= totalAmount) {
            currentCustomer.addToHistory(InvoiceController.addInvoice(cartItems));
            currentCustomer.getCart().clear();
            currentCustomer.setCredit(currentCustomer.getCredit() - totalAmount);

        } else throw new CreditNotEnoughException();
    }


    //-----getter and setter -------
    public static ArrayList<Object> getProducts() {
        return ProductController.getProducts();
    }

    public static ArrayList<Object> getProducts(String searchPhrase) {
        String regex = ".*" + searchPhrase + ".*";
        Pattern pattern = Pattern.compile(regex);
        ArrayList<Object> temp = new ArrayList<>();
        for (Object obj : ProductController.getProducts()) {
            Matcher matcher = pattern.matcher(((BaseProduct) obj).getName());
            if (matcher.find()) {
                temp.add(obj);
            }
        }
        return temp;
    }


    public static int getCurrentCustomerIndex() {
        return currentCustomerIndex;
    }

    public static void setCurrentCustomerIndex(int currentCustomerIndex) {
        CustomerController.currentCustomerIndex = currentCustomerIndex;
    }

    public static Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public static void setCurrentCustomer(Customer currentCustomer) {
        CustomerController.currentCustomer = currentCustomer;
        currentCustomerIndex = customers.indexOf(currentCustomer);
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static void setCustomers(ArrayList<Customer> customers) {
        CustomerController.customers = customers;
    }
}
