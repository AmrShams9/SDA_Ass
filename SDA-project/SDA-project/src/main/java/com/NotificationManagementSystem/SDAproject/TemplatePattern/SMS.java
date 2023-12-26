package com.NotificationManagementSystem.SDAproject.TemplatePattern;

public class SMS implements EmailService{

    private String phoneNumber;


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getters

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void service() {
        System.out.println("Wait for the SMS to arrive at " + phoneNumber);
    }

    // Custom method for sending SMS
    private void sendSMS(String phoneNumber, String message) {
        // Implementation for sending SMS
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }
}
