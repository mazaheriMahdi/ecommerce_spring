package com.ui.ac.shop.ir.shop.controller;


import com.ui.ac.shop.ir.shop.Exception.AccessDeniedException;
import com.ui.ac.shop.ir.shop.Service.DiscountService;
import com.ui.ac.shop.ir.shop.Service.UserService;
import com.ui.ac.shop.ir.shop.model.Discount;
import com.ui.ac.shop.ir.shop.model.RequestModels.DiscountRequestModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.DiscountResponseModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.MessageResponseModel;
import com.ui.ac.shop.ir.shop.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/discount")
public class DiscountController {
    DiscountService discountService;
    UserService userService;

    @Autowired
    public DiscountController(DiscountService discountService, UserService userService) {
        this.discountService = discountService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<DiscountResponseModel>> getAllDiscounts(@RequestAttribute(name = "user") User user) {
        if (user.getIsStaff()) {
            return new ResponseEntity<>(discountService.getAllDiscounts(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(discountService.getAllUserDiscounts(user.getId()), HttpStatus.OK);
        }
    }


    @PostMapping
    public ResponseEntity<MessageResponseModel> addDiscount(@RequestAttribute(name = "user") User user, @RequestBody DiscountRequestModel discount) {
        if (user.getIsStaff()) {
            Discount discountModel = new Discount(discount.getPercent(), userService.getUserByEmail(discount.getEmail()), discount.getCount(), discount.getDay());
            discountService.addDiscount(discountModel);
            return new ResponseEntity<>(new MessageResponseModel(discountModel.getCode()), HttpStatus.OK);
        } else throw new AccessDeniedException();
    }

}
