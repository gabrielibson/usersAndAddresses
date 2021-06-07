package com.orange.talents.users.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AddressRequestDTO {
    @NotBlank
    private String street;
    @NotBlank
    private Integer number;
    @NotBlank
    private String complement;
    @NotBlank
    private String neighborhood;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    @NotBlank
    private String zipCode;
}
