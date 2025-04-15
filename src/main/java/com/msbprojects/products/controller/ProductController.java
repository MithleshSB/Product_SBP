package com.msbprojects.products.controller;

import com.msbprojects.products.dto.CategoryDTO;
import com.msbprojects.products.dto.ProductDTO;
import com.msbprojects.products.exception.CategoryNotFoundException;
import com.msbprojects.products.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;
    @PostMapping
    public ResponseEntity<ProductDTO> createProdcut(@RequestBody ProductDTO productDTO) throws CategoryNotFoundException {
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProductById(@PathVariable Long id,@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.updateProductById(id,productDTO), HttpStatus.OK);
    }
}
