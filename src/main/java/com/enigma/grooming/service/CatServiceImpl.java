package com.enigma.grooming.service;

import com.enigma.grooming.exception.EntityExistException;
import com.enigma.grooming.exception.NotFoundException;
import com.enigma.grooming.model.Cat;
import com.enigma.grooming.model.SystemAuth;
import com.enigma.grooming.model.User;
import com.enigma.grooming.model.request.CatRequest;
import com.enigma.grooming.repository.CatRepository;
import com.enigma.grooming.repository.UserRepository;
import com.enigma.grooming.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CatServiceImpl implements CatService {

    private CatRepository catRepository;
    private ModelMapper modelMapper;
    private JwtUtil jwtUtil;
    private SystemAuthService systemAuthService;
    private UserRepository userRepository;

    @Autowired
    public CatServiceImpl(CatRepository catRepository, ModelMapper modelMapper, JwtUtil jwtUtil, SystemAuthService systemAuthService, UserRepository userRepository) {
        this.catRepository = catRepository;
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
        this.systemAuthService = systemAuthService;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Cat create(CatRequest catRequest) {
        try {
            String mail = jwtUtil.getMail(catRequest.getToken());
//            if (!systemAuthService.findByEmail(mail).equals(mail)){
//                throw new NotFoundException();
//            }

            SystemAuth existingSystemAuth = systemAuthService.findByEmail(mail);
//                    catRepository.findByMailEquals(mail);
            Optional<User> user = userRepository.findBySystemAuth(existingSystemAuth);
            User newUser = user.get();
            Cat cat = modelMapper.map(catRequest, Cat.class);
            cat.setUser(newUser);

//            cat.setUser();
//            User tes  = cat.getUser().getSystemAuth().setEmail(existingSystemAuth.getEmail());
            return catRepository.save(cat);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public Page<Cat> getList(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<Cat> result = catRepository.findAll(pageable);
        return result;
    }

    @Override
    public Page<Cat> getListByUser(Integer page, Integer size, String direction, String sortBy, String token) {
        String mail = jwtUtil.getMail(token);
        System.out.println(mail);
        SystemAuth existingSysAuth = systemAuthService.findByEmail(mail);
        Optional<User> userOpt = userRepository.findBySystemAuth(existingSysAuth);
        User newUser = userOpt.get();
        Integer id = newUser.getUserId();
        System.out.println(id);
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<Cat> result = catRepository.findAllByUser(newUser,pageable);
        System.out.println(result);
        return result;
    }

    @Override
    public void update(CatRequest catRequest, String id) {
        try {
            Cat existingCat = get(id);
            existingCat.setCatName(catRequest.getCatName());
            existingCat.setCatImageUrl(catRequest.getCatImageUrl());
            existingCat.setRace(catRequest.getRace());
            existingCat.setGender(catRequest.getGender());
            existingCat.setColor(catRequest.getColor());
            catRepository.save(existingCat);
//            existingCat.setUser(catRequest);
        } catch (NotFoundException e) {
            throw new NotFoundException("update failed because id not found");
        }

    }

    @Override
    public void delete(String id) {
        try {
            Cat existingCat = get(id);
            catRepository.delete(existingCat);
        } catch (NotFoundException e) {
            throw new NotFoundException("Delete failed because Id is not found");
        }

    }

    @Override
    public Cat get(String id) {
        Optional<Cat> cat = catRepository.findById(id);
        if (cat.isEmpty()) {
            throw new NotFoundException("Packet Not Found");
        }
        return cat.get();
    }


}
