package com.NotificationManagementSystem.SDAproject.CompositePattern;

import com.NotificationManagementSystem.SDAproject.Customer;
import com.NotificationManagementSystem.SDAproject.Location;
import com.NotificationManagementSystem.SDAproject.PlaceHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


public class Orders implements Composite, OrderType{
    private String orderID;
    private Location destination;
    private ArrayList<Composite> products;
    private PlaceHolder placeHolder;

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
    //Invoker of the Command Pattern
}
