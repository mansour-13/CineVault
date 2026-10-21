package com.cinevault.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinevault.dto.UserMovieRequest;
import com.cinevault.dto.UserMovieResponse;
import com.cinevault.entity.UserMovie;
import com.cinevault.service.UserMovieService;

@RestController 
@RequestMapping("api/user-movie")
public class UserMovieController {

    private final UserMovieService userMovieService;

    public UserMovieController(UserMovieService userMovieService) {
        this.userMovieService = userMovieService;
    }

    @PostMapping
    public UserMovieResponse importUserMovie(@RequestBody UserMovieRequest request) {
        return userMovieService.importUserMovie(request);
    }
    
    @GetMapping 
    public List<UserMovieResponse> getUserMovieByUserId() {
        Integer userId = 1;
       return userMovieService.getUserMovieByUserId(userId);
    }
}
