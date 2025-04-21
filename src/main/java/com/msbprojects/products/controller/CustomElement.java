package com.msbprojects.products.controller;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "mycustomEndpoint")
public class CustomElement {

    @ReadOperation
    public String getMessage(){
        return "This is custom Endpoint message.";
    }

}
