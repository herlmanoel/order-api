package com.orderapi.entities;

import com.orderapi.entities.base.BaseEntity;
import com.orderapi.entities.dtos.CategoryDto;
import com.orderapi.entities.dtos.IngredientDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_product")
public class Product extends BaseEntity {
    private String name;
    private String description;
    private String imagePath;
    private double price;

    @ManyToOne
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_product_ingredient", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    public Product() {
    }

    public Product(Long id, String name, String description, String imagePath, double price, Category category, List<Ingredient> ingredients) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
