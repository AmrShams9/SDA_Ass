package com.NotificationManagementSystem.SDAproject.ObserverPattern;

import com.NotificationManagementSystem.SDAproject.Account;
import com.NotificationManagementSystem.SDAproject.TemplatePattern.EmailService;

public interface Subject{
    void subscribe(Observer account);
    void unsubscribe(Observer account);
    void Notify(Observer account, EmailService notification);
}
