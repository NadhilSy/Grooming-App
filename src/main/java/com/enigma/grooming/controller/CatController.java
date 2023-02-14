package com.enigma.grooming.controller;

import com.enigma.grooming.model.Cat;
import com.enigma.grooming.model.SystemAuth;
import com.enigma.grooming.model.User;
import com.enigma.grooming.model.request.CatListRequest;
import com.enigma.grooming.model.request.CatRequest;
import com.enigma.grooming.model.response.*;
import com.enigma.grooming.service.CatService;
import com.enigma.grooming.service.SystemAuthService;
import com.enigma.grooming.service.UserService;
import com.enigma.grooming.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cats")
public class CatController {
    private CatService catService;
    JwtUtil jwtUtil;
    SystemAuthService systemAuthService;
    UserService userService;

    @Autowired
    public CatController(CatService catService, JwtUtil jwtUtil, SystemAuthService systemAuthService, UserService userService) {
        this.catService = catService;
        this.jwtUtil = jwtUtil;
        this.systemAuthService = systemAuthService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity createCat(@Valid @RequestBody CatRequest catRequest, @RequestHeader("Authorization") String token) {
        String mail = jwtUtil.getMail(token.split(" ")[1]);
        SystemAuth existingSysAuth = systemAuthService.findByEmail(mail);
        Optional<User> user = userService.findBySystemAuth(existingSysAuth);
        CatListRequest catListRequest = new CatListRequest();
        catListRequest.setUserInfo(user.get());
        catRequest.setUserInfo(user.get());
        Cat result = catService.create(catRequest);
        CatCreateResponse catCreateResponse = new CatCreateResponse(result, result.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("", catCreateResponse));
    }
    @GetMapping
    public ResponseEntity<CommonResponse> getAllCatsByUser(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(defaultValue = "catName") String sortBy,
            @RequestHeader(name = "Authorization") String token
    ) {
        String mail = jwtUtil.getMail(token.split(" ")[1]);
        SystemAuth existingSysAuth = systemAuthService.findByEmail(mail);
        Optional<User> user = userService.findBySystemAuth(existingSysAuth);
        Page<Cat> cats = catService.getListByUser(page, size, direction, sortBy, user.get());
        return ResponseEntity.status(HttpStatus.OK).body(new CatResponse(cats.getContent(),user.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@Valid @RequestBody CatRequest catRequest, @PathVariable("id") String id) {
        catService.update(catRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success update Cat", catRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id) {
        catService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delete cats", null));
    }
}
