package com.orange.talents.users.controller;

import com.orange.talents.users.dto.AddressRequestDTO;
import com.orange.talents.users.dto.AddressResponseDTO;
import com.orange.talents.users.dto.UserRequestDTO;
import com.orange.talents.users.dto.UserResponseDTO;
import com.orange.talents.users.model.Address;
import com.orange.talents.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userDTORequest) {
        URI location = URI.create("/users");
        return ResponseEntity.created(location).body(this.userService.saveUser(userDTORequest));
    }

    @PostMapping(value = "/{id}/address")
    public ResponseEntity<AddressResponseDTO> createAddress(@PathVariable Long id, @RequestBody AddressRequestDTO addressRequestDTO) {
        URI location = URI.create(String.format("/%s/address", id));
        return ResponseEntity.created(location).body(this.userService.saveAddress(id, addressRequestDTO));
    }

    @GetMapping(value = "/{id}")
    public List<Address> getUserById(@PathVariable Long id) {
        return this.userService.listAddresses(id);
    }
}
