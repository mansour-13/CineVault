package com.cinevault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cinevault.entity.Person;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    public boolean existsByExternalIdAndExternalSource(String externalId, String externalSource);
    public Optional<Person> findByExternalIdAndExternalSource(String externalId, String externalSource);
}
