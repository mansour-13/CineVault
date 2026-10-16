package com.cinevault.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.cinevault.dto.TmdbPersonResponse;
import com.cinevault.entity.Person;
import com.cinevault.repository.PersonRepository;

@Service 
public class PersonService {
    
    private final PersonRepository personRepository;
    private final TmdbService tmdbService;

    public PersonService(PersonRepository personRepository, TmdbService tmdbService) {
        this.personRepository = personRepository;
        this.tmdbService = tmdbService;
    }

    public Person findOrCreatePerson(Integer tmdbId) {
        
        String externalId = tmdbId.toString();
        String externalSource = "TMDB";

        Optional<Person> existingPerson = personRepository.findByExternalIdAndExternalSource(externalId, externalSource);
            
        if (existingPerson.isPresent()) {
            return existingPerson.get();
        } else {
            Person newPerson = new Person();
            TmdbPersonResponse tmdbPersonResponse = tmdbService.getPersonById(tmdbId);
            newPerson.setExternalId(externalId);
            newPerson.setExternalSource(externalSource);
            newPerson.setName(tmdbPersonResponse.name());
            newPerson.setBiography(tmdbPersonResponse.biography());
            newPerson.setPlaceOfBirth(tmdbPersonResponse.placeOfBirth());
            newPerson.setDateOfBirth(tmdbPersonResponse.dateOfBirth());
            return personRepository.save(newPerson);
        }
    }
}
