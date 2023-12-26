package com.NotificationManagementSystem.SDAproject.CompositePattern;
public class Product implements Composite{

    private String name;
    private long serialNumber;
    private String category;
    private double price;
    private String vendor;



   public Product(String name , double price , String category, String vendor)
   {
        this.name = name;
        this.price= price;
        this .category = category;
        this.vendor = vendor;


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

    }

    @Override
    public void add(Composite composite) {

    }

    @Override
    public void delete(Composite composite) {

    }
}
