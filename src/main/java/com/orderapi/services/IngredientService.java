package com.orderapi.services;

import com.orderapi.entities.Ingredient;
import com.orderapi.entities.dtos.IngredientDto;
import com.orderapi.repositories.IngredientRepository;
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
public class IngredientService {

    @Autowired
    private IngredientRepository repository;

    @Autowired
    private ModelMapper modelMapper;


    @Transactional(readOnly = true)
    public Page<IngredientDto> findAllPaged(PageRequest pageRequest){
        Page<Ingredient> list = repository.findAll(pageRequest);
        Page<IngredientDto> ingredients = list.map(x -> entityToDto(x));
        return ingredients;
    }

    @Transactional
    public IngredientDto save(IngredientDto dto) {
        Ingredient entity = dtoToEntity(dto);
        Ingredient newEntity = repository.save(entity);
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
    public IngredientDto update(Long id, IngredientDto dto) {
        try {
            Optional<Ingredient> entityOptional = repository.findById(id);

            if (entityOptional.isEmpty()) {
                throw new ResourceNotFoundException("Id not found " + id);
            }

            Ingredient entity = dtoToEntity(dto);
            entity = repository.save(entity);

            return entityToDto(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional(readOnly = true)
    public IngredientDto findById(Long entityId) {
        Ingredient user  = repository.findById(entityId)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
        return this.entityToDto(user);
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long userId) {
        return repository.existsById(userId);
    }


    public Ingredient dtoToEntity(IngredientDto dto){
        return modelMapper.map(dto, Ingredient.class);
    }

    public IngredientDto entityToDto(Ingredient entity){
        return modelMapper.map(entity, IngredientDto.class);
    }

}
