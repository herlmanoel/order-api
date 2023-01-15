package com.orderapi.controllers;

import com.orderapi.entities.dtos.OrderDto;
import com.orderapi.entities.enums.Status;
import com.orderapi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<Page<OrderDto>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    )
    {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<OrderDto> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
        OrderDto userDto = service.findById(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto insert(@RequestBody OrderDto dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> update (
            @PathVariable Long id,
            @RequestBody OrderDto dto
    ) {
        if(!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderDto> patch(
            @PathVariable Long id,
            @RequestBody Status status    ) {

        if(!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        OrderDto dto = service.patch(id, status);

        return ResponseEntity.ok(dto);
    }
}
