package com.NotificationManagementSystem.SDAproject.CompositePattern;

import com.NotificationManagementSystem.SDAproject.Customer;
import com.NotificationManagementSystem.SDAproject.Location;
import com.NotificationManagementSystem.SDAproject.PlaceHolder;

import java.util.ArrayList;

public class CompoundOrder implements Composite, OrderType {

    private String orderId;
    private ArrayList<Location> destinations;
    private ArrayList<Composite> products;
    private ArrayList<OrderType> orders;
    private ArrayList<PlaceHolder> placeHolders;
    private ArrayList<Customer> customers;

    public CompoundOrder(String orderId , ArrayList<Customer> customers) {
        this.orderId = orderId;
        this.customers = new ArrayList<>();
        this.destinations = new ArrayList<>();
        this.products = new ArrayList<>();
        this.placeHolders = new ArrayList<>();
    }

    @Override
    public void getChild(int i) {
        if (i >= 0 && i < products.size()) {
            Composite child = products.get(i);
            // You can perform operations with the child, if needed
            System.out.println("Child: " + child);
        } else {
            System.out.println("Invalid index");
        }
    }

    @Override
    public void add(Composite composite) {
        products.add(composite);
    }

    @Override
    public void delete(Composite composite) {
        products.remove(composite);
    }

    @Override
    public void createOrder(Customer account, OrderType order) {
        // Adding a product to the order
        orders.add(order);
        System.out.println("Order created for customer " + account.getName());
    }

    @Override
    public boolean modifyOrder(Customer account, OrderType order) {
        // Modifying the order details
        if (orders.contains(order)) {
            // Perform modifications
            System.out.println("Order modified for customer " + account.getName());
            return true;
        } else {
            System.out.println("Order not found");
            return false;
        }
    }

    @Override
    public boolean cancelOrder(Customer account, OrderType order) {
        // Canceling the order
        if (orders.contains(order)) {
            orders.remove(order);
            System.out.println("Order canceled for customer " + account.getName());
            return true;
        } else {
            System.out.println("Order not found");
            return false;
        }
    }

    @Override
    public ArrayList getProductName() {
        return products;
    }

    @Override
    public void setStatus(String newStatus) {
        // Setting the status of the order
        System.out.println("Order status updated to: " + newStatus);
    }
}
