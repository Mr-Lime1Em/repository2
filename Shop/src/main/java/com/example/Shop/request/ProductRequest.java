package com.example.Shop.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequest {
	@NotBlank(message = "Brand can't be empty")
    private String brand;
	@NotBlank(message = "Type can't be empty")
    private String type;
	@NotBlank(message = "Category can't be empty")
    private String category;
	@Size(max = 1000, message = "Description can't be that length")
    private String description;
	@NotNull(message = "Price can't be empty")
    private Double price;
	@Max(value = 5, message = "Rating can't be higher than 5")
	@Min(value = 1, message = "Rating can't be lower than 1")
    private Integer rating;
    private String imgUrl;
}
