package com.cinevault.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cinevault.dto.UserMovieRequest;
import com.cinevault.dto.UserMovieResponse;
import com.cinevault.entity.Movie;
import com.cinevault.entity.UserMovie;
import com.cinevault.repository.AppUserRepository;
import com.cinevault.repository.MovieRepository;
import com.cinevault.repository.UserMovieRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service 
public class UserMovieService {
    
    private final UserMovieRepository userMovieRepository;
    private final AppUserRepository appUserRepository;
    private final MovieRepository movieRepository;
    private final MovieService movieService;
    private final EntityManager entityManager;

    public UserMovieService(UserMovieRepository userMovieRepository, AppUserRepository appUserRepository, MovieRepository movieRepository,
                            MovieService movieService, EntityManager entityManager) {
        this.userMovieRepository = userMovieRepository;
        this.appUserRepository = appUserRepository;
        this.movieRepository = movieRepository;
        this.movieService = movieService;
        this.entityManager = entityManager;
    }

    @Transactional 
    public UserMovieResponse importUserMovie (UserMovieRequest request) {

        Integer userId = 1;
        String externalId = request.tmdbId().toString();
        String externalSource = "TMDB";
        Integer movieId;
    
        Optional<Movie> movie = movieRepository.findByExternalIdAndExternalSource(externalId,externalSource);
        
        if (movie.isPresent()) {
            movieId = movie.get().getId();
        } else {
            Movie importedMovie = movieService.importMovieFromTmdb(request.tmdbId());
            movieId = importedMovie.getId();
        }

        if (userMovieRepository.existsByAppUser_IdAndMovie_Id(userId, movieId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Movie already exists in your watchlist.");
        }

        UserMovie userMovie = new UserMovie();
        
        userMovie.setMovie(movieRepository.getReferenceById(movieId));
        userMovie.setAppUser(appUserRepository.getReferenceById(userId));
        userMovie.setStatus(request.status());
        userMovie.setPriority(request.priority());
        userMovie.setPersonalRating(request.personalRating());
        userMovie.setPersonalReview(request.personalReview());

        userMovieRepository.save(userMovie);
        entityManager.flush();
        entityManager.refresh(userMovie);
        return new UserMovieResponse(
            userMovie.getId(),
            userMovie.getMovie().getId(),
            userMovie.getStatus(),
            userMovie.getPriority(),
            userMovie.getPersonalRating(),
            userMovie.getPersonalReview(),
            userMovie.getAddedAt()
        );
    }
    
    public List<UserMovieResponse> getUserMovieByUserId(Integer userId) {

        List<UserMovie> userMovies =  userMovieRepository.findByAppUser_Id(userId);

    return userMovies.stream()
        .map(userMovie -> new UserMovieResponse(
            userMovie.getId(),
            userMovie.getMovie().getId(),
            userMovie.getStatus(),
            userMovie.getPriority(),
            userMovie.getPersonalRating(),
            userMovie.getPersonalReview(),
            userMovie.getAddedAt()
        ))
        .toList();
    }
}
