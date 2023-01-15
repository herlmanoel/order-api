package com.orderapi.services;

import com.orderapi.entities.Product;
import com.orderapi.entities.dtos.ProductDto;
import com.orderapi.repositories.ProductRepository;
import com.orderapi.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ModelMapper modelMapper;


    @Transactional(readOnly = true)
    public Page<ProductDto> findAllPaged(PageRequest pageRequest){
        Page<Product> list = repository.findAll(pageRequest);
        Page<ProductDto> Products = list.map(x -> entityToDto(x));
        return Products;
    }

    @Transactional
    public ProductDto save(ProductDto dto) {
        Product entity = dtoToEntity(dto);
        Product newEntity = repository.save(entity);
        return entityToDto(newEntity);
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found "+ id);
        }
    }

    @Transactional
    public ProductDto update(Long id, ProductDto dto) {
        try {
            Optional<Product> entityOptional = repository.findById(id);

            if (entityOptional.isEmpty()) {
                throw new ResourceNotFoundException("Id not found " + id);
            }

            Product entity = dtoToEntity(dto);
            entity = repository.save(entity);

            return entityToDto(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional(readOnly = true)
    public ProductDto findById(Long entityId) {
        Product user  = repository.findById(entityId)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
        return this.entityToDto(user);
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long userId) {
        return repository.existsById(userId);
    }


    public Product dtoToEntity(ProductDto dto){
        return modelMapper.map(dto, Product.class);
    }

    public ProductDto entityToDto(Product entity){
        return modelMapper.map(entity, ProductDto.class);
    }

}
