package com.enigma.grooming.service;

import com.enigma.grooming.model.Auth;
import com.enigma.grooming.model.SystemAuth;
import com.enigma.grooming.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(String id);
    void deleteById(String id);
    void updateById(User user);
    Optional<User> findByName(String name);
    Optional<User> findByAuth(Auth auth);
    Optional<User> findBySystemAuth(SystemAuth auth);
    User create(User user);
}
