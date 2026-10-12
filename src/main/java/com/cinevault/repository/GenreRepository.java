package com.cinevault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cinevault.entity.Genre;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
        public Optional<Genre> findByNameIgnoreCase(String name);
}
