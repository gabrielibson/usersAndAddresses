package com.orange.talents.users.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String birthDate;
    private List<AddressResponseDTO> addresses;
}
