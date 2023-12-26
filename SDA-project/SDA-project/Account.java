package com.NotificationManagementSystem.SDAproject;

import com.NotificationManagementSystem.SDAproject.CompositePattern.Orders;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private int accountId;
    private String password;
    private double balance;
    private List<OrderType> orders;
    private Location location;

    public Account(int accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
        this.orders = new ArrayList<>();
    }

    public void setAccountId(int i)
    {
        accountId=i;
    }
    public int getAccountId()
    {
        return accountId;
    }
    public void setPassword(String p)
    {
        password=p;
    }

    public double getBalance()
    {
        return balance;
    }

    public void deductBalance(double amount)
    {
        balance -= amount;
    }

    public void refundBalance(double amount)
    {
        balance += amount;
    }

    public List<OrderType> getOrders() {

        return orders;
    }

    public void placeOrder(OrderType order) {

        orders.add(order);
    }

    public void cancelOrder(OrderType order) {
        orders.remove(order);

    }
}
