package com.enigma.grooming.service;

import com.enigma.grooming.model.Cat;
import com.enigma.grooming.model.User;
import com.enigma.grooming.model.request.CatRequest;
import com.enigma.grooming.model.response.CatEncapsulated;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CatService {
    Cat create(CatRequest catRequest);

    Page<Cat> getList(Integer page, Integer size, String direction, String sortBy);

    Page<Cat> getListByUser(Integer page, Integer size, String direction, String sortBy, User user);

    List<CatEncapsulated> getListByUser(User user, Boolean isDeleted);

    void update(CatRequest catRequest, String id);

    void delete(String id);

    Cat get(String id);

}
