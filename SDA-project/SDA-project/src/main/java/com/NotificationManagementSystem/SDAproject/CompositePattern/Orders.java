package com.NotificationManagementSystem.SDAproject.CompositePattern;

import com.NotificationManagementSystem.SDAproject.*;
import com.NotificationManagementSystem.SDAproject.CommandPattern.OrderShippingCancellationCommand;

import java.util.ArrayList;

public class Orders implements Composite, OrderType {
    private String orderID;
    private OrderType orderType;
    private Location destination;
    private ArrayList<Composite> products;
    private ArrayList<OrderType> orders;

    private PlaceHolder placeHolder;
    private ArrayList<OrderShippingCancellationCommand> commands;
    public DataBase data;

    public Orders(String orderID, Location destination, PlaceHolder placeHolder) {
        this.orderID = orderID;
        this.destination = destination;
        this.products = new ArrayList<>();
        this.placeHolder = placeHolder;
        this.commands = new ArrayList<>();
    }

    @Override
    public void getChild(int i) {
        if (i >= 0 && i < products.size()) {
            Composite child = products.get(i);
            // Perform operations with the child if needed
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

    public void cancelShipping() {
        System.out.println("Shipping for order " + orderID + " canceled.");
    }

    @Override
    public void createOrder(Account account, OrderType order) {
        // Adding a product to the order
        account.addOrder(order,data);
        System.out.println("Order created for customer " + account.getName());
    }

    @Override
    public boolean modifyOrder(Account account, OrderType order) {
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
    public boolean cancelOrder(Account account, OrderType order) {
        // Canceling the order
        if (account.getOrders().contains(order)) {
            account.removeOrder(order,data);
            System.out.println("Order canceled for customer " + account.getName());
            return true;
        } else {
            System.out.println("Order not found");
            return false;
        }
    }
/// complete this method
    @Override
    public boolean cancelShipping(Account account, OrderType order) {
        if(order.cancelOrder(account,order)){
            return true;
        }
        return false;
    }

    @Override
    public ArrayList getProductName(){
        return products;
    }

    @Override
    public void setStatus(String newStatus) {
        // Setting the status of the order
        System.out.println("Order status updated to: " + newStatus);
    }
    // Invoker of the Command Pattern
}
