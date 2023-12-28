package com.NotificationManagementSystem.SDAproject.CommandPattern;

import com.NotificationManagementSystem.SDAproject.Account;
import com.NotificationManagementSystem.SDAproject.CompositePattern.OrderType;
import com.NotificationManagementSystem.SDAproject.CompositePattern.Orders;

public class OrderShippingCancellationCommand implements OrderCancellationCommand {
    private OrderType order;
    private Account account;

    public OrderShippingCancellationCommand(Account account, OrderType order) {
        this.account = account;
        this.order = order;
    }

    @Override
    public void cancelOrder(Account account, OrderType order) {

        order.cancelShipping(account, order);
    }
}
