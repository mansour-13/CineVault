package com.cinevault.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.cinevault.dto.TmdbMovieResponse;
import com.cinevault.dto.TmdbSearchResponse;

@Service 
public class TmdbService {
    
    @Value("${tmdb.api.key}")
    private String apiKey;
    private final RestClient restClient;

    public TmdbService() {
        this.restClient = RestClient.builder()
            .baseUrl("https://api.themoviedb.org/3")
            .build();
    }

    public TmdbMovieResponse getMovieById(Integer id) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{id}")
                        .queryParam("api_key", apiKey)
                        .queryParam("language", "en-US")
                        .build(id))
                .retrieve()
                .body(TmdbMovieResponse.class);
    }

    public TmdbSearchResponse searchMovie(String title) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("language", "en-US")
                        .queryParam("query", title)
                        .build())
                .retrieve()
                .body(TmdbSearchResponse.class);
    }
}
