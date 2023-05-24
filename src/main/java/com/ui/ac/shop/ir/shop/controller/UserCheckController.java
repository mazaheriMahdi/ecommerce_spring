package com.ui.ac.shop.ir.shop.controller;


import com.ui.ac.shop.ir.shop.Exception.NoTokenProvidedException;
import com.ui.ac.shop.ir.shop.Service.UserService;
import com.ui.ac.shop.ir.shop.model.ContainerModel.CurrentUserContainer;
import com.ui.ac.shop.ir.shop.model.ResponseModels.MessageResponseModel;
import jakarta.servlet.http.HttpServlet;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public void check(@RequestHeader(HttpHeaders.AUTHORIZATION) UUID token) {
      if (token == null){
          isLoggedIn = false;
      }else {
          isLoggedIn = userService.checkUserExistence(token);
          System.out.println(isLoggedIn + "-------------------------------------");
      }
    }
}
