package com.msbprojects.products.service;

import com.msbprojects.products.dto.CategoryDTO;
import com.msbprojects.products.dto.ProductDTO;
import com.msbprojects.products.entity.Category;
import com.msbprojects.products.entity.Product;
import com.msbprojects.products.exception.CategoryNotFoundException;
import com.msbprojects.products.mapper.CategoryMapper;
import com.msbprojects.products.mapper.ProductMapper;
import com.msbprojects.products.repository.CategoryRepository;
import com.msbprojects.products.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    //create category
    public ProductDTO createProduct(ProductDTO productDTO) throws CategoryNotFoundException {

        //in payload we will have name, price ,description,categoryID
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category with ID: "+productDTO.getCategoryId()+" not found."));
        Product productEntity = ProductMapper.toProductEntity(productDTO,category);
        Product savedEntity = productRepository.save(productEntity);
        return ProductMapper.toProductDTO(savedEntity);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();
    }

    public ProductDTO getProductById(Long id) {
        Product byId = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product Not Found"));
        return ProductMapper.toProductDTO(byId);
    }

    public String deleteProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.map(product -> {
            productRepository.deleteById(id);
            return "Product deleted with ID: " + id;
        }).orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

    }

    public ProductDTO updateProductById(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        Category  category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()->new RuntimeException("Category Not Found with Id "+ productDTO.getCategoryId()));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        return ProductMapper.toProductDTO(productRepository.save(product));
    }
}
