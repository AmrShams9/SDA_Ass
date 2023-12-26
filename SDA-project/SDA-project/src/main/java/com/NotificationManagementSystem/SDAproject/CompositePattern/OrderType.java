package com.NotificationManagementSystem.SDAproject.CompositePattern;

import com.NotificationManagementSystem.SDAproject.Customer;

import java.util.ArrayList;

public interface OrderType {
    void createOrder(Customer account , OrderType order);


    boolean modifyOrder(Customer account , OrderType order);


    boolean cancelOrder(Customer account , OrderType order);


    ArrayList getProductName();

    void setStatus(String newStatus);
}
