package com.NotificationManagementSystem.SDAproject;
import com.NotificationManagementSystem.SDAproject.CommandPattern.*;
import com.NotificationManagementSystem.SDAproject.CompositePattern.*;


import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class OrderCancellationManager {
    private List<OrderCancellationCommand> cancellationCommands;

    public OrderCancellationManager() {
        this.cancellationCommands = new ArrayList<>();
    }

    public void registerCancellationCommand(OrderCancellationCommand cancellationCommand) {
        cancellationCommands.add(cancellationCommand);
    }

    public void cancelOrderPlacement(Orders order) {
        for (OrderCancellationCommand command : cancellationCommands) {
            if (command instanceof OrderPlacementCancellationCommand) {
                command.execute(order);
            }
        }
    }

    public void cancelOrderShipping(Orders order) {
        for (OrderCancellationCommand command : cancellationCommands) {
            if (command instanceof OrderShippingCancellationCommand) {
                command.execute(order);
            }
        }
    }
}

