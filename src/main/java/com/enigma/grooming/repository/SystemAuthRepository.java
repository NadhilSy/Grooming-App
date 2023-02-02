package com.enigma.grooming.repository;

import com.enigma.grooming.model.SystemAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemAuthRepository extends JpaRepository<SystemAuth,String > {
}
