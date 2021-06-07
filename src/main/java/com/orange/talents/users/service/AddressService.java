package com.orange.talents.users.service;

import com.orange.talents.users.client.Endereco;
import com.orange.talents.users.client.ViaCepClient;
import com.orange.talents.users.dto.AddressRequestDTO;
import com.orange.talents.users.dto.AddressResponseDTO;
import com.orange.talents.users.exceptions.ViaCEPException;
import com.orange.talents.users.mappers.AddressMapper;
import com.orange.talents.users.model.Address;
import com.orange.talents.users.model.User;
import com.orange.talents.users.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final ViaCepClient viaCepClient;

    AddressService(AddressRepository addressRepository, ViaCepClient viaCepClient) {
        this.addressRepository = addressRepository;
        this.viaCepClient = viaCepClient;
    }

    public AddressResponseDTO saveAddress(User user, AddressRequestDTO addressRequestDTO) {
        Address address = AddressMapper.addressRequestDTOtoAddress(
                addressRequestDTO, findAddressByCep(addressRequestDTO.getZipCode()));
        address.setUser(user);
        return AddressMapper.addressToAddressResponseDTO(this.addressRepository.save(address));
    }

    private Endereco findAddressByCep(String zipCode) {
        Endereco endereco;
        try{
            endereco = this.viaCepClient.findByCep(zipCode);
        }catch (Exception e) {
            throw new ViaCEPException();
        }
        return endereco;
    }
}
