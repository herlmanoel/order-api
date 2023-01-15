package com.orderapi.controllers;

import com.orderapi.entities.dtos.IngredientDto;
import com.orderapi.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService service;

    @GetMapping
    public ResponseEntity<Page<IngredientDto>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    )
    {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<IngredientDto> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDto> findById(@PathVariable Long id) {
        IngredientDto userDto = service.findById(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IngredientDto insert(@RequestBody IngredientDto dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientDto> update (
            @PathVariable Long id,
            @RequestBody IngredientDto dto
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
}
