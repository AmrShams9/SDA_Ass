package com.NotificationManagementSystem.SDAproject;

import com.NotificationManagementSystem.SDAproject.CompositePattern.Orders;
import com.NotificationManagementSystem.SDAproject.TemplatePattern.EmailService;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private Account account;
    EmailService email;

    public Customer(String name, double balance) {
        this.name = name;

    }

    public void setName(String n)
    {
        name=n;
    }


    public String getName()
    {

        return name;
    }

    public EmailService getEmail() {
        return email;
    }


    public void createAccount(int accountId, String password, double initialBalance) {
        this.account = new Account(accountId, password, initialBalance,null);
    }

    public boolean login(int accountId, String password) {
        return account.Check(accountId, password);
    }
}
