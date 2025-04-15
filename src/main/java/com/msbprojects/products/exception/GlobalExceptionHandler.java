package com.msbprojects.products.exception;

import com.msbprojects.products.dto.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    // we can use exceptionDTO for better error response for the webRequest

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCategoryAlreadyExists(CategoryAlreadyExistsException ex,
                                                                            WebRequest webRequest) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                webRequest.getDescription(false),// will get path
                HttpStatus.CONFLICT,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponseDTO);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCategoryNotFound(CategoryNotFoundException ex,
                                                                       WebRequest webRequest) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                webRequest.getDescription(false),// will get path
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponseDTO);
    }

    //Spring will 1st find if any specific definition is there and if not then will call this generic handler
    // remove @AllArgsConstructor to create category will give null for injected repository to test this method.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleGlobalException(Exception ex,
                                                                      WebRequest webRequest) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                webRequest.getDescription(false),// will get path
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponseDTO);
    }
}
