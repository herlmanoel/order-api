package com.orderapi.services;

import com.orderapi.entities.Order;
import com.orderapi.entities.dtos.OrderDto;
import com.orderapi.entities.enums.Status;
import com.orderapi.repositories.OrderRepository;
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
public class OrderService  {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private ModelMapper modelMapper;


	@Transactional(readOnly = true)
	public Page<OrderDto> findAllPaged(PageRequest pageRequest){
		Page<Order> list = repository.findAll(pageRequest);
		Page<OrderDto> Orders = list.map(x -> entityToDto(x));
		return Orders;
	}

	@Transactional
	public OrderDto save(OrderDto dto) {
		Order entity = dtoToEntity(dto);
		Order newEntity = repository.save(entity);
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
	public OrderDto update(Long id, OrderDto dto) {
		try {
			Optional<Order> entityOptional = repository.findById(id);

			if (entityOptional.isEmpty()) {
				throw new ResourceNotFoundException("Id not found " + id);
			}

			Order entity = dtoToEntity(dto);
			entity = repository.save(entity);

			return entityToDto(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	@Transactional(readOnly = true)
	public OrderDto findById(Long entityId) {
		Order user  = repository.findById(entityId)
				.orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
		return this.entityToDto(user);
	}

	@Transactional(readOnly = true)
	public boolean existsById(Long userId) {
		return repository.existsById(userId);
	}

	@Transactional
	public OrderDto patch(Long id, Status status) {
		Order entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Entity not found."));

		entity.setStatus(status);
		repository.save(entity);

		return entityToDto(entity);
	}


	public Order dtoToEntity(OrderDto dto){
		return modelMapper.map(dto, Order.class);
	}

	public OrderDto entityToDto(Order entity){
		return modelMapper.map(entity, OrderDto.class);
	}

}
