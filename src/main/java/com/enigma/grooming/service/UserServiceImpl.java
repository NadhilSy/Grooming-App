package com.enigma.grooming.service;

import com.enigma.grooming.model.Auth;
import com.enigma.grooming.model.SystemAuth;
import com.enigma.grooming.model.User;
import com.enigma.grooming.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(Integer.parseInt(id));
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(Integer.parseInt(id));
    }

    @Override
    public void updateById(User user) {
        userRepository.save(user);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Optional<User> findByAuth(Auth auth) {
        return userRepository.findByAuth(auth);
    }

    @Override
    public Optional<User> findBySystemAuth(SystemAuth auth) {
        return userRepository.findBySystemAuth(auth);
    }
}
