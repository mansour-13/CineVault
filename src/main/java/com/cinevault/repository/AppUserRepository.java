package com.cinevault.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cinevault.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    
}
