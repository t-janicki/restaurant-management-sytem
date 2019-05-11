package com.restaurant.management.repository;

import com.restaurant.management.domain.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Override
    Page<Cart> findAll(Pageable pageable);

    Page<Cart> findAllByRestaurantInfoId(Long restaurantId, Pageable pageable);

    Page<Cart> findByCustomerId(Long id, Pageable pageable);

    Page<Cart> findByCustomerIdAndRestaurantInfoId(Long customerId, Long restaurantId, Pageable pageable);

    Optional<Cart> findByIdAndRestaurantInfoIdAndCustomerId(Long id, Long customerId, Long restaurantId);

    Optional<Cart> findByIdAndRestaurantInfoId(Long cartId, Long restaurantId);

}
