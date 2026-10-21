package com.cinevault.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinevault.entity.UserMovie;
import java.util.List;

public interface UserMovieRepository extends JpaRepository<UserMovie, Integer> {

    public boolean existsByAppUser_IdAndMovie_Id(Integer userId, Integer movieId);
    
    public List<UserMovie> findByAppUser_Id(Integer userid);
}
