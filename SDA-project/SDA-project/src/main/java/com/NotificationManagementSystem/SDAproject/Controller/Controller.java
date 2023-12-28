package com.NotificationManagementSystem.SDAproject.Controller;

import com.NotificationManagementSystem.SDAproject.Account;
import com.NotificationManagementSystem.SDAproject.Cart;
import com.NotificationManagementSystem.SDAproject.DataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    private final DataBase database;

    @Autowired
    public Controller(DataBase database) {
        this.database = database;
    }

    // Registration endpoint
    @PostMapping("/register")
    public String register(@RequestParam String name,
                           @RequestParam String password,
                           @RequestParam double balance) {
        Account newAccount = new Account(database.getAccounts().size() + 1, password, balance, new Cart());
        newAccount.setName(name);
        database.getAccounts().add(newAccount);
        return "Registration successful!";
    }

    // Login endpoint
    @GetMapping("/login")
    public String login(@RequestParam int accountId,
                        @RequestParam String password) {
        List<Account> accounts = database.getAccounts();
        for (Account account : accounts) {
            if (account.getAccountId() == accountId && account.Check(accountId, password)) {
                return "Login successful! Welcome, " + account.getName() + "!";
            }
        }
        return "Login failed. Please check your credentials.";
    }
}