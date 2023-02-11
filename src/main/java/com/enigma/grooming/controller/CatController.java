package com.enigma.grooming.controller;

import com.enigma.grooming.model.Cat;
import com.enigma.grooming.model.request.CatRequest;
import com.enigma.grooming.model.response.PagingResponse;
import com.enigma.grooming.model.response.SuccessResponse;
import com.enigma.grooming.service.CatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cats")
public class CatController {

    @Autowired
    private CatService catService;

    @PostMapping
    public ResponseEntity createCat(@Valid @RequestBody CatRequest catRequest, @RequestHeader("Authorization") String token) {
        catRequest.setToken(token.split(" ")[1]);
        Cat result = catService.create(catRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("", result));
    }

    //for admin
    @GetMapping
    public ResponseEntity getAllCats(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(defaultValue = "catName") String sortBy
    ) {
        Page<Cat> cats = catService.getList(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get cats", cats));
    }

    //for user
    @GetMapping("/user")
    public ResponseEntity getAllCatsByUser(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(defaultValue = "catName") String sortBy,
            @RequestHeader(name = "Authorization") String token
    ) {
        Page<Cat> cats = catService.getListByUser(page, size, direction, sortBy, token.split(" ")[1]);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Succes get cats", cats));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@Valid @RequestBody CatRequest catRequest, @PathVariable("id") String id){
        catService.update(catRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success update Cat", catRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id){
        catService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delete cats", null));
    }
}
