package com.enigma.grooming.service;

import com.enigma.grooming.exception.EntityExistException;
import com.enigma.grooming.exception.NotFoundException;
import com.enigma.grooming.exception.UnauthorizedException;
import com.enigma.grooming.model.SystemAuth;
import com.enigma.grooming.model.User;
import com.enigma.grooming.model.request.LoginRequest;
import com.enigma.grooming.model.request.RegistrationRequest;
import com.enigma.grooming.repository.SystemAuthRepository;
import com.enigma.grooming.util.JwtUtil;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SystemAuthServiceImpl implements SystemAuthService {
    SystemAuthRepository systemAuthRepository;
    ModelMapper modelMapper;

    UserService userService;

    JwtUtil jwtUtil;

    @Autowired
    public SystemAuthServiceImpl(SystemAuthRepository systemAuthRepository, ModelMapper modelMapper, UserService userService, JwtUtil jwtUtil) {
        this.systemAuthRepository = systemAuthRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    @Override
    public User register(RegistrationRequest registrationRequest) {
        try {
            if (systemAuthRepository.findById(registrationRequest.getEmail()).isPresent()) {
                throw new EntityExistException("Email is exist");
            }
            SystemAuth newAuth = modelMapper.map(registrationRequest, SystemAuth.class);
            SystemAuth authResult = systemAuthRepository.save(newAuth);
            User user = modelMapper.map(registrationRequest, User.class);
            user.setSystemAuth(authResult);

            return userService.create(user);

        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException("Email is exist");
        }
    }

    @Override
    public String login(LoginRequest loginRequest) {
        try {
            Optional<SystemAuth> auth = systemAuthRepository.findByEmail(loginRequest.getEmail());
            if (auth.isEmpty()) {
                throw new NotFoundException();
            } else if (!auth.get().getPassword().equals(loginRequest.getPassword())) {
                throw new UnauthorizedException("Password not matched");
            }
            Optional<User> userInfo = userService.findBySystemAuth(auth.get());
            String token = jwtUtil.generateToken(loginRequest.getEmail() + " " + userInfo.get().getName() + " " +auth.get().getRole());
            return token;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public SystemAuth findByEmail(String email) {
        Optional<SystemAuth> result = systemAuthRepository.findByEmail(email);
        if (result.isPresent()) {
            return result.get();
        }
        throw new NotFoundException("Username with email " + email + "is not exists");
    }

//    public Optional<SystemAuth> findByEmailOpt(String email){}
}
