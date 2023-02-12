package com.enigma.grooming.model.response;

import com.enigma.grooming.model.User;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserResponse extends CommonResponse {
    List<UserEncapsulated> data;

    public UserResponse(List<User> users) {
        super();
        super.setCode("00");
        super.setStatus("OK");
        super.setMessage("Success get data");
        this.data = users.stream().map(s -> new UserEncapsulated(s.getUserId(), s.getName(), s.getAddress(), s.getPhoneNumber(),s.getSystemAuth().getRole())).collect(Collectors.toList());
    }
}
