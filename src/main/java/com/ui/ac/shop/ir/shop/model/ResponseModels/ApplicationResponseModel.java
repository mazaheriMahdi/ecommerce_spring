package com.ui.ac.shop.ir.shop.model.ResponseModels;

import com.ui.ac.shop.ir.shop.model.Enums.Status;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Value
public class ApplicationResponseModel {
    Long id;
    Status status;
    String content;
}
