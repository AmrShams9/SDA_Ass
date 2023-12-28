package com.NotificationManagementSystem.SDAproject.CommandPattern;

import com.NotificationManagementSystem.SDAproject.Account;
import com.NotificationManagementSystem.SDAproject.CompositePattern.OrderType;
import com.NotificationManagementSystem.SDAproject.CompositePattern.Orders;

public interface OrderCancellationCommand {
    void cancelOrder(Account account, OrderType order);
}
