package com.dpf.movies.controller.impl;

import com.dpf.movies.controller.MovieController;
import com.dpf.movies.core.base.BaseController;
import com.dpf.movies.dto.MovieDetailOutDTO;
import com.dpf.movies.dto.MovieInDTO;
import com.dpf.movies.dto.MovieOutDTO;
import com.dpf.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${v1API}/${resource.movies}")
public class MovieControllerImpl extends BaseController implements MovieController {

    private MovieService movieService;

    @Autowired
    public MovieControllerImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDetailOutDTO create(@RequestBody MovieInDTO movie) {
        return movieService.save(movie);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MovieOutDTO> readAll(Pageable pageable) {
        return movieService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieDetailOutDTO read(@PathVariable Long id) {
        return movieService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieDetailOutDTO update(@PathVariable Long id, @RequestBody MovieInDTO movie) {
        return movieService.update(id, movie);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieDetailOutDTO partialUpdate(@PathVariable Long id, @RequestBody MovieInDTO movie) {
        return movieService.partialUpdate(id, movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        movieService.delete(id);
    }

}
