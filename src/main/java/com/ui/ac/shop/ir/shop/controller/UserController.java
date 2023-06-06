package com.ui.ac.shop.ir.shop.controller;


import com.ui.ac.shop.ir.shop.Exception.AccessDeniedException;
import com.ui.ac.shop.ir.shop.Service.CustomerService;
import com.ui.ac.shop.ir.shop.Service.UserService;
import com.ui.ac.shop.ir.shop.model.RequestModels.LoginRequestModel;
import com.ui.ac.shop.ir.shop.model.RequestModels.SignInRequestModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.UserResponseModel;
import com.ui.ac.shop.ir.shop.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {
    UserService userService;
    CustomerService customerService;

    @Autowired
    public UserController(UserService userService, CustomerService customerService) {
        this.userService = userService;
        this.customerService = customerService;
    }

    @PostMapping(value = "/signIn")
    public ResponseEntity<UserResponseModel> signIn(@RequestBody SignInRequestModel signInRequestModel) {
        User user = userService.addUser(new User(signInRequestModel.getName()
                , signInRequestModel.getEmail()
                , signInRequestModel.getPassword())
        );
        customerService.createCustomer(user.getId() , signInRequestModel.getAvatar_url());
        return new ResponseEntity<>(new UserResponseModel(user.getUuid()), HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserResponseModel> login(@RequestBody LoginRequestModel loginRequestModel) {
        User user = userService.getUserByEmailAndPass(loginRequestModel.getEmail(), loginRequestModel.getPassword());
        return new ResponseEntity<>(new UserResponseModel(user.getUuid()), HttpStatus.OK);

    }

    @PostMapping(value = "/isStaff")
    public ResponseEntity<Map<String, Boolean>> isStaff(@RequestAttribute(name = "user") User user) {
        Map<String , Boolean> response = new HashMap<>();
        response.put("isStaff" , user.getIsStaff());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestAttribute(name = "user") User user) {
        if (user.getIsStaff()){
            return new  ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);
        }throw new AccessDeniedException();
    }


}
