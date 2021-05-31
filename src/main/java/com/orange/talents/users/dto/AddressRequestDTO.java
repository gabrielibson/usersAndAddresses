package com.orange.talents.users.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AddressRequestDTO {
    public AddressRequestDTO(@NotBlank String street, @NotBlank String complement, @NotBlank String neighborhood,
                             @NotBlank String city, @NotBlank String state, @NotBlank String cep) {
        this.street = street;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.cep = cep;
    }

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
    private String cep;
}
