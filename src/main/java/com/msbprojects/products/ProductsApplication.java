package com.msbprojects.products;

import com.msbprojects.products.DummyTests.MyApp;
import com.msbprojects.products.DummyTests.MyComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ProductsApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProductsApplication.class, args);

        // Testing

        //CASE 1 - getting object from application context and calling method @Component used.
//		ApplicationContext context  = SpringApplication.run(ProductsApplication.class, args);
//		MyComponent object = context.getBean(MyComponent.class);
//		object.getMessage();

        //CASE 2 - direct hard creation because no @Component used , spring will not find the bean.
        //MyComponent mc = new MyComponent();
        //mc.getMessage(); // can't call this

        //CASE 3 - using config and third party, @bean annotation with constructor injection
//		ApplicationContext context  = SpringApplication.run(ProductsApplication.class, args);
//		MyApp object = context.getBean(MyApp.class);
//		object.MyAppRun();
    }

}
