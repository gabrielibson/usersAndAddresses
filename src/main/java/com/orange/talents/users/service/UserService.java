package com.orange.talents.users.service;

import com.orange.talents.users.client.Endereco;
import com.orange.talents.users.client.ViaCepClient;
import com.orange.talents.users.dto.AddressRequestDTO;
import com.orange.talents.users.dto.AddressResponseDTO;
import com.orange.talents.users.dto.UserRequestDTO;
import com.orange.talents.users.dto.UserResponseDTO;
import com.orange.talents.users.exceptions.ElementNotFoundException;
import com.orange.talents.users.exceptions.IntegrityViolationException;
import com.orange.talents.users.exceptions.ViaCEPException;
import com.orange.talents.users.mappers.AddressMapper;
import com.orange.talents.users.mappers.UserMapper;
import com.orange.talents.users.model.Address;
import com.orange.talents.users.model.User;
import com.orange.talents.users.repository.AddressRepository;
import com.orange.talents.users.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ViaCepClient viaCepClient;

    public UserService(UserRepository userRepository, AddressRepository addressRepository, ViaCepClient viaCepClient) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.viaCepClient = viaCepClient;
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

    public AddressResponseDTO saveAddress(Long userId, AddressRequestDTO addressRequestDTO) {
        Address address = AddressMapper.addressRequestDTOtoAddress(
                addressRequestDTO, findAddressByCep(addressRequestDTO.getCep()));
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ElementNotFoundException(userId));
        user.getAddresses().add(address);
        address.setUser(user);
        return AddressMapper.addressToAddressResponseDTO(this.addressRepository.save(address));
    }

    public UserResponseDTO findUserById(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ElementNotFoundException(userId));
        return UserMapper.userToUserDto(user);
    }

    private Endereco findAddressByCep(String cep) {
        Endereco endereco;
        try{
            endereco = this.viaCepClient.findByCep(cep);
        }catch (Exception e) {
            throw new ViaCEPException();
        }
        return endereco;
    }
}
