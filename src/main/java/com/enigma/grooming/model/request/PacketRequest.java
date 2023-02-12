package com.enigma.grooming.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PacketRequest {
    @NotBlank
    private String packageName;
    @Length(min = 20)
    private String description;
    @Min(value = 0)
    private String price;
    private String icon;
}
