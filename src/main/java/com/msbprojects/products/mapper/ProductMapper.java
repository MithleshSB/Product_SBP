package com.msbprojects.products.mapper;

import com.msbprojects.products.dto.CategoryDTO;
import com.msbprojects.products.dto.ProductDTO;
import com.msbprojects.products.entity.Category;
import com.msbprojects.products.entity.Product;

public class ProductMapper {

    public static ProductDTO toProductDTO(Product product){
        //using all args constructor
        return  new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(),
                product.getCategory().getId());
    }

    public static Product toProductEntity(ProductDTO productDTO, Category category){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        return product;
    }
}
