package com.NotificationManagementSystem.SDAproject.CommandPattern;

import com.NotificationManagementSystem.SDAproject.CompositePattern.Orders;

public interface OrderCancellationCommand {
    void execute(Orders order);
}
