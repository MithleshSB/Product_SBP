package com.msbprojects.products.controller;

import com.msbprojects.products.dto.CategoryDTO;
import com.msbprojects.products.dto.ProductDTO;
import com.msbprojects.products.exception.CategoryNotFoundException;
import com.msbprojects.products.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@Tag(name = "Product Rest APIs",description = "create,read,update and delete Product operations.")
public class ProductController {

    private ProductService productService;

    @ApiResponse(
            responseCode = "201",
            description = "CREATED"
    )
    @Operation(summary = "Create Product",description = "Rest Api to create product.")
    @PostMapping
    public ResponseEntity<ProductDTO> createProdcut(@RequestBody ProductDTO productDTO) throws CategoryNotFoundException {
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Fetch All Products",description = "Rest Api to fetch all products.")
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @Operation(summary = "Fetch product by id",description = "Rest Api to fetch product by id.")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete product by id",description = "Rest Api to delete product by id.")
    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }

    @Operation(summary = "Update Product by id",description = "Rest Api to update product by id.")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProductById(@PathVariable Long id,@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.updateProductById(id,productDTO), HttpStatus.OK);
    }
}
