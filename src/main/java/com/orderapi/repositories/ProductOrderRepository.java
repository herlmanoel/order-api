package com.orderapi.repositories;

import com.orderapi.entities.Category;
import com.orderapi.entities.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

}
