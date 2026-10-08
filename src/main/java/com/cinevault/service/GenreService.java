package com.cinevault.service;

import org.springframework.stereotype.Service;
import com.cinevault.repository.GenreRepository;
import java.util.List;
import com.cinevault.entity.Genre;

@Service 
public class GenreService {
    
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}
