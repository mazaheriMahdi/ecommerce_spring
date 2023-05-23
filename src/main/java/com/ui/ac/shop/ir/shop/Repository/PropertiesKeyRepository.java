package com.ui.ac.shop.ir.shop.Repository;

import com.ui.ac.shop.ir.shop.model.PropertiesKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesKeyRepository extends JpaRepository<PropertiesKey, Long> {
}
