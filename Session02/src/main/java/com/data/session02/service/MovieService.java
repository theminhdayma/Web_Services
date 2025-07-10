package com.data.session02.service;

import com.data.session02.entity.Movie;
import com.data.session02.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService implements AppService<Movie, Long> {

    private final MovieRepository movieRepository;

    @Override
    public Movie save(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public Movie update(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }


    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

}
