package com.enigma.grooming.model.request;

import com.enigma.grooming.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Setter;

@Data
public class TransactionRequest {
    @JsonIgnore
    User user;
    String catId;
    Integer packetId;
}
