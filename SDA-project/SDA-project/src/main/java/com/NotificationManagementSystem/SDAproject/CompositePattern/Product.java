package com.NotificationManagementSystem.SDAproject.CompositePattern;

import com.NotificationManagementSystem.SDAproject.Account;
import com.NotificationManagementSystem.SDAproject.Location;
import com.NotificationManagementSystem.SDAproject.ObserverPattern.Observer;
import com.NotificationManagementSystem.SDAproject.ObserverPattern.Subject;
import com.NotificationManagementSystem.SDAproject.PlaceHolder;
import com.NotificationManagementSystem.SDAproject.TemplatePattern.EmailService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

@Component
@Lazy
public class Product implements Composite, Subject {

    private String name;
    private long serialNumber;
    private String category;
    private double price;
    private String vendor;
    private ArrayList<Observer> observerList;
    private String availability;
    private PlaceHolder placeHolder;

    public Product(String name, double price, String category, String vendor) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.vendor = vendor;
        this.observerList = new ArrayList<>();
        this.availability = "True";
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    public Product setCategory(String category) {
        this.category = category;
        return this;
    }

    public Product setVendor(String vendor) {
        this.vendor = vendor;
        return this;
    }

    public Product createProduct() {
        return new Product(name, price, category, vendor);
    }

    @Override
    public void getChild(int i) {
        // As a leaf node, a Product doesn't have children
        throw new UnsupportedOperationException("Leaf nodes do not have children");
    }

    @Override
    public void add(Composite composite) {
        // As a leaf node, a Product cannot have children
        throw new UnsupportedOperationException("Leaf nodes do not support adding children");
    }

    @Override
    public void delete(Composite composite) {
        // As a leaf node, a Product cannot have children to delete
        throw new UnsupportedOperationException("Leaf nodes do not have children to delete");
    }

    public double getPrice() {
        return price;
    }
    public void setAvailability(boolean availible , Observer account, EmailService notification ){
        availability = this.name + (availible ? "Available" : "Not Available");
        Notify(account, notification);
    }

    @Override
    public void subscribe(Observer account)
    {
        observerList.add(account);

    }

    @Override
    public void unsubscribe(Observer account)
    {
        if(observerList.contains(account)){
            observerList.remove(account);
        }else {
            System.out.println("There is No active User");
        }

    }

    @Override
    public void Notify(Observer account, EmailService notification)
    {

        for (Observer observer : observerList) {

            observer.update(availability);

        }

    }
    public String getName() {
        return name;
    }

    public long getSerialNumber() {
        return serialNumber;
    }


    public PlaceHolder getPlaceHolder() {
        return placeHolder;
    }

    public boolean isAvailable() {
        Random rand = new Random();
        int x = rand.nextInt(20);  // Use nextInt to generate a random integer in the range [0, 20)

        // Adjust the condition to make it more balanced
        if (x > 10) {
            return false;
        } else {
            return true;
        }
    }
}
