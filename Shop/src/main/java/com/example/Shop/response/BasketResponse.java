package com.example.Shop.response;

import lombok.Data;

@Data
public class BasketResponse {
    private Long id;
	private String brand;
    private String type;
    private String category;
    private String description;
    private Double price;
    private Integer rating;
    private String imgUrl;
    private Long ownerId;
    private Integer quantity;
}
