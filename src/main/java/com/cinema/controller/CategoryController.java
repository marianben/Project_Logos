package com.cinema.controller;

import com.cinema.dto.CategoryDTO;
import com.cinema.entity.CategoryEntity;
import com.cinema.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CategoryDTO category) {
        CategoryDTO categoryDTO = categoryService.save(category);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<?> getById(@PathVariable("categoryId") Long id) {
        CategoryDTO categoryDTO = categoryService.findByCategoryId(id);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

}