package com.NotificationManagementSystem.SDAproject;

import com.NotificationManagementSystem.SDAproject.CompositePattern.CompoundOrder;
import com.NotificationManagementSystem.SDAproject.CompositePattern.OrderType;
import com.NotificationManagementSystem.SDAproject.CompositePattern.Orders;
import com.NotificationManagementSystem.SDAproject.CompositePattern.Product;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private int accountId;
    private String password;
    private double balance;
    private Cart cart;
    private List<OrderType> orders;
    private Location location;

    public Account(int accountId, String password, double balance,Cart carts) {
        this.accountId = accountId;
        this.password = password;
        this.balance = balance;
        this.orders = new ArrayList<>();
        this.cart =carts=null ;
    }


    public void setAccountId(int i)
    {
        accountId=i;
    }
    public int getAccountId()
    {
        return accountId;
    }
    public void setPassword(String p)
    {
        password=p;
    }

    public boolean Check(int inputAccountId, String inputPassword) {
        return accountId == inputAccountId && password.equals(inputPassword);
    }

    public double getBalance()
    {
        return balance;
    }

    public void deductBalance(double amount)
    {
        balance -= amount;
    }

    public void refundBalance(double amount)
    {
        balance += amount;
    }

    public List<OrderType> getOrders() {

        return orders;
    }

//change their  name-----
    public void placeOrder(OrderType order) {

        orders.add(order);
    }

    public void cancelOrder(OrderType order) {
        orders.remove(order);

    }//----

    public Cart getCart() {
        return cart;
    }

    public void addToCart(Product product) {
        cart.addProduct(product);
    }

    public void removeFromCart(Product product) {
        cart.removeProduct(product);
    }

    public List<Product> viewCart() {
        return cart.getProducts();
    }

    public void clearCart() {
        cart.clearCart();
    }

    public double calculateCartTotal() {
        return cart.calculateTotal();
    }


    public void PlaceSimpleOrder(OrderType simpleOrder ,String orderID, Location destination, PlaceHolder placeHolder) {
         simpleOrder = new Orders(orderID, destination, placeHolder);
        placeOrder(simpleOrder);
    }
  public void PlaceCompoundOrder(OrderType CompoundOrder,String orderId, ArrayList<Location> destinations, ArrayList<PlaceHolder> placeHolders)
  {
      CompoundOrder = new CompoundOrder(orderId,destinations,placeHolders);
      placeOrder(CompoundOrder);
  }

  public void purchase(Cart cart)
  {
      deductBalance(cart.calculateTotal());
      clearCart();
  }


 public void CancelOrderShipping(OrderType order)
 {
     //add logic
 }
 public void CancelOrderPlacement(OrderType order)
 {
     // add logic
 }







}
