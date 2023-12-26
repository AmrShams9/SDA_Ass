package com.NotificationManagementSystem.SDAproject;

public class Location {
    private String location;
    private String ipAddress;

    // Constructors (if needed)

    // Setters

    public void setLocation(String location) {
        this.location = location;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    // Getters

    public String getLocation() {
        return location;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    // Method to get object info

    public void getInfo() {
        System.out.println("Location Info:");
        System.out.println("Location: " + location);
        System.out.println("IP Address: " + ipAddress);
    }
}
