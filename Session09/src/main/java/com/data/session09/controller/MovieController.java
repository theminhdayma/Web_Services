package com.data.session09.controller;

import com.data.session09.model.dto.reponse.DataResponse;
import com.data.session09.model.dto.request.MovieDto;
import com.data.session09.model.entity.Movie;
import com.data.session09.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Movie>>> getMovies() {
        List<Movie> movies = movieService.getMovies();
        return new ResponseEntity<>(new DataResponse<>(movies, HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResponse<Movie>> postMovie(@Valid @ModelAttribute MovieDto movieDto) {
        Movie created = movieService.insertMovie(movieDto);
        return new ResponseEntity<>(new DataResponse<>(created, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Movie>> getMovie(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        return new ResponseEntity<>(new DataResponse<>(movie, HttpStatus.OK), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Movie>> updateMovie(@PathVariable Long id, @Valid @ModelAttribute MovieDto movieDto) {
        Movie updatedMovie = movieService.updateMovie(id, movieDto);
        return new ResponseEntity<>(new DataResponse<>(updatedMovie, HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Void>> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(new DataResponse<>(null, HttpStatus.NO_CONTENT), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search-logs")
    public ResponseEntity<DataResponse<List<Movie>>> searchMovies(@RequestParam String title) {
        List<Movie> movies = movieService.searchMoviesByTitle(title);
        return new ResponseEntity<>(new DataResponse<>(movies, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/suggestions")
    public ResponseEntity<DataResponse<List<Movie>>> getMovieSuggestions(@RequestParam String title) {
        List<Movie> suggestions = movieService.getMovieSuggestions(title);
        return new ResponseEntity<>(new DataResponse<>(suggestions, HttpStatus.OK), HttpStatus.OK);
    }

}
