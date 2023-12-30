package com.NotificationManagementSystem.SDAproject;

public class PlaceHolder {
    private String name;
    private Location location;
    private Shipment shipment;

    // Constructors

    public PlaceHolder() {
    }

    public PlaceHolder(String name, Location location, Shipment shipment) {
        this.name = name;
        this.location = location;
        this.shipment = shipment;
    }
    public PlaceHolder(String name) {
        this.name = name;
        this.location = location;
        this.shipment = shipment;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    // Getters

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Shipment getShipment() {
        return shipment;
    }

    // Method to get object info

    public void getInfo() {
        System.out.println("PlaceHolder Info:");
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Shipment: " + shipment);
    }
}
