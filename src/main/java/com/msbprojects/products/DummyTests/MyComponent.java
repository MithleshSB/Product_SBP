package com.msbprojects.products.DummyTests;

import org.springframework.stereotype.Component;

@Component
public class MyComponent {
    public MyComponent() {
        System.out.println("My component contructor");
    }
    public void getMessage() {
        System.out.println("My component get Message");
    }
}
