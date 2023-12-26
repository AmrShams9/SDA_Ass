package com.NotificationManagementSystem.SDAproject.CommandPattern;

import com.NotificationManagementSystem.SDAproject.CompositePattern.Orders;

public class OrderShippingCancellationCommand implements OrderCancellationCommand {
    private Orders order;

    public OrderShippingCancellationCommand(Orders order) {

        this.order = order;
    }

    @Override
    public void execute(Orders order) {

        order.cancelShipping();
    }
}
