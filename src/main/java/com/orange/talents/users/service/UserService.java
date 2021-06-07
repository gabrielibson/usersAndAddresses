package com.orange.talents.users.service;

import com.orange.talents.users.dto.AddressRequestDTO;
import com.orange.talents.users.dto.AddressResponseDTO;
import com.orange.talents.users.dto.UserRequestDTO;
import com.orange.talents.users.dto.UserResponseDTO;
import com.orange.talents.users.exceptions.ElementNotFoundException;
import com.orange.talents.users.exceptions.IntegrityViolationException;
import com.orange.talents.users.mappers.UserMapper;
import com.orange.talents.users.model.User;
import com.orange.talents.users.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AddressService addressService;

    public UserService(UserRepository userRepository, AddressService addressService) {
        this.userRepository = userRepository;
        this.addressService = addressService;
    }

    public UserResponseDTO findUserById(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ElementNotFoundException(userId));
        return UserMapper.userToUserDto(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public AddressResponseDTO saveAddress(Long id, AddressRequestDTO addressRequestDTO) {
        User user = this.userRepository.findById(id).orElseThrow(() ->  new ElementNotFoundException(id));
        return this.addressService.saveAddress(user, addressRequestDTO);
    }

    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        User user;
        try {
            user = this.userRepository.save(UserMapper.userDtoToUser(userRequestDTO));
        }catch (DataIntegrityViolationException dataIntegrityViolationException) {
            if(Objects.requireNonNull(dataIntegrityViolationException.getMessage()).contains("USER(CPF)")) {
                throw new IntegrityViolationException("CPF");
            }else {
                throw new IntegrityViolationException("E-MAIL");
            }
        }
        return UserMapper.userToUserDto(user);
    }
}
