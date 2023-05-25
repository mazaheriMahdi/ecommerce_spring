package com.ui.ac.shop.ir.shop.Repository;

import com.ui.ac.shop.ir.shop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem , Long> {
    Optional<OrderItem> findAllByOrderId(Long id);
    Optional<OrderItem> findByProductId(Long id);

}
