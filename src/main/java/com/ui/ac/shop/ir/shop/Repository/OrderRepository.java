package com.ui.ac.shop.ir.shop.Repository;

import com.ui.ac.shop.ir.shop.model.Enums.OrderStatus;
import com.ui.ac.shop.ir.shop.model.Enums.Status;
import com.ui.ac.shop.ir.shop.model.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<List<Order>> findAllByCustomerId(Long id);
    Optional<List<Order>> findAllByOrderStatus(OrderStatus status);

}
