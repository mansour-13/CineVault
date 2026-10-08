package com.cinevault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cinevault.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    
}
