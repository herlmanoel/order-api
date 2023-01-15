package com.orderapi.entities;

import com.orderapi.entities.base.BaseEntity;
import com.orderapi.entities.dtos.ProductDto;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product_order")
public class ProductOrder extends BaseEntity {
    private int quantity;

    @ManyToOne
    private Product product;

    public ProductOrder() {
    }

    public ProductOrder(Long id, Product product, int quantity) {
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
