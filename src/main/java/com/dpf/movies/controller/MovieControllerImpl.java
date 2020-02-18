package com.dpf.movies.controller;

import com.dpf.movies.common.base.BaseController;
import com.dpf.movies.dto.MovieDetailOutDTO;
import com.dpf.movies.dto.MovieInDTO;
import com.dpf.movies.dto.MovieOutDTO;
import com.dpf.movies.service.MovieService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${v1API}/${resource.movies}")
public class MovieControllerImpl extends BaseController implements MovieController {

    @Inject
    private MovieService movieService;

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
