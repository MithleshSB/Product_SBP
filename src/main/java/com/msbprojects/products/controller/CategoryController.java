package com.msbprojects.products.controller;

import com.msbprojects.products.dto.CategoryDTO;
import com.msbprojects.products.exception.CategoryAlreadyExistsException;
import com.msbprojects.products.exception.CategoryNotFoundException;
import com.msbprojects.products.repository.CategoryRepository;
import com.msbprojects.products.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) throws CategoryNotFoundException {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public String deleteCategoryById(@PathVariable Long id) {
        return categoryService.deleteCategoryById(id);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) throws CategoryAlreadyExistsException {
        //case 1, we can't do try catch in all methods hence we need global handler
//        try{
//            CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
//            return  ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
//        } catch (CategoryAlreadyExists ex) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
//        }
        // Global exception handle will handle automatically no need for try catch.
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }
}
