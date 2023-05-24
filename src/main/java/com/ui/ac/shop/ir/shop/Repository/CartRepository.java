package com.ui.ac.shop.ir.shop.Repository;

import com.ui.ac.shop.ir.shop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
