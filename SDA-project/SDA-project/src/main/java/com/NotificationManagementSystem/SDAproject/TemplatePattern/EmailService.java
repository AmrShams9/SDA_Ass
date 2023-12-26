package com.NotificationManagementSystem.SDAproject.TemplatePattern;

import com.NotificationManagementSystem.SDAproject.CompositePattern.OrderType;
import com.NotificationManagementSystem.SDAproject.Customer;

public interface EmailService {
    default void showNotification(Customer customer, OrderType order) {
        String template = "Dear {x}, your booking of the {y} is confirmed. Thanks for using our store :)";
        String personalizedMessage = template
                .replace("{x}", customer.getName())
                .replace("{y}", order.getProductName().toString());

        sendEmail(customer.getEmail().toString(), "Order Confirmation", personalizedMessage);
    }

    private void sendEmail(String to, String subject, String content){
        System.out.println("Sending email to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Content: " + content);
        System.out.println("Email sent successfully!");
    }

    void service();
}



