package com.msbprojects.products.DummyTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyApp {
    private MyComponent2 myComponent;

    @Autowired
    public MyApp(MyComponent2 myComponent){
        this.myComponent = myComponent;
    }

    public void MyAppRun(){
       myComponent.getMessage();
    }
}
