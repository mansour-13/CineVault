package com.cinevault.service;

import com.cinevault.repository.MovieCreditRepository;
import com.cinevault.repository.PersonRepository;
import org.springframework.stereotype.Service;
import com.cinevault.repository.MovieRepository;
import java.util.List;
import com.cinevault.entity.Genre;
import com.cinevault.entity.Movie;
import com.cinevault.entity.MovieCredit;
import com.cinevault.entity.Person;
import com.cinevault.entity.CreditRole;
import java.util.Optional;
import com.cinevault.dto.TmdbMovieResponse;
import com.cinevault.dto.TmdbCreditResponse.TmdbCastResponse;
import com.cinevault.dto.TmdbCreditResponse.TmdbCrewResponse;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.cinevault.repository.CreditRoleRepository;
import com.cinevault.repository.GenreRepository;
import com.cinevault.dto.TmdbCreditResponse;
import com.cinevault.dto.TmdbGenreResponse;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service 
public class MovieService {
    
    private final MovieCreditRepository movieCreditRepository;
      private final MovieRepository movieRepository;
    private final TmdbService tmdbService;
    private final GenreRepository genreRepository;
    private final EntityManager entityManager;
    private final PersonService personService;
    private final CreditRoleRepository creditRoleRepository;

    public MovieService(MovieRepository movieRepository, TmdbService tmdbService, GenreRepository genreRepository, EntityManager entityManager, PersonService personService, CreditRoleRepository creditRoleRepository, MovieCreditRepository movieCreditRepository) {
        this.movieRepository = movieRepository;
        this.tmdbService = tmdbService;
        this.genreRepository = genreRepository;
        this.entityManager = entityManager;
        this.personService = personService;
        this.creditRoleRepository = creditRoleRepository;
        this.movieCreditRepository = movieCreditRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Integer id) {
        return movieRepository.findById(id);
    }

    @Transactional 
    public Movie importMovieFromTmdb(Integer tmdbId) {

        String externalId = tmdbId.toString();
        String externalSource = "TMDB";

        if (movieRepository.existsByExternalIdAndExternalSource(externalId, externalSource)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Movie already exists.");
        }

        TmdbMovieResponse response = tmdbService.getMovieById(tmdbId);

        Movie movie = new Movie();

        movie.setTitle(response.title());
        movie.setOriginalTitle(response.originalTitle());
        movie.setOverview(response.overview());
        movie.setReleaseDate(response.releaseDate());
        movie.setRuntimeMinutes(response.runtimeMinutes());
        movie.setExternalId(externalId);
        movie.setExternalSource(externalSource);

        for (TmdbGenreResponse genreResponse : response.genres()) {
            Optional<Genre> existingGenre = genreRepository.findByNameIgnoreCase(genreResponse.name());
            if (existingGenre.isEmpty()){
                Genre newGenre = new Genre();
                newGenre.setName(genreResponse.name());
                genreRepository.save(newGenre);
                movie.getGenres().add(newGenre);
            } else {
                movie.getGenres().add(existingGenre.get());
            }
        }

        Movie savedMovie = movieRepository.save(movie);

        TmdbCreditResponse credits = tmdbService.getMovieCredits(tmdbId);
        CreditRole actorRole = creditRoleRepository.findByNameIgnoreCase("ACTOR").orElseThrow();
        for (TmdbCastResponse castResponse : credits.cast()) {
            Person person = personService.findOrCreatePerson(castResponse.externalId());
            MovieCredit movieCredit = new MovieCredit();
            movieCredit.setMovie(savedMovie);
            movieCredit.setPerson(person);
            movieCredit.setRole(actorRole);
            movieCredit.setCharacterName(castResponse.characterName());
            movieCredit.setCreditOrder(castResponse.creditOrder());
            movieCreditRepository.save(movieCredit);
        }

        for (TmdbCrewResponse crewResponse : credits.crew()) {
            Optional<CreditRole> crewRole = creditRoleRepository.findByNameIgnoreCase(crewResponse.job());         
            if (crewRole.isPresent()) {
                Person person = personService.findOrCreatePerson(crewResponse.externalId());
                MovieCredit movieCredit = new MovieCredit();
                movieCredit.setMovie(savedMovie);
                movieCredit.setPerson(person);
                movieCredit.setRole(crewRole.get());
                movieCreditRepository.save(movieCredit);
            }
        }
        
        entityManager.flush();
        entityManager.refresh(savedMovie);
        return savedMovie;
    }

}
