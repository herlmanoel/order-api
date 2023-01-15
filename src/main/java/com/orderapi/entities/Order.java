package com.orderapi.entities;

import com.orderapi.entities.base.BaseEntity;
import com.orderapi.entities.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order extends BaseEntity   {
    private String nameTable;
    private Status status;

    @OneToMany
    Set<ProductOrder> products;

    public Order(){}

    public Order(Long id, String table, Status status, Set<ProductOrder> products) {
        this.id = id;
        this.nameTable = table;
        this.status = status;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String table) {
        this.nameTable = table;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<ProductOrder> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductOrder> products) {
        this.products = products;
    }
}
