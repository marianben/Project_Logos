package com.cinema.repository;

import com.cinema.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository
        extends JpaRepository<CategoryEntity, Long> {

    boolean existsById(Long id);

    boolean existsByNameIgnoreCase(String name);

    Optional<CategoryEntity> findByNameIgnoreCase(String name);
}