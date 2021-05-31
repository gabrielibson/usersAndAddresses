package com.orange.talents.users.repository;

import com.orange.talents.users.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
