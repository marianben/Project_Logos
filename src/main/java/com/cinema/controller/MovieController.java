package com.cinema.controller;

import javax.validation.Valid;
import java.util.List;

import com.cinema.dto.MovieDTO;
import com.cinema.repository.CategoryRepository;
import com.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cinema.repository.MovieRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("movies") // localhost:8080/movies/***
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<?> createMovie(
            @Valid @RequestBody MovieDTO movieDTO
    ) {
        movieService.saveMovie(movieDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getMovies() {
        List<MovieDTO> movies = movieService.findAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("{movieId:[0-9]{1,5}}") // \\d
    public ResponseEntity<?> getMovieById(@PathVariable("movieId") Long id) {
        MovieDTO movie = movieService.findMovieById(id);

        if (movie == null) {
            return new ResponseEntity<>("No element found", HttpStatus.NOT_FOUND);//404
        }

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PutMapping("{movieId:[0-9]{1,5}}")
    public ResponseEntity<?> updateMovie(
            @PathVariable("movieId") Long id,
            @RequestBody MovieDTO movieToUpdate
    ) {
        MovieDTO movie = movieService.updateMovie(id, movieToUpdate);

        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400
        }

        return new ResponseEntity<>(movie, HttpStatus.OK); // 200
    }

    @GetMapping("get_all")
    public ResponseEntity<?> getAllMovies(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(movieRepository.findAll(pageable));
    }

    @GetMapping("category")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }
}