package com.NotificationManagementSystem.SDAproject.CompositePattern;
//import org.springframework.stereotype.Component;


public interface Composite {
    void getChild(int i);
    void add(Composite composite);
    void delete(Composite composite);


    double getPrice();
}
