package com.orderapi.components;

import com.orderapi.entities.Category;
import com.orderapi.entities.Ingredient;
import com.orderapi.entities.Product;
import com.orderapi.repositories.CategoryRepository;
import com.orderapi.repositories.IngredientRepository;
import com.orderapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InsertDataListener {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productsRepository;
    List<Category> categoriesList = List.of(
            new Category(1L, "Massas", ""),
            new Category(2L, "Pizzas", ""),
            new Category(3L, "Bebidas", ""),
            new Category(4L, "Sobremesas", "")
    );

    List<Ingredient> ingredientList = List.of(
            new Ingredient(1L, "Queijo", ""),
            new Ingredient(2L, "Tomate", ""),
            new Ingredient(3L, "Leite", ""),
            new Ingredient(4L, "Arroz", ""),
            new Ingredient(2L, "Chocolate", "")
    );

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        ingredientsSave();
        categoriesSave();
        productsSave();
    }



    private void ingredientsSave() {
        ingredientList.forEach(item -> ingredientRepository.save(item));
    }

    private void categoriesSave() {
        categoriesList.forEach(item -> categoryRepository.save(item));
    }

    private void productsSave() {
        List<Product> productList = List.of(
                new Product(1L, "Macarrão", "descrição 01", "", 19.4, categoriesList.get(1), ingredientList)
        );

        productList.forEach(item -> productsRepository.save(item));
    }
}
