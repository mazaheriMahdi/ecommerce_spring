package com.ui.ac.shop.ir.shop.controller;


import com.ui.ac.shop.ir.shop.Service.UserService;
import com.ui.ac.shop.ir.shop.model.RequestModels.LoginRequestModel;
import com.ui.ac.shop.ir.shop.model.RequestModels.SignInRequestModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.UserResponseModel;
import com.ui.ac.shop.ir.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/signIn")
    public ResponseEntity<UserResponseModel> signIn(@RequestBody SignInRequestModel signInRequestModel) {
        User user = userService.addUser(new User(signInRequestModel.getName()
                , signInRequestModel.getEmail()
                , signInRequestModel.getPassword())
        );
        return new ResponseEntity<>(new UserResponseModel(user.getUuid()), HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserResponseModel> login(@RequestBody LoginRequestModel loginRequestModel){
        User user = userService.getUserByEmail(loginRequestModel.getEmail());
        return new ResponseEntity<>(new UserResponseModel(user.getUuid()) , HttpStatus.OK );

    }



}
