package com.orange.talents.users.mappers;

import com.orange.talents.users.client.Endereco;
import com.orange.talents.users.dto.AddressRequestDTO;
import com.orange.talents.users.dto.AddressResponseDTO;
import com.orange.talents.users.model.Address;

public class AddressMapper {
    public static AddressResponseDTO addressToAddressResponseDTO(Address address) {
        return AddressResponseDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .neighborhood(address.getNeighborhood())
                .city(address.getCity())
                .state(address.getState())
                .cep(address.getCep())
                .build();
    }

    public static Address addressRequestDTOtoAddress(AddressRequestDTO addressRequestDTO, Endereco addressViaCep) {
        return Address.builder()
                .street(addressViaCep.getLogradouro())
                .number(addressRequestDTO.getNumber())
                .complement(addressViaCep.getComplemento())
                .neighborhood(addressViaCep.getBairro())
                .city(addressViaCep.getLocalidade())
                .state(addressViaCep.getUf())
                .cep(addressViaCep.getCep())
                .build();
    }
}
