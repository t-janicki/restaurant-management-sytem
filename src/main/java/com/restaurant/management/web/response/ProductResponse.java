package com.restaurant.management.web.response;

import java.util.Calendar;
import java.util.List;

public class ProductResponse {
    private Long id;
    private String uniqueId;
    private String name;
    private String category;
    private Double price;
    private Calendar createdAt;
    private List<IngredientResponse> ingredients;

    public ProductResponse() {
    }

    public ProductResponse(Long id, String uniqueId, String name,
                           String category, Double price, Calendar createdAt,
                           List<IngredientResponse> ingredients) {
        this.id = id;
        this.uniqueId = uniqueId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.createdAt = createdAt;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public List<IngredientResponse> getIngredients() {
        return ingredients;
    }
}
