package com.msbprojects.products.DummyTests;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAppConfig {

    //@Configuration tells this is the bean factory having methods level defined with @Bean annotation
    //these will be singleton bean, created once and used whereEver. Used for Third party classes,jar as per need.

    @Bean
    public MyComponent2 myComponent2(){
        return new MyComponent2();
    }
}
