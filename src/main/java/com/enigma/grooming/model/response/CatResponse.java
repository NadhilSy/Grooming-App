package com.enigma.grooming.model.response;

import com.enigma.grooming.model.Cat;
import com.enigma.grooming.model.User;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CatResponse extends CommonResponse {
    private List<CatEncapsulated> cats;
    private UserEncapsulated owner;

    public CatResponse(List<Cat> cats, User user) {
        super();
        super.setCode("00");
        super.setMessage("Success get cats");
        super.setStatus("OK");
        this.cats = cats.stream().map(CatEncapsulated::new).collect(Collectors.toList());
        this.owner = new UserEncapsulated(user.getUserId(), user.getName(), user.getAddress(), user.getPhoneNumber(), user.getSystemAuth().getRole());
    }
}
