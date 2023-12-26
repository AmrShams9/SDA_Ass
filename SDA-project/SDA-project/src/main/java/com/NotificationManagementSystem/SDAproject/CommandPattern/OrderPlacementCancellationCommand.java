package com.NotificationManagementSystem.SDAproject.CommandPattern;

import com.NotificationManagementSystem.SDAproject.CompositePattern.Orders;
import com.NotificationManagementSystem.*;
import com.NotificationManagementSystem.SDAproject.Customer;

public class OrderPlacementCancellationCommand implements OrderCancellationCommand {
    private Customer customer;
    private Orders order;

    public OrderPlacementCancellationCommand(Customer customer, Orders order) {
        this.customer = customer;
        this.order = order;
    }

    @Override
    public void execute(Orders order) {
        customer.cancelOrder(order);
    }
}


