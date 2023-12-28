package com.NotificationManagementSystem.SDAproject.CommandPattern;

import com.NotificationManagementSystem.SDAproject.Account;
import com.NotificationManagementSystem.SDAproject.CompositePattern.OrderType;
import com.NotificationManagementSystem.SDAproject.CompositePattern.Orders;

import com.NotificationManagementSystem.SDAproject.Customer;
import com.NotificationManagementSystem.SDAproject.DataBase;

public class OrderPlacementCancellationCommand implements OrderCancellationCommand {
    private Account customer;
    private OrderType order;
    public DataBase data;

    public OrderPlacementCancellationCommand(Account customer, OrderType order) {
        this.customer = customer;
        this.order = order;
    }

    @Override
    public void cancelOrder(Account account, OrderType order) {
        account.removeOrder(order,data);
    }
}


