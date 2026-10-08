package com.cinevault.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cinevault.entity.Genre;
import com.cinevault.service.GenreService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;


@RestController 
@RequestMapping ("/api/genres")
public class GenreController {
    
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping 
    public List<Genre> getAllGeres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Integer id) {
        return genreService.getGenreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
}
