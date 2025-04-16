package com.msbprojects.products.controller;

import com.msbprojects.products.dto.CategoryDTO;
import com.msbprojects.products.exception.CategoryAlreadyExistsException;
import com.msbprojects.products.exception.CategoryNotFoundException;
import com.msbprojects.products.repository.CategoryRepository;
import com.msbprojects.products.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
@Tag(name = "Category Rest APIs",description = "create,read,update and delete Category operations.")
public class CategoryController {

    private CategoryService categoryService;

    @Operation(summary = "Fetch All Categories",description = "Rest Api to fetch all categories.")
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @Operation(summary = "Fetch Category by id",description = "Rest Api to fetch category by id.")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) throws CategoryNotFoundException {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Delete category by id",description = "Rest Api to delete category by id.")
    @DeleteMapping("/{id}")
    public String deleteCategoryById(@PathVariable Long id) {
        return categoryService.deleteCategoryById(id);
    }

    @ApiResponse(
            responseCode = "201",
            description = "CREATED"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Create Category",description = "Rest Api to create category.")
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
