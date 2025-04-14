package com.msbprojects.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO
{
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long CategoryId;

}
