package com.orange.talents.users.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class UserRequestDTO {
    @NotBlank(message = "name must not be blank")
    private String name;
    @NotBlank(message = "email must not be blank")
    @Email(message = "e-mail should be valid")
    private String email;
    @NotBlank(message = "cpf must not be blank")
    @CPF(message = "cpf should be valid")
    private String cpf;
    @NotBlank(message = "birthDate must not be blank")
    private String birthDate;
}
