package com.cinevault.service;

import org.springframework.stereotype.Service;
import com.cinevault.repository.MovieRepository;
import java.util.List;
import com.cinevault.entity.Genre;
import com.cinevault.entity.Movie;
import java.util.Optional;
import com.cinevault.dto.TmdbMovieResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.cinevault.repository.GenreRepository;
import com.cinevault.dto.TmdbGenreResponse;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service 
public class MovieService {
    
    private final MovieRepository movieRepository;
    private final TmdbService tmdbService;
    private final GenreRepository genreRepository;
    private final EntityManager entityManager;

    public MovieService(MovieRepository movieRepository, TmdbService tmdbService, GenreRepository genreRepository, EntityManager entityManager) {
        this.movieRepository = movieRepository;
        this.tmdbService = tmdbService;
        this.genreRepository = genreRepository;
        this.entityManager = entityManager;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Integer id) {
        return movieRepository.findById(id);
    }

    @Transactional 
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

        for (TmdbGenreResponse genreResponse : response.genres()) {
            Optional<Genre> existingGenre = genreRepository.findByNameIgnoreCase(genreResponse.name());
            if (existingGenre.isEmpty()){
                Genre newGenre = new Genre();
                newGenre.setName(genreResponse.name());
                genreRepository.save(newGenre);
                movie.getGenres().add(newGenre);
            } else {
                movie.getGenres().add(existingGenre.get());
            }
        }

        Movie savedMovie = movieRepository.save(movie);
        entityManager.flush();
        entityManager.refresh(savedMovie);
        return savedMovie;
    }

}
