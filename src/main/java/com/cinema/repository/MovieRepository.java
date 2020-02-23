package com.cinema.repository;

import com.cinema.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository
        extends JpaRepository<MovieEntity, Long> {


    boolean existsById(Long id);
}