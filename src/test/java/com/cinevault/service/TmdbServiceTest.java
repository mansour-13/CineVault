package com.cinevault.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cinevault.dto.TmdbMovieResponse;
import com.cinevault.dto.TmdbSearchResponse;

@SpringBootTest
class TmdbServiceTest {

    @Autowired
    private TmdbService tmdbService;

    @Test
    void testGetMovieById() {
        TmdbMovieResponse response = tmdbService.getMovieById(157336);
        System.out.println(response);
    }

    @Test
    void testSearchMovie() {
        TmdbSearchResponse response = tmdbService.searchMovie("Interstellar");
        System.out.println(response);
    }
}
