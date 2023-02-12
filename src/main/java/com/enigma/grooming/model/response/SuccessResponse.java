package com.enigma.grooming.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class SuccessResponse<T> extends CommonResponse {
    T data;
//    String token;
    public SuccessResponse(String message, T data) {
        super.setCode("00");
        super.setMessage(message);
        super.setStatus(HttpStatus.OK.name());
        this.data = data;
    }

//    public SuccessResponse(String message, T data, String token) {
//        super.setCode("00");
//        super.setMessage(message);
//        super.setStatus(HttpStatus.OK.name());
//        this.data = data;
//        this.token = token;
//    }

//    public T getData() {
//        return data;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
}
