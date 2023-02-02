package com.enigma.grooming.service;

import com.enigma.grooming.exception.NotFoundException;
import com.enigma.grooming.exception.UnauthorizedException;
import com.enigma.grooming.model.Auth;
import com.enigma.grooming.model.User;
import com.enigma.grooming.model.request.GoogleAccountRequest;
import com.enigma.grooming.model.request.RegistrationGoogleRequest;
import com.enigma.grooming.repository.AuthRepository;
import com.enigma.grooming.util.JwtUtil;
import com.enigma.grooming.util.Role;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{
    AuthRepository authRepository;
    ModelMapper modelMapper;

    UserService userService;

    JwtUtil jwtUtil;

    @Autowired
    public AuthServiceImpl(AuthRepository authRepository, ModelMapper modelMapper, UserService userService, JwtUtil jwtUtil) {
        this.authRepository = authRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    @Override
    public String register(GoogleAccountRequest googleAccountRequest) {
        try {


//            String token = jwtUtil.generateToken(user.getAuth().getEmail());
            return null;

        }catch (DataIntegrityViolationException e){
            throw new EntityExistsException();
        }
    }

    @Override
    public String login(GoogleAccountRequest googleAccountRequest) {
        try {
            Optional<Auth> auth = authRepository.findById(googleAccountRequest.getEmail());
            if (auth.isEmpty()){
                RegistrationGoogleRequest registrationGoogleRequest = new RegistrationGoogleRequest();
                registrationGoogleRequest.setGoogleId(googleAccountRequest.getSub());
                registrationGoogleRequest.setEmail(googleAccountRequest.getEmail());
                registrationGoogleRequest.setPhoto(googleAccountRequest.getPicture());
                registrationGoogleRequest.setName(googleAccountRequest.getName());
                registrationGoogleRequest.setRole(Role.MEMBER);
                Auth newAuth = modelMapper.map(registrationGoogleRequest, Auth.class);
                Auth authResult = authRepository.save(newAuth);

                User user = modelMapper.map(registrationGoogleRequest, User.class);
                user.setAuth(authResult);

                userService.create(user);
            }else if (!auth.get().getGoogleId().equals(googleAccountRequest.getSub())){
                RegistrationGoogleRequest registrationGoogleRequest = new RegistrationGoogleRequest();
                registrationGoogleRequest.setGoogleId(googleAccountRequest.getSub());
                registrationGoogleRequest.setEmail(googleAccountRequest.getEmail());
                registrationGoogleRequest.setPhoto(googleAccountRequest.getPicture());
                registrationGoogleRequest.setName(googleAccountRequest.getName());
                registrationGoogleRequest.setRole(Role.MEMBER);
                Auth newAuth = modelMapper.map(registrationGoogleRequest, Auth.class);
                Auth authResult = authRepository.save(newAuth);

                User user = modelMapper.map(registrationGoogleRequest, User.class);
                user.setAuth(authResult);

                userService.create(user);
            }

            String token = jwtUtil.generateToken(googleAccountRequest.getEmail());
            return token;

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


}
