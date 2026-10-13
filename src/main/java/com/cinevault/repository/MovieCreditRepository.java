package com.cinevault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cinevault.entity.MovieCredit;

public interface MovieCreditRepository extends JpaRepository<MovieCredit, Integer> {
    
}
