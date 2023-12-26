package com.NotificationManagementSystem.SDAproject.TemplatePattern;

public class Gmail implements EmailService{

    private String emailAddress;

    // Constructors (if needed)

    // Setters

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // Getters

    public String getEmailAddress() {
        return emailAddress;
    }
    @Override
    public void service() {
        System.out.println("Wait for the email to arrive at " + emailAddress);
    }
}
