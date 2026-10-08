package com.cinevault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cinevault.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    
}
