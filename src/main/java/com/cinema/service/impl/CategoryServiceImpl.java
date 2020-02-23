package com.cinema.service.impl;

import com.cinema.Exeption.AlreadyExistsException;
import com.cinema.Exeption.NotFoundException;
import com.cinema.dto.CategoryDTO;
import com.cinema.entity.CategoryEntity;
import com.cinema.repository.CategoryRepository;
import com.cinema.service.CategoryService;
import com.cinema.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public CategoryDTO save(CategoryDTO category) {
        boolean exists = categoryRepository.existsByNameIgnoreCase(category.getName());
        if (exists) {
            throw new AlreadyExistsException("Category with name [" + category.getName() + "] already exists");
        }

        CategoryEntity categoryEntity = modelMapper.map(category, CategoryEntity.class);// dtoToEntityMapper(category);
        categoryRepository.save(categoryEntity);
        category.setId(categoryEntity.getId());
        return category;
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();

        List<CategoryDTO> categoryDTOS = modelMapper.mapAll(categoryEntities, CategoryDTO.class);

//        for (CategoryEntity entity : categoryEntities) {
//            CategoryDTO categoryDTO = entityToDTOMapper(entity);
//            categoryDTOS.add(categoryDTO);
//        }
        return categoryDTOS;
    }

    @Override
    public CategoryDTO findByCategoryId(Long id) {
//        boolean exists = categoryRepository.existsById(id);
//        if (!exists) {
//            throw new NotFoundException("Category with id[" + id + "] not found");
//        }

        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Category with id[" + id + "] not found")
                );

        CategoryDTO categoryDTO = modelMapper.map(categoryEntity, CategoryDTO.class);//entityToDTOMapper(categoryEntity);
        return categoryDTO;
    }

    @Override
    public CategoryDTO findByCategoryName(String name) {

        CategoryEntity categoryEntity = categoryRepository.findByNameIgnoreCase(name)
                .orElseThrow(
                        () -> new NotFoundException("Category with name[" + name + "] not found")
                );
        return modelMapper.map(categoryEntity, CategoryDTO.class);
    }


/*    private CategoryDTO entityToDTOMapper(CategoryEntity categoryEntity) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setName(categoryEntity.getName());
        return categoryDTO;
    }
    private CategoryEntity dtoToEntityMapper(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryDTO.getId());
        categoryEntity.setName(categoryDTO.getName());
        return categoryEntity;
    }*/
}