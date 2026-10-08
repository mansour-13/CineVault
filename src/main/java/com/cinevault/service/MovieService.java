package com.cinevault.service;

import org.springframework.stereotype.Service;
import com.cinevault.repository.MovieRepository;
import java.util.List;
import com.cinevault.entity.Movie;
import java.util.Optional;

@Service 
public class MovieService {
    
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Integer id) {
        return movieRepository.findById(id);
    }
}
