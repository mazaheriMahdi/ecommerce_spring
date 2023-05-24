package com.ui.ac.shop.ir.shop.controller;


import com.ui.ac.shop.ir.shop.Exception.InvalidTokenException;
import com.ui.ac.shop.ir.shop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;



@RestController
@RequestMapping("/*")
public class UserCheckController  {
    UserService userService;

    private static  Boolean isLoggedIn = false;

    public static Boolean getIsLoggedIn() {
        return isLoggedIn;
    }


    @Autowired
    public UserCheckController(UserService userService) {
        this.userService = userService;
    }

    public static void CHECK_USER_LOGIN(){
        if(!UserCheckController.getIsLoggedIn())throw new InvalidTokenException();
    }

    @GetMapping
    @PostMapping
    public void check(@RequestHeader(HttpHeaders.AUTHORIZATION) UUID token) {
      if (token == null){
          isLoggedIn = false;
      }else {
          isLoggedIn = userService.checkUserExistence(token);
          if (isLoggedIn) userService.setCurrentUser(token);
          System.out.println(isLoggedIn + "-------------------------------------");
      }
    }
}
