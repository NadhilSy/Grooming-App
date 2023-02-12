package com.enigma.grooming.model.response;

import com.enigma.grooming.model.User;

public class UserByIdResponse extends SuccessResponse<UserEncapsulated> {

    public UserByIdResponse(User user) {
        super();
        super.setStatus("OK");
        super.setCode("00");
        super.setMessage("Success get data");
        super.setData(new UserEncapsulated(user.getUserId(), user.getName(), user.getAddress(), user.getPhoneNumber(), user.getSystemAuth().getRole()));
    }
}
