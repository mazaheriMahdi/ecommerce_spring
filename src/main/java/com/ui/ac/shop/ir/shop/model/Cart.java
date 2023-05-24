package com.ui.ac.shop.ir.shop.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Cart {
    @Id
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate place_date = LocalDate.now();
}
