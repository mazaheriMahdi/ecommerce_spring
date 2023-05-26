package com.ui.ac.shop.ir.shop.controller;


import com.ui.ac.shop.ir.shop.Service.CustomerService;
import com.ui.ac.shop.ir.shop.Service.UserService;
import com.ui.ac.shop.ir.shop.model.RequestModels.CustomerPatchRequestModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.GetProfileResponseModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.MessageResponseModel;
import com.ui.ac.shop.ir.shop.model.User.Customer;
import com.ui.ac.shop.ir.shop.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/profile")
public class CustomerController {
    CustomerService customerService;
    UserService userService;

    @Autowired
    public CustomerController(CustomerService customerService, UserService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<GetProfileResponseModel> getProfile(@RequestAttribute("customer") Customer customer) {
        GetProfileResponseModel profileResponseModel = new GetProfileResponseModel(
                customer.getUser().getName(),
                customer.getUser().getEmail(),
                customer.getCredit(),
                customer.getAvatarUrl()
        );

        return new ResponseEntity<>(profileResponseModel, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<MessageResponseModel> updateProfile(
            @RequestBody CustomerPatchRequestModel requestModel,
            @RequestAttribute("customer") Customer customer,
            @RequestAttribute("user") User user
    ) {
        user.setName(requestModel.getFirstName());
        user.setEmail(requestModel.getEmail());
        userService.updateUser(user);
        return new ResponseEntity<>(new MessageResponseModel("Profile updated successfully"), HttpStatus.OK);

    }


}
