package com.cinevault.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cinevault.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
        public boolean existsByExternalIdAndExternalSource(String externalId, String externalSource);
        public Optional<Movie> findByExternalIdAndExternalSource(String externalId, String externalSource); 

}
