package com.cinema.service;

import com.cinema.dto.MovieDTO;
import com.cinema.entity.MovieEntity;

import java.util.List;

public interface MovieService {

    void saveMovie(MovieDTO movie);

    List<MovieDTO> findAllMovies();

    MovieDTO findMovieById(Long id);

    MovieDTO updateMovie(Long id, MovieDTO movieToUpdate);
}