package com.NotificationManagementSystem.SDAproject.CompositePattern;

import com.NotificationManagementSystem.SDAproject.Account;
import com.NotificationManagementSystem.SDAproject.Customer;

import java.util.ArrayList;

public interface OrderType {
    void createOrder(Account account , OrderType order);


    boolean modifyOrder(Account account , OrderType order);


    boolean cancelOrder(Account account , OrderType order);

    boolean cancelShipping(Account account , OrderType order);


    ArrayList getProductName();

    void setStatus(String newStatus);
}
