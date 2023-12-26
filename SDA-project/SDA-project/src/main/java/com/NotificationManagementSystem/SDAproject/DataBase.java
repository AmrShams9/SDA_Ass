package com.NotificationManagementSystem.SDAproject;

import com.NotificationManagementSystem.SDAproject.CompositePattern.OrderType;
import com.NotificationManagementSystem.SDAproject.CompositePattern.Orders;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Customer> customers;

    public Database() {
        this.customers = new ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void saveOrder(Customer customer, OrderType orderType, Location location, Shipment shipment, String orderStatus) {
        OrderType order = new Orders(orderType, location, shipment, orderStatus);
        customer.placeOrder(order);
    }

    public Customer getCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    public List<OrderType> getAllOrders() {
        List<OrderType> allOrders = new ArrayList<>();
        for (Customer customer : customers) {
            allOrders.addAll(customer.getOrders());
        }
        return allOrders;
    }

    public List<OrderType> getOrdersByCustomer(Customer customer) {
        return customer.getOrders();
    }

    public void updateOrderStatus(OrderType order, String newStatus) {
        order.setStatus(newStatus);
    }

    // Additional methods as needed
}
