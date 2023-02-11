package com.enigma.grooming.service;

import com.enigma.grooming.model.Cat;
import com.enigma.grooming.model.request.CatListRequest;
import com.enigma.grooming.model.request.CatRequest;
import org.springframework.data.domain.Page;

public interface CatService {
    Cat create(CatRequest catRequest);
    Page<Cat> getList(Integer page, Integer size, String direction, String sortBy);
    Page<Cat> getListByUser(Integer page, Integer size, String direction, String sortBy, CatListRequest catListRequest);

    void update(CatRequest catRequest, String id);
    void delete(String id);

    Cat get(String id);

}
