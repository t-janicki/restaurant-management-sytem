package com.restaurant.management.repository;

import com.restaurant.management.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findCartByCustomerPhoneNumberAndIsOpenTrue(Long phoneNumber);

}
