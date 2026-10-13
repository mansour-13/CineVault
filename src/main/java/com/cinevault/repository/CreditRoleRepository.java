package com.cinevault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cinevault.entity.CreditRole;
import java.util.Optional;

public interface CreditRoleRepository extends JpaRepository<CreditRole, Integer> {
    public Optional<CreditRole> findByNameIgnoreCase(String name);    
}
