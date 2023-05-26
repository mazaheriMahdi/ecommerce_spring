package com.ui.ac.shop.ir.shop.controller;


import com.ui.ac.shop.ir.shop.Service.CustomerService;
import com.ui.ac.shop.ir.shop.model.User.Customer;
import com.ui.ac.shop.ir.shop.model.ResponseModels.GetProfileResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/profile")
public class CustomerController {
    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<GetProfileResponseModel> getProfile(@RequestAttribute("customer") Customer customer){
        GetProfileResponseModel profileResponseModel = new GetProfileResponseModel(
                customer.getUser().getName(),
                customer.getUser().getEmail(),
                customer.getCredit(),
                customer.getAvatarUrl()
        );

        return new ResponseEntity<>(profileResponseModel , HttpStatus.OK);
    }


}
