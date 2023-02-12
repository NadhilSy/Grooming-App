package com.enigma.grooming.model.response;

import com.enigma.grooming.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
public class LoginResponse extends CommonResponse {
    @Getter
    @Setter
    private class Data {
        String name;
        String role;
        String imageUrl;

        public Data(String name, String role, String imageUrl) {
            this.name = name;
            this.role = role;
            this.imageUrl = imageUrl;
        }
    }

    private Data data;
    private String token;

    public LoginResponse(String name, String role, String imageUrl, String token) {
        super();
        super.setStatus("Success");
        super.setMessage("Login success");
        super.setCode("00");
        this.data = new Data(name, role, imageUrl);
        this.token = token;

    }
}
