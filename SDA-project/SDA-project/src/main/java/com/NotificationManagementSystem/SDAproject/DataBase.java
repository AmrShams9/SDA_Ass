package com.NotificationManagementSystem.SDAproject;

import com.NotificationManagementSystem.SDAproject.CompositePattern.OrderType;
import com.NotificationManagementSystem.SDAproject.CompositePattern.Orders;
import com.NotificationManagementSystem.SDAproject.CompositePattern.Product;
import com.NotificationManagementSystem.SDAproject.TemplatePattern.EmailService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@Qualifier
@Configurable

public class DataBase {
    private List<Account> accounts;
    private ArrayList<OrderType> savedOrders;
    private ArrayList<Shipment> savedShipments;
    private ArrayList<EmailService> savedNotifications;
    private List<Product> savedProducts;
    private ArrayList<Cart> savedCarts;

@Bean(name = "DataBase")
public DataBase dataBase() {
    return new DataBase();
}
    public DataBase() {
        this.accounts = new ArrayList<>();
        this.savedNotifications = new ArrayList<>();
        this.savedShipments = new ArrayList<>();
        this.savedOrders = new ArrayList<>();
        this.savedProducts = new ArrayList<>();
        this.savedCarts = new ArrayList<>();
    }

    // Getters and Setters for accounts
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    // Getters and Setters for savedOrders
    public ArrayList<OrderType> getSavedOrders() {
        return savedOrders;
    }

    public void setSavedOrders(ArrayList<OrderType> savedOrders) {
        this.savedOrders = savedOrders;
    }

    // Getters and Setters for savedShipments
    public ArrayList<Shipment> getSavedShipments() {
        return savedShipments;
    }

    public void setSavedShipments(ArrayList<Shipment> savedShipments) {
        this.savedShipments = savedShipments;
    }

    // Getters and Setters for savedNotifications
    public ArrayList<EmailService> getSavedNotifications() {
        return savedNotifications;
    }

    public void setSavedNotifications(ArrayList<EmailService> savedNotifications) {
        this.savedNotifications = savedNotifications;
    }

    // Method to save an account
    public void saveAccount(Account account) {
        accounts.add(account);
    }

    // Method to save an order
    public void saveOrder(OrderType order) {
        savedOrders.add(order);
    }

    // Method to save a shipment
    public void saveShipment(Shipment shipment) {
        savedShipments.add(shipment);
    }

    // Method to save a notification
    public void saveNotification(EmailService notification) {
        savedNotifications.add(notification);
    }

    // Method to show all data
    public void showData() {
        System.out.println("Accounts:");
        for (Account account : accounts) {
            System.out.println(account);
        }

        System.out.println("Saved Orders:");
        for (OrderType order : savedOrders) {
            System.out.println(order);
        }

        System.out.println("Saved Shipments:");
        for (Shipment shipment : savedShipments) {
            System.out.println(shipment);
        }

        System.out.println("Saved Notifications:");
        for (EmailService notification : savedNotifications) {
            System.out.println(notification);
        }

        System.out.println("Saved Products:");
        for (Product product : savedProducts) {
            System.out.println(product);
        }
    }

    public void saveProduct(Product product) {
        savedProducts.add(product);
    }

    public List<Product> getSavedProducts() {
        return savedProducts;
    }

    public void saveCart(int accountId, Cart cart) {
        // Find the account by accountId and update its cart
        for (Account account :accounts) {
            if (account.getAccountId() == accountId) {
                account.setCart(cart);
                break;
            }
        }
    }
}
