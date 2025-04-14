package com.msbprojects.products.service;

import com.msbprojects.products.dto.CategoryDTO;
import com.msbprojects.products.entity.Category;
import com.msbprojects.products.mapper.CategoryMapper;
import com.msbprojects.products.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;
    //create category
    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Category categoryEntity = CategoryMapper.toCategoryEntity(categoryDTO);
        Category savedEntity = categoryRepository.save(categoryEntity);
        return CategoryMapper.toCategoryDTO(savedEntity);
    }
}
