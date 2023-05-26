package com.ui.ac.shop.ir.shop.model.ResponseModels;

import com.ui.ac.shop.ir.shop.model.Enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
@Value
public class PaymentResponseModel {
    Status status;
    double amount;
    String cardNumber;
    LocalDate localDate ;
}
