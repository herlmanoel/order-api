package com.orderapi.entities.dtos;

public class ProductOrderDto {
    private Long id;
    private ProductDto product;
    private int quantity;

    public ProductOrderDto(){}

    public ProductOrderDto(Long id, ProductDto product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
