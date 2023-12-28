package com.NotificationManagementSystem.SDAproject;

import com.NotificationManagementSystem.SDAproject.CommandPattern.OrderCancellationCommand;
import com.NotificationManagementSystem.SDAproject.CommandPattern.OrderPlacementCancellationCommand;
import com.NotificationManagementSystem.SDAproject.CompositePattern.*;
import com.NotificationManagementSystem.SDAproject.ObserverPattern.Observer;
import com.NotificationManagementSystem.SDAproject.TemplatePattern.EmailService;

import java.util.ArrayList;
import java.util.List;

public class Account implements Observer {
    private int accountId;
    private String name;
    private String password;
    private double balance;
    private Cart cart;
    private List<OrderType> orders;
    private Location location;
    private ArrayList<OrderCancellationCommand> cancelOrderPlacement;
    public ArrayList<EmailService> accNotifications;

    public Account(int accountId, String password, double balance, Cart carts) {
        this.accountId = accountId;
        this.password = password;
        this.balance = balance;
        this.orders = new ArrayList<>();
        this.cart = carts = null;
        this.name = "User";
        this.cancelOrderPlacement = new ArrayList<>();
        this.accNotifications = new ArrayList<>();
    }

    public void setAccountId(int i) {
        accountId = i;
    }


    public int getAccountId() {
        return accountId;
    }

    public void setPassword(String p) {
        password = p;
    }

    public boolean Check(int inputAccountId, String inputPassword) {
        return accountId == inputAccountId && password.equals(inputPassword);
    }

    public double getBalance() {
        return balance;
    }

    public void deductBalance(double amount) {
        balance -= amount;
    }

    public void refundBalance(double amount) {
        balance += amount;
    }

    public List<OrderType> getOrders() {
        return orders;
    }

    public void addOrder(OrderType order, DataBase database) {
        orders.add(order);
        database.saveOrder(order);
    }

    public void removeOrder(OrderType order, DataBase database) {
        orders.remove(order);
        database.getSavedOrders().remove(order);
    }

    public Cart getCart() {
        return cart;
    }

    public void addToCart(Product product) {
        cart.addProduct(product);
    }

    public void removeFromCart(Product product) {
        cart.removeProduct(product);
    }

    public List<Product> viewCart() {
        return cart.getProducts();
    }

    public double calculateCartTotal() {
        return cart.calculateTotal();
    }

    public void PlaceSimpleOrder(OrderType simpleOrder, String orderID, Location destination, PlaceHolder placeHolder, DataBase database) {
        simpleOrder = new Orders(orderID, destination, placeHolder);
        addOrder(simpleOrder, database);
    }

    public void PlaceCompoundOrder(OrderType compoundOrder, String orderId, ArrayList<Location> destinations, ArrayList<PlaceHolder> placeHolders, DataBase database) {
        compoundOrder = new CompoundOrder(orderId, destinations, placeHolders);
        addOrder(compoundOrder, database);
    }

    public void purchase(Cart cart) {
        deductBalance(cart.calculateTotal());
        cart.clearCart();
    }

    public void CancelOrderShipping(OrderType order) {
        // Add logic for canceling order shipping
    }

    public void CancelOrderPlacement(OrderPlacementCancellationCommand command, Account account, OrderType order, DataBase database) {
        command.cancelOrder(account, order);
        database.getSavedOrders().remove(order);
    }

    public void showNotifications(DataBase database) {
        for (EmailService notification : accNotifications) {
            System.out.println(notification);
            database.saveNotification(notification);
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<EmailService> getAccNotifications() {
        return accNotifications;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(this.name +" has new Notification");
        EmailService new2 = new Notifications();
        accNotifications.add(new2);

    }
}
