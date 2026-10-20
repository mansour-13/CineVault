package com.cinevault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cinevault.entity.UserMovie;

public interface UserMovieRepository extends JpaRepository<UserMovie, Integer> {
    public boolean existsByAppUser_IdAndMovie_Id(Integer userId, Integer movieId);
}
