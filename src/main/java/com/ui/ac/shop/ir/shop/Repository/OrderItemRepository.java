package com.ui.ac.shop.ir.shop.Repository;

import com.ui.ac.shop.ir.shop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem , Long> {
    Optional<List<OrderItem>> findAllByOrderId(Long id);
    Optional<OrderItem> findByProductIdAndOrder_Id(Long id , Long orderId);

}
