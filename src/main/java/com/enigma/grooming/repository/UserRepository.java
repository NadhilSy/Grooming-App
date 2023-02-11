package com.enigma.grooming.repository;

import com.enigma.grooming.model.Auth;
import com.enigma.grooming.model.SystemAuth;
import com.enigma.grooming.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByName(String name);
    Optional<User> findBySystemAuth(SystemAuth systemAuth);
    Optional<User> findByAuth(Auth auth);
}
