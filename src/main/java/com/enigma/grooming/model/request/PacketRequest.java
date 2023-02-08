package com.enigma.grooming.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacketRequest {

    private String packageName;
    private String description;
    private String price;
}
