package com.enigma.grooming.repository;

import com.enigma.grooming.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
