package com.orderapi.entities.dtos;

import java.util.List;

public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private String imagePath;
    private String price;

    private CategoryDto category;
    private List<IngredientDto> ingredients;
    public ProductDto(){}
    public ProductDto(Long id, String name, String description, String imagePath, String price, CategoryDto category, List<IngredientDto> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.price = price;
        this.category = category;
        this.ingredients = ingredients;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }
}
