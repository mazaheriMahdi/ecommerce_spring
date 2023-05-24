package com.ui.ac.shop.ir.shop.Repository;

import com.ui.ac.shop.ir.shop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findCartItemsByCartId(UUID id);
    Optional<CartItem> findCartItemByProductId(Long id);
}
