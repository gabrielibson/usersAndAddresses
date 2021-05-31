package com.orange.talents.users.mappers;

import com.orange.talents.users.dto.UserRequestDTO;
import com.orange.talents.users.dto.UserResponseDTO;
import com.orange.talents.users.exceptions.InvalidDateFormatException;
import com.orange.talents.users.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Collectors;

public class UserMapper {
    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static User userDtoToUser(UserRequestDTO userRequestDTO) {
        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse(userRequestDTO.getBirthDate(), dtf);
        }catch (DateTimeParseException dateTimeParseException) {
            throw new InvalidDateFormatException(
                    DATE_PATTERN, dateTimeParseException.getParsedString(), dateTimeParseException.getErrorIndex());
        }
        return User.builder()
                .name(userRequestDTO.getName())
                .cpf(userRequestDTO.getCpf())
                .email(userRequestDTO.getEmail())
                .birthDate(birthDate)
                .build();
    }

    public static UserResponseDTO userToUserDto(User user) {
        String birthDate = user.getBirthDate().format(dtf);
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .cpf(user.getCpf())
                .birthDate(birthDate)
                .addresses(user.getAddresses()
                        .stream()
                        .map(AddressMapper::addressToAddressResponseDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
