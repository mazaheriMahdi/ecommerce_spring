package com.ui.ac.shop.ir.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "application")
public class Application {

    @Id
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    
    @Enumerated(EnumType.ORDINAL)
    private Type type;
    
}

