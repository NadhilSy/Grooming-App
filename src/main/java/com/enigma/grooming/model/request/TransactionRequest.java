package com.enigma.grooming.model.request;

import com.enigma.grooming.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.ToString;

public class TransactionRequest {

    //cat
    private String catName;
    private String color;
    private String gender;
    private String race;
    private String catImageUrl;
    private User user;
}
