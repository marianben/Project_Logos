package com.cinema.service;

import com.cinema.dto.CategoryDTO;
import com.cinema.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    CategoryDTO save(CategoryDTO category);

    List<CategoryDTO> findAll();

    CategoryDTO findByCategoryId(Long id);

    CategoryDTO findByCategoryName(String name);
}