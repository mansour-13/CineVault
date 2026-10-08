package com.cinevault.service;

import org.springframework.stereotype.Service;
import com.cinevault.repository.GenreRepository;
import java.util.List;
import com.cinevault.entity.Genre;
import java.util.Optional;

@Service 
public class GenreService {
    
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Optional<Genre> getGenreById(Integer id) {
        return genreRepository.findById(id);
    }
}
