package com.NotificationManagementSystem.SDAproject.Controller;

import com.NotificationManagementSystem.SDAproject.*;
import com.NotificationManagementSystem.SDAproject.CommandPattern.OrderPlacementCancellationCommand;
import com.NotificationManagementSystem.SDAproject.CompositePattern.CompoundOrder;
import com.NotificationManagementSystem.SDAproject.CompositePattern.OrderType;
import com.NotificationManagementSystem.SDAproject.CompositePattern.Orders;
import com.NotificationManagementSystem.SDAproject.CompositePattern.Product;
import com.NotificationManagementSystem.SDAproject.TemplatePattern.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    private final DataBase database;
    private Queue<EmailService> notificationService; // Assuming you have a NotificationService class
    private ArrayList<Product> productArrayList;
    private ArrayList<OrderType> simpleOrders;
    private ArrayList<OrderType> compoundOrder;

    public Controller(/*@Qualifier(value = "DataBase")*/ DataBase database, Queue<EmailService> notificationService, ArrayList<Product> productArrayList) {
        this.database = database;
        this.notificationService = notificationService;
        this.productArrayList = productArrayList;

        // Initialize some sample products (you can modify or add more as needed)
        Product product1 = new Product("Laptop", 1000.0, "Electronics", "Vendor A");
        Product product2 = new Product("Smartphone", 500.0, "Electronics", "Vendor B");
        Product product3 = new Product("Headphones", 50.0, "Electronics", "Vendor C");
        Product product4 = new Product("Mobile Charger", 100.0, "Electronics", "Vendor D");
        Product product5 = new Product("Pizza", 10.0, "Food", "Vendor E");
        Product product6 = new Product("T-Shirt", 30.0, "Clothing", "Vendor C");
        productArrayList.add(product1);
        productArrayList.add(product2);
        productArrayList.add(product3);
        productArrayList.add(product4);
        productArrayList.add(product5);
        productArrayList.add(product6);

        // Add products to the database
        database.saveProduct(product1);
        database.saveProduct(product2);
        database.saveProduct(product3);
        database.saveProduct(product4);
        database.saveProduct(product5);
        database.saveProduct(product6);
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

    @GetMapping("/validateUser")
    public String validateUser(@RequestParam int accountId,
                               @RequestParam String password) {
        List<Account> accounts = database.getAccounts();
        for (Account account : accounts) {
            if (account.getAccountId() == accountId && account.Check(accountId, password)) {
                return "User is valid!";
            }
        }
        return "User validation failed. Please check your credentials.";
    }

    @GetMapping("/listProducts")
    public List<Product> listProducts() {
        return database.getSavedProducts();
    }

    @PostMapping(value = "/addCart")
    public boolean addToCart(@RequestParam int accountId,
                             @RequestParam String password,
                             @RequestParam List<String> productNames) {
        // Step 1: Get the account based on accountId and password
        Account account = getAccount(accountId, password);

        // Step 2: Check if the account exists and credentials are valid
        if (account == null) {
            return false; // Invalid credentials or account not found
        }

        // Step 3: Get the user's cart from the database
        Cart userCart = account.getCart();

        // Step 4: Update the user's cart by adding the specified products
        for (String productName : productNames) {
            Product product = getProductByName(productName);
            if (product != null) {
                userCart.addProduct(product);
            }
        }

        // Step 5: Save the updated cart back to the database
        database.saveCart(accountId, userCart);

        return true; // Cart updated successfully
    }

    // Helper method to get an account based on accountId and password
    private Account getAccount(int accountId, String password) {
        List<Account> accounts = database.getAccounts();
        for (Account account : accounts) {
            if (account.getAccountId() == accountId && account.checkPassword(password)) {
                return account;
            }
        }
        return null; // Account not found or invalid credentials
    }

    // Helper method to get a product by name
    private Product getProductByName(String productName) {
        for (Product product : productArrayList) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null; // Product not found
    }

    @PostMapping("/purchaseCart")
    public String purchaseCartBySimpleOrder(@RequestParam int accountId,
                                            @RequestParam double cartPrice,
                                            @RequestParam Cart cart,
                                            @RequestParam OrderType simpleOrder) {
        // Find the account
        Account account = findAccountById(accountId);
        if (account == null) {
            return "Account not found.";
        }

        // Check if the provided cart is the same as the account's current cart
        if (!account.getCart().equals(cart)) {
            return "Invalid cart provided for the account.";
        }

        // Check if the provided simple order is valid
        if (!validateSimpleOrder(simpleOrder)) {
            return "Invalid simple order provided.";
        }

        // Check if the account has enough balance for the purchase
        double purchaseAmount = cartPrice;
        if (account.getBalance() < purchaseAmount) {
            return "Insufficient balance to purchase the products in the cart.";
        }

        // Deduct the purchase amount from the account balance
        account.deductBalance(purchaseAmount);
        account.purchase(cart);

        // Perform the purchase (you may want to add more purchase logic here if needed)
        /*for (Product product : cart.getProducts()) {
            account.purchase(product, simpleOrder);
        }*/

        return "Products purchased successfully. Remaining balance: $" + account.getBalance();
    }

    // Validate if the provided simple order is valid (you can modify this method based on your requirements)
    private boolean validateSimpleOrder(OrderType simpleOrder) {
        // Add your validation logic here
        return simpleOrder != null; // Example: Simple validation, just checking if it's not null
    }

    // Helper method to find an account by ID
    private Account findAccountById(int accountId) {
        List<Account> accounts = database.getAccounts();
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        return null;
    }

    // Helper method to find a product by serial number
    private Product findProductBySerialNumber(long serialNumber) {
        List<Product> products = database.getSavedProducts();
        for (Product product : products) {
            if (product.getSerialNumber() == serialNumber) {
                return product;
            }
        }
        return null;
    }

    @PostMapping("/orderCompoundOrder")
    public String orderCompoundOrder(@RequestParam int accountId,
                                     @RequestParam String orderId,
                                     @RequestParam Location destinations,
                                     @RequestParam ArrayList<Long> productSerialNumbers,
                                     @RequestParam ArrayList<Integer> friendAccountIds) {
        // Find the account
        Account account = findAccountById(accountId);
        if (account == null) {
            return "Account not found.";
        }
        ArrayList<PlaceHolder> placeHolderArrayList = new ArrayList<>();
        PlaceHolder placeHolder = new PlaceHolder();
        ArrayList<Location> x = new ArrayList<>();
        x.add(destinations);
        placeHolderArrayList.add(placeHolder);

        // Create a compound order
        List<OrderType> orders = new ArrayList<>();
        for (long serialNumber : productSerialNumbers) {
            Product product = findProductBySerialNumber(serialNumber);
            if (product != null) {
                orders.add(new Orders(orderId, destinations, placeHolder));
            }
        }

        // Find friend accounts
        List<Account> friendAccounts = new ArrayList<>();
        for (int friendAccountId : friendAccountIds) {
            Account friendAccount = findAccountById(friendAccountId);
            if (friendAccount != null) {
                friendAccounts.add(friendAccount);
            }
        }

        // Add friend orders to the compound order
        for (Account friendAccount : friendAccounts) {
            for (long serialNumber : productSerialNumbers) {
                Product product = findProductBySerialNumber(serialNumber);
                if (product != null) {
                    orders.add(new Orders(orderId, destinations, placeHolder));
                }
            }
        }

        // Deduct the total order amount from the account balance
        double totalOrderAmount = calculateTotalOrderAmountMain(orders);
        if (account.getBalance() < totalOrderAmount) {
            return "Insufficient balance to place the compound order.";
        }
        account.deductBalance(totalOrderAmount);

        // Perform the compound order (you may want to add more order-related logic here if needed)
        CompoundOrder compoundOrder = new CompoundOrder(orderId, x, placeHolderArrayList);
        account.addOrder(compoundOrder, database);

        return "Compound order placed successfully. Remaining balance: $" + account.getBalance();
    }

    // Helper method to calculate the total order amount
    private double calculateTotalOrderAmountMain(List<OrderType> orders) {
        double totalOrderAmount = 0.0;
        for (OrderType order : orders) {
            totalOrderAmount += order.calculateOrderAmount(); // Modify this based on your actual calculation logic
        }
        return totalOrderAmount;
    }


    @PostMapping("/placeSimpleOrder")
    public String placeSimpleOrder(@RequestParam int accountId,
                                   @RequestParam String orderId,
                                   @RequestParam String location,
                                   @RequestParam List<Long> productSerialNumbers) {
        // Find the account
        Account account = findAccountById(accountId);
        if (account == null) {
            return "Account not found.";
        }
        Location loco = new Location(location);
        PlaceHolder p = new PlaceHolder(location);

        // Create a simple order
        List<OrderType> orders = new ArrayList<>();
        for (long serialNumber : productSerialNumbers) {
            Product product = findProductBySerialNumber(serialNumber);
            if (product != null) {
                orders.add(new Orders(orderId, loco, product.getPlaceHolder()));
            }
        }

        // Deduct the total order amount from the account balance
        double totalOrderAmount = calculateTotalOrderAmount(orders);
        if (account.getBalance() < totalOrderAmount) {
            return "Insufficient balance to place the simple order.";
        }
        account.deductBalance(totalOrderAmount);

        // Perform the simple order (you may want to add more order-related logic here if needed)
        Orders simpleOrder = new Orders(orderId, loco,p);
        account.addOrder(simpleOrder, database);

        return "Simple order placed successfully. Remaining balance: $" + account.getBalance();
    }

    // Helper method to calculate the total order amount
    private double calculateTotalOrderAmount(List<OrderType> orders) {
        double totalAmount = 0.0;
        for (OrderType order : orders) {
            totalAmount += order.calculateOrderAmount();
        }
        return totalAmount;
    }

    @PostMapping("/sendNotification")
    public String sendNotification(@RequestParam int accountId,
                                   @RequestParam String notificationContext,
                                   @RequestParam int orderId,
                                   @RequestParam Product subject) {
        // Find the account
        Account account = findAccountById(accountId);
        if (account == null) {
            return "Account not found.";
        }

        // Check product availability
        Optional<String> availabilityStatus = checkProductAvailability(subject);

        // Create a notification with availability status
        String context = notificationContext + "\nProduct Availability: " + availabilityStatus.orElse("Not specified");
        EmailService notification = new Notifications(context);

        // Attach the account as an observer to the product
        subject.subscribe(account);

        // Notify the observers (accounts) about the change in the product (availability status)
        subject.notify();

        // Send the notification to the account
        notificationService.add(notification);

        // Detach the account as an observer after sending the notification
        subject.unsubscribe(account);

        return "Notification sent successfully.";
    }

    // Method to check product availability
    private Optional<String> checkProductAvailability(Product product) {
        // Replace this with your actual logic to check product availability
        // Example: Check if the product is in stock, has sufficient quantity, etc.
        if (product.isAvailable()) {
            return Optional.of("Available");
        } else {
            return Optional.of("Out of stock");
        }
    }

    @PostMapping("/cancelOrderPlacement")
    public String cancelOrderPlacement(@RequestParam int accountId,
                                       @RequestParam String orderId) {
        // Find the account
        Account account = findAccountById(accountId);
        if (account == null) {
            return "Account not found.";
        }

        // Find the order to be canceled
        OrderType orderToCancel = findOrderById(orderId, account.getOrders());
        if (orderToCancel == null) {
            return "Order not found.";
        }

        // Create a command to cancel order placement
        OrderPlacementCancellationCommand command = new OrderPlacementCancellationCommand(account ,orderToCancel);
        account.CancelOrderPlacement(command, account, orderToCancel, database);

        return "Order placement canceled successfully.";
    }

    // Cancel Order Shipping Endpoint
    @PostMapping("/cancelOrderShipping")
    public String cancelOrderShipping(@RequestParam int accountId,
                                      @RequestParam String orderId) {
        // Find the account
        Account account = findAccountById(accountId);
        if (account == null) {
            return "Account not found.";
        }

        // Find the order to be canceled
        OrderType orderToCancel = findOrderById(orderId, account.getOrders());
        if (orderToCancel == null) {
            return "Order not found.";
        }

        // Cancel order shipping
        if (orderToCancel instanceof CompoundOrder) {
            CompoundOrder compoundOrder = (CompoundOrder) orderToCancel;
            compoundOrder.cancelOrder(account, orderToCancel);
        } else {
            // Handle simple order shipping cancellation logic if needed
        }

        return "Order shipping canceled successfully.";
    }

    // Helper method to find an order by ID
    private OrderType findOrderById(String orderId, List<OrderType> orders) {
        for (OrderType order : orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    // Get Account Notifications Endpoint
    @GetMapping("/getAccountNotifications")
    public List<String> getAccountNotifications(@RequestParam int accountId,
                                                @RequestParam String location,
                                                @RequestParam String placeHolder) {
        // Find the account
        Account account = findAccountById(accountId);
        if (account == null) {
            return new ArrayList<>(); // Return an empty list if the account is not found
        }
        Location loc = new Location(location);
        PlaceHolder q = new PlaceHolder(placeHolder);
        OrderType order = new Orders("12",loc,q);


        // Retrieve account notifications
        List<String> accountNotifications = new ArrayList<>();
        for (EmailService notification : account.getAccNotifications()) {
            accountNotifications.add(notification.showNoty());
        }

        return accountNotifications;
    }

    // Send Message to Account using Notification Queue Endpoint
    @PostMapping("/sendMessageToAccount")
    public String sendMessageToAccount(@RequestParam int accountId,
                                       @RequestParam String message) {
        // Find the account
        Account account = findAccountById(accountId);
        if (account == null) {
            return "Account not found.";
        }

        // Create a notification with the provided message
        EmailService notification = new Notifications(message);

        // Add the notification to the notification queue
        notificationService.add(notification);

        return "Message added to the notification queue successfully.";
    }

    public void addNotificationsQueue(Notifications notification) {
        if (notificationService != null) {
            notificationService.add(notification);
        }


    }
}

