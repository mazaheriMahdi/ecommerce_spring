package com.ui.ac.shop.ir.shop.model;

import com.ui.ac.shop.ir.shop.model.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Discount {
    @Id
    private Long id;

    @Transient
    private String code;
    private Integer percent;
    @ManyToOne
    private User user;

    public String getCode() {
        return user.getName()+id+percent;
    }
}
