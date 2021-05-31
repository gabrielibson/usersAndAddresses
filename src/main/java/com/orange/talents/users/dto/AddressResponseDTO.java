package com.orange.talents.users.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddressResponseDTO {
    private final Long id;
    private final String street;
    private final Integer number;
    private final String complement;
    private final String neighborhood;
    private final String city;
    private final String state;
    private final String cep;
}
