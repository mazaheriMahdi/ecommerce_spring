package com.ui.ac.shop.ir.shop.Repository;

import com.ui.ac.shop.ir.shop.model.Product.PropertiesKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertiesKeyRepository extends JpaRepository<PropertiesKey, Long> {
    boolean existsByName(String name);
    Optional<PropertiesKey> findByName(String name);
}
