package com.ui.ac.shop.ir.shop.model;

import com.ui.ac.shop.ir.shop.model.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class Discount {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private String code;
    private Integer percent;
    @ManyToOne
    private User user;


    LocalDate startAt = LocalDate.now();
    private int day;
    public Boolean isExpired(){
        return (Period.between(startAt , LocalDate.now()).getDays() > day);
    }

    public Discount(Long id, Integer percent, User user, int count , int day) {
        this.id = id;
        this.percent = percent;
        this.user = user;
        this.count = count;
        this.day = day;
    }

    private int count;

    public Discount(Integer percent, User user, int count,  int day) {
        this.percent = percent;
        this.user = user;
        this.count = count;
        this.day = day;
    }

    public int getExpireDate(){
        if (isExpired())return 0;
        return (day - Period.between(startAt , LocalDate.now()).getDays());
    }

    public String getCode() {
        return user.getName()+id+percent;
    }
}
