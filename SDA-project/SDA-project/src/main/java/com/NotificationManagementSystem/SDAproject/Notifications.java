package com.NotificationManagementSystem.SDAproject;

import com.NotificationManagementSystem.SDAproject.CompositePattern.OrderType;
import com.NotificationManagementSystem.SDAproject.TemplatePattern.EmailService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
@Component
@Lazy
public class Notifications implements EmailService {

    private Queue<EmailService> notificationsQueue;
    private String context;
    private ArrayList<EmailService> messages;

    public Notifications() {
        this.notificationsQueue = new LinkedList<>();
        this.messages = new ArrayList<>();
    }

    public Notifications(String context) {
        this.notificationsQueue = new LinkedList<>();
        this.context = context;
        this.messages = new ArrayList<>();
    }

    public Queue<EmailService> getNotificationsQueue() {
        return notificationsQueue;
    }

    public void setNotificationsQueue(Queue<EmailService> notificationsQueue) {
        this.notificationsQueue = notificationsQueue;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void sentToAccount(Account customer, EmailService notification, OrderType order, String context) {
        notification = new Notifications(context);
        // notification.showNotification(customer, order);
        notificationsQueue.add(notification);
        EmailService headNotification = notificationsQueue.peek();
        if (headNotification == notification) {
            notificationsQueue.poll(); // Remove the head of the queue
            customer.getAccNotifications().add(notification);
            notification.showNotification(customer, order);
        } else {
            System.out.println("Wait for your Turn");
        }
    }

    public void showNotificationsQueue() {
        System.out.println("Showing notifications for context: " + context);
        for (EmailService notification : notificationsQueue) {
            System.out.println("Notification: " + notification);
        }
    }

    public void pop(EmailService notification) {
        notificationsQueue.poll(); // Remove the head of the queue
    }

    @Override
    public void service() {
        // Implement service method if neede
        System.out.println("method not used yet:)");
    }
}
