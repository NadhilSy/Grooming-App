package com.enigma.grooming.model.response;

import com.enigma.grooming.util.JwtUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
@Getter
public class LoginResponse extends CommonResponse{
    String data;
    String  token;
    private class Data {
        String name;
        String role;
        String imageUrl;
        String token;

        public Data(String name, String role, String imageUrl) {
            this.name = name;
            this.role = role;
            this.imageUrl = imageUrl;
        }
    }
    public LoginResponse(String resp,String token) {
        super();
        super.setStatus("Success");
        super.setMessage("Login success");
        super.setCode("00");
        this.token = token;
        this.data = resp;
//        Data data = new Data("","","");
    }
}
