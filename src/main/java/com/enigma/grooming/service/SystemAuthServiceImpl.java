package com.enigma.grooming.service;

import com.enigma.grooming.exception.EntityExistException;
import com.enigma.grooming.exception.NotFoundException;
import com.enigma.grooming.exception.UnauthorizedException;
import com.enigma.grooming.model.Auth;
import com.enigma.grooming.model.SystemAuth;
import com.enigma.grooming.model.User;
import com.enigma.grooming.model.request.GoogleAccountRequest;
import com.enigma.grooming.model.request.RegistrationGoogleRequest;
import com.enigma.grooming.model.request.RegistrationRequest;
import com.enigma.grooming.model.response.ErrorResponse;
import com.enigma.grooming.repository.AuthRepository;
import com.enigma.grooming.repository.SystemAuthRepository;
import com.enigma.grooming.util.JwtUtil;
import com.enigma.grooming.util.Role;
import jakarta.persistence.EntityExistsException;
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
    public String register(RegistrationRequest registrationRequest) {
        try {
            SystemAuth newAuth = modelMapper.map(registrationRequest, SystemAuth.class);
            if (!systemAuthRepository.findById(newAuth.getEmail()).isEmpty()){
                throw new EntityExistException("Email is exist");
            }
            SystemAuth authResult = systemAuthRepository.save(newAuth);
            User user = modelMapper.map(registrationRequest, User.class);
            user.setSystemAuth(authResult);

            userService.create(user);


            String token = jwtUtil.generateToken(user.getSystemAuth().getEmail());
            return token;

        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException();
        }
    }

    @Override
    public String login(RegistrationRequest registrationRequest) {
        try {
            Optional<SystemAuth> auth = systemAuthRepository.findById(registrationRequest.getEmail());
            if (auth.isEmpty()) {
                throw new NotFoundException();
            } else if (!auth.get().getPassword().equals(registrationRequest.getPassword())) {
                throw new UnauthorizedException("Password not matched");
            }

            String token = jwtUtil.generateToken(registrationRequest.getEmail());
            return token;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
