package com.msbprojects.products.service;

import com.msbprojects.products.dto.CategoryDTO;
import com.msbprojects.products.entity.Category;
import com.msbprojects.products.entity.Product;
import com.msbprojects.products.exception.CategoryAlreadyExistsException;
import com.msbprojects.products.exception.CategoryNotFoundException;
import com.msbprojects.products.mapper.CategoryMapper;
import com.msbprojects.products.mapper.ProductMapper;
import com.msbprojects.products.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;
    //create category
    public CategoryDTO createCategory(CategoryDTO categoryDTO) throws CategoryAlreadyExistsException {
        Optional<Category> optionalCategory= categoryRepository.findByName(categoryDTO.getName());
        if(optionalCategory.isPresent()){
            throw new CategoryAlreadyExistsException("Category "+categoryDTO.getName()+" already exists.");
        }
        Category categoryEntity = CategoryMapper.toCategoryEntity(categoryDTO);
        Category savedEntity = categoryRepository.save(categoryEntity);
        return CategoryMapper.toCategoryDTO(savedEntity);
    }

    public List<CategoryDTO> getAllCategories(){
        return categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDTO).toList();
    }
    public CategoryDTO getCategoryById(Long Id) throws CategoryNotFoundException {
        Category byId = categoryRepository.findById(Id).orElseThrow(()->
                new CategoryNotFoundException("Category with ID: "+Id+" not found"));
        return CategoryMapper.toCategoryDTO(byId);
    }
    public String deleteCategoryById(Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.map(product -> {
            categoryRepository.deleteById(id);
            return "Category deleted with ID: " + id;
        }).orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
    }
}
