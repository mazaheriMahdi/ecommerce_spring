package com.ui.ac.shop.ir.shop.model.ResponseModels;


import com.ui.ac.shop.ir.shop.model.Customer;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@AllArgsConstructor
@Value
public class OrderResponseModel {
     Long id;
     Customer customer;
     LocalDate placedAt ;


}
