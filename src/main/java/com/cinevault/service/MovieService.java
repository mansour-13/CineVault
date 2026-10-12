package com.cinevault.service;

import org.springframework.stereotype.Service;
import com.cinevault.repository.MovieRepository;
import java.util.List;
import com.cinevault.entity.Movie;
import java.util.Optional;
import com.cinevault.dto.TmdbMovieResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service 
public class MovieService {
    
    private final MovieRepository movieRepository;
    private final TmdbService tmdbService;

    public MovieService(MovieRepository movieRepository, TmdbService tmdbService) {
        this.movieRepository = movieRepository;
        this.tmdbService = tmdbService;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Integer id) {
        return movieRepository.findById(id);
    }

    public Movie importMovieFromTmdb(Integer tmdbId) {

        String externalId = tmdbId.toString();
        String externalSource = "TMDB";

        if (movieRepository.existsByExternalIdAndExternalSource(externalId, externalSource)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Movie already exists.");
        }

        TmdbMovieResponse response = tmdbService.getMovieById(tmdbId);

        Movie movie = new Movie();

        movie.setTitle(response.title());
        movie.setOriginalTitle(response.originalTitle());
        movie.setOverview(response.overview());
        movie.setReleaseDate(response.releaseDate());
        movie.setRuntimeMinutes(response.runtimeMinutes());
        movie.setExternalId(externalId);
        movie.setExternalSource(externalSource);

        return movieRepository.save(movie);
    }

}
