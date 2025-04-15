package com.msbprojects.products.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CategoryAlreadyExistsException extends Throwable {
    public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}
