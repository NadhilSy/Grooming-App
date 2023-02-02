package com.enigma.grooming.repository;

import com.enigma.grooming.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, String > {
}
