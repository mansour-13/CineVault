package com.cinevault.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cinevault.service.TmdbService;
import com.cinevault.dto.TmdbSearchResponse;
import org.springframework.web.bind.annotation.PathVariable;
import com.cinevault.dto.TmdbMovieResponse;
import com.cinevault.entity.Movie;
import org.springframework.web.bind.annotation.PostMapping;
import com.cinevault.service.MovieService;

@RestController
@RequestMapping ("/api/tmdb")
public class TmdbController {
    
    private final TmdbService tmdbService;
    private final MovieService movieService;

    public TmdbController(TmdbService tmdbService, MovieService movieService) {
        this.tmdbService = tmdbService;
        this.movieService = movieService;
    }

    @GetMapping ("/search")
    public TmdbSearchResponse searchMovie(@RequestParam String title) {
        return tmdbService.searchMovie(title);
    }

    @GetMapping ("/movies/{id}")
    public TmdbMovieResponse getMovieById(@PathVariable Integer id) {
        return tmdbService.getMovieById(id);
    }

    @PostMapping("/import/{tmdbId}")
    public Movie importMovieFromTmdb(@PathVariable Integer tmdbId) {
        return movieService.importMovieFromTmdb(tmdbId);
    }
}
