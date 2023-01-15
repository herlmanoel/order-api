package com.orderapi.services;

import com.orderapi.entities.Category;
import com.orderapi.entities.dtos.CategoryDto;
import com.orderapi.repositories.CategoryRepository;
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
public class CategoryService  {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ModelMapper modelMapper;


    @Transactional(readOnly = true)
    public Page<CategoryDto> findAllPaged(PageRequest pageRequest){
        Page<Category> list = repository.findAll(pageRequest);
        Page<CategoryDto> Categorys = list.map(x -> entityToDto(x));
        return Categorys;
    }

    @Transactional
    public CategoryDto save(CategoryDto dto) {
        Category entity = dtoToEntity(dto);
        Category newEntity = repository.save(entity);
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
    public CategoryDto update(Long id, CategoryDto dto) {
        try {
            Optional<Category> entityOptional = repository.findById(id);

            if (entityOptional.isEmpty()) {
                throw new ResourceNotFoundException("Id not found " + id);
            }

            Category entity = dtoToEntity(dto);
            entity = repository.save(entity);

            return entityToDto(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional(readOnly = true)
    public CategoryDto findById(Long entityId) {
        Category user  = repository.findById(entityId)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
        return this.entityToDto(user);
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long userId) {
        return repository.existsById(userId);
    }


    public Category dtoToEntity(CategoryDto dto){
        return modelMapper.map(dto, Category.class);
    }

    public CategoryDto entityToDto(Category entity){
        return modelMapper.map(entity, CategoryDto.class);
    }

}
