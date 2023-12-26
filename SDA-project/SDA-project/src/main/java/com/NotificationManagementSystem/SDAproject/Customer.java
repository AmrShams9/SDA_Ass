package com.NotificationManagementSystem.SDAproject;

import com.NotificationManagementSystem.SDAproject.CompositePattern.Orders;
import com.NotificationManagementSystem.SDAproject.TemplatePattern.EmailService;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private double balance;
    private List<Orders> orders;
    EmailService email;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.orders = new ArrayList<>();
    }

    public String getName()
    {

        return name;
    }

    public double getBalance() {

        return balance;
    }

    public List<Orders> getOrders() {

        return orders;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void placeOrder(Orders order) {

        orders.add(order);
    }

    public void cancelOrder(Orders order) {
        orders.remove(order);

    }


    public EmailService getEmail() {
        return email;
    }
}

