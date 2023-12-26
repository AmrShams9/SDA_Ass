package com.NotificationManagementSystem.SDAproject.CompositePattern;

import com.NotificationManagementSystem.SDAproject.Customer;
import com.NotificationManagementSystem.SDAproject.Location;
import com.NotificationManagementSystem.SDAproject.PlaceHolder;
import com.NotificationManagementSystem.SDAproject.Shipment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


public class Orders implements Composite, OrderType{
    private String orderID;
    private OrderType orderType;
    private Location destination;
    private ArrayList<Composite> products;
    private PlaceHolder placeHolder;

    public Orders(OrderType orderType, Location destination, Shipment shipment, String status) {
        this.orderType = orderType;
        this.destination = destination;
        this.products = new ArrayList<>();
        this.placeHolder = placeHolder;
    }

    @Override
    public void getChild(int i) {

    }

    @Override
    public void add(Composite composite) {

    }

    @Override
    public void delete(Composite composite) {

    }
// We can use this method as an interface
    public void cancelShipping() {
    }

    @Override
    public void createOrder(Customer account, OrderType order) {

    }

    @Override
    public boolean modifyOrder(Customer account, OrderType order) {
        return false;
    }

    @Override
    public boolean cancelOrder(Customer account, OrderType order) {
        return false;
    }

    @Override
    public ArrayList getProductName() {
        return products;
    }

    @Override
    public void setStatus(String newStatus) {

    }
    //Invoker of the Command Pattern
}
