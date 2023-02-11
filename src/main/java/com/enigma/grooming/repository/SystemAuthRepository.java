package com.enigma.grooming.repository;

import com.enigma.grooming.model.SystemAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemAuthRepository extends JpaRepository<SystemAuth,String > {
    Optional<SystemAuth> findByEmail(String email);
}
