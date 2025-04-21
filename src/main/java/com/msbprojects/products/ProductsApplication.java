package com.msbprojects.products;

import com.msbprojects.products.DummyTests.MyApp;
import com.msbprojects.products.DummyTests.MyComponent;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class ProductsApplication {

    // can comment this if using annotation , no need for mentioning specific class
   // private static final Logger log = LoggerFactory.getLogger(ProductsApplication.class);


    public static void main(String[] args) {

        SpringApplication.run(ProductsApplication.class, args);
            log.info("This is an info message");
            log.error("This is an error message");

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
