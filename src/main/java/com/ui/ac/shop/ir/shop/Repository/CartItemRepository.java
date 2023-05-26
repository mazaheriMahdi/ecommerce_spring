package com.ui.ac.shop.ir.shop.Repository;

import com.ui.ac.shop.ir.shop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<List<CartItem>> findCartItemsByCartId(UUID id);
    Optional<CartItem> findCartItemByProductId(Long id);
    Optional<CartItem> findCartItemByProductIdAndCart_Id(Long id, UUID cartId);

}
