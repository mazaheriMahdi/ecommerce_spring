package com.ui.ac.shop.ir.shop.Repository;

import com.ui.ac.shop.ir.shop.model.Application;
import com.ui.ac.shop.ir.shop.model.Status;
import com.ui.ac.shop.ir.shop.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application , Long> {
    Optional<Application> findAllByType(Type type);
    Optional<Application> findAllByStatus(Status status);
    Optional<Application> findApplicationById(Long id);
}
