package com.orderapi.entities.dtos;

import com.orderapi.entities.enums.Status;

import java.util.Set;

public class OrderDto {
    private Long id;
    private String table;
    private Status status;
    Set<ProductOrderDto> products;
    public OrderDto(){}
    public OrderDto(Long id, String table, Status status, Set<ProductOrderDto> products) {
        this.id = id;
        this.table = table;
        this.status = status;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<ProductOrderDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductOrderDto> products) {
        this.products = products;
    }
}
