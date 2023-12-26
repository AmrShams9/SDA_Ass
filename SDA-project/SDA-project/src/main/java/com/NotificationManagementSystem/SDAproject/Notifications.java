package com.NotificationManagementSystem.SDAproject;

import com.NotificationManagementSystem.SDAproject.CompositePattern.OrderType;
import com.NotificationManagementSystem.SDAproject.TemplatePattern.EmailService;

import java.util.LinkedList;
import java.util.Queue;

public class Notifications {

    private Queue<EmailService> notificationsQueue;
    private String context;

    public Notifications() {
        this.notificationsQueue = new LinkedList<>();
    }

    public Notifications(String context) {
        this.notificationsQueue = new LinkedList<>();
        this.context = context;
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

    public void sentToAccount(Customer customer, EmailService notification, OrderType order) {
        notification.showNotification(customer, order);
        notificationsQueue.add(notification);
    }

    public void showNotifications() {
        System.out.println("Showing notifications for context: " + context);
        for (EmailService notification : notificationsQueue) {
            System.out.println("Notification: " + notification);
        }
    }
}
