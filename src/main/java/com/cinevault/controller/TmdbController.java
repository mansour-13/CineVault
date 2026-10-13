package com.cinevault.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cinevault.service.TmdbService;
import com.cinevault.dto.TmdbSearchResponse;
import org.springframework.web.bind.annotation.PathVariable;
import com.cinevault.dto.TmdbMovieResponse;
import com.cinevault.dto.TmdbCreditResponse;

@RestController
@RequestMapping ("/api/tmdb")
public class TmdbController {
    
    private final TmdbService tmdbService;

    public TmdbController(TmdbService tmdbService) {
        this.tmdbService = tmdbService;
    }

    @GetMapping ("/search")
    public TmdbSearchResponse searchMovie(@RequestParam String title) {
        return tmdbService.searchMovie(title);
    }

    @GetMapping ("/movies/{id}")
    public TmdbMovieResponse getMovieById(@PathVariable Integer id) {
        return tmdbService.getMovieById(id);
    }

    @GetMapping ("/movies/{id}/credits")
    public TmdbCreditResponse getMovieCredits(@PathVariable Integer id) {
        return tmdbService.getMovieCredits(id);
    }

}
