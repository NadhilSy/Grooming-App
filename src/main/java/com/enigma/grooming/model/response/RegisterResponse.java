package com.enigma.grooming.model.response;

import com.enigma.grooming.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
public class RegisterResponse extends CommonResponse{
    @Getter @Setter
    class Data {
        String name;
        String email;
        String imageUrl;
        public Data(String name, String email, String imageUrl) {
            this.name = name;
            this.email = email;
            this.imageUrl = imageUrl;
        }
    }
    Data data;
    public RegisterResponse(User data) {
        super();
        super.setCode("00");
        super.setStatus("Success");
        super.setMessage("Success make an account");
        this.data = new Data(data.getName(),data.getSystemAuth().getEmail(),"https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");
    }
}
