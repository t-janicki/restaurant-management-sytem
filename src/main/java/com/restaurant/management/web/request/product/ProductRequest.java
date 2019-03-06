package com.restaurant.management.web.request.product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ProductRequest {

//    @NotBlank
    private String uniqueId;

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @NotNull
    private double price;

    @NotNull
    private List<IngredientRequest> ingredients;

    public String getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public List<IngredientRequest> getIngredients() {
        return ingredients;
    }
}
