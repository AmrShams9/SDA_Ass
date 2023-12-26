package com.NotificationManagementSystem.SDAproject.CompositePattern;

import com.NotificationManagementSystem.SDAproject.Customer;
import com.NotificationManagementSystem.SDAproject.Location;
import com.NotificationManagementSystem.SDAproject.PlaceHolder;


import java.util.ArrayList;

public class CompoundOrder implements Composite, OrderType{

    private String orderId;
    private ArrayList <Location> destination;
    private ArrayList<Composite> products;
    private ArrayList <PlaceHolder> placeHolder;
    @Override
    public void getChild(int i) {

    }

    @Override
    public void add(Composite composite) {

    }

    @Override
    public void delete(Composite composite) {

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
}
