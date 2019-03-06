package com.restaurant.management.repository;

import com.restaurant.management.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByNameAndLastname(String name, String lastname);

    Optional<Customer> findByPhoneNumber(Long phoneNumber);

    Boolean existsByPhoneNumber(Long phoneNumber);

    Boolean existsByEmail(String email);

    Optional<Customer> findByPhoneNumberOrEmail(Long phoneNumber, String email);

    Optional<Customer> findById(Long id);

    void deleteById(Long id);

}
