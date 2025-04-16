package com.msbprojects.products.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(
        name = "Product",
        description = "It Holds Product Information."
)
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
