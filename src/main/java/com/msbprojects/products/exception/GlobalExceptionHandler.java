package com.msbprojects.products.exception;

import com.msbprojects.products.dto.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

//@RestControllerAdvice //  You need this for the handlers to work
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCategoryAlreadyExists(CategoryAlreadyExistsException ex,
                                                                            WebRequest webRequest) {
        return buildResponse(ex, webRequest, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCategoryNotFound(CategoryNotFoundException ex,
                                                                       WebRequest webRequest) {
        return buildResponse(ex, webRequest, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleProductNotFound(ProductNotFoundException ex,
                                                                       WebRequest webRequest) {
        return buildResponse(ex, webRequest, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleGlobalException(Exception ex, WebRequest webRequest) {

        String path = webRequest.getDescription(false);
        // Let Swagger endpoints fail naturally so SpringDoc can handle it
        if (path.contains("/v3/api-docs") || path.contains("/swagger-ui") || path.contains("/swagger-ui.html")) {
            throw new RuntimeException(ex); // Rethrow, don't handle it
        }

        return buildResponse(ex, webRequest, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 🔧 Reusable method to avoid repetition
    private ResponseEntity<ExceptionResponseDTO> buildResponse(Exception ex,
                                                               WebRequest webRequest,
                                                               HttpStatus status) {
        ExceptionResponseDTO response = new ExceptionResponseDTO(
                webRequest.getDescription(false),
                status,
                (ex.getMessage() != null ? ex.getMessage() : "Unexpected error occurred"),
                LocalDateTime.now()
        );
        return ResponseEntity.status(status).body(response);
    }
}
