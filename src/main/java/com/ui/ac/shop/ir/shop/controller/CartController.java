package com.ui.ac.shop.ir.shop.controller;


import com.ui.ac.shop.ir.shop.Exception.CartIdNotProvidedException;
import com.ui.ac.shop.ir.shop.Exception.InvalidCartIdException;
import com.ui.ac.shop.ir.shop.Service.CartItemService;
import com.ui.ac.shop.ir.shop.Service.CartService;
import com.ui.ac.shop.ir.shop.Service.CustomerService;
import com.ui.ac.shop.ir.shop.Service.UserService;
import com.ui.ac.shop.ir.shop.model.Cart;
import com.ui.ac.shop.ir.shop.model.CartItem;
import com.ui.ac.shop.ir.shop.model.Customer;
import com.ui.ac.shop.ir.shop.model.RequestModels.AddCartItemRequestModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.CartCreateResponseModel;
import com.ui.ac.shop.ir.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {
    CartService cartService;
    CartItemService cartItemService;
    UserService userService;
    CustomerService customerService;

    @Autowired
    public CartController(CartService cartService, CartItemService cartItemService, UserService userService, CustomerService customerService) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
        this.userService = userService;
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CartCreateResponseModel> createCart() {
        UserCheckController.CHECK_USER_LOGIN();
        User user = userService.getCurrentUser();
        Customer customer = customerService.getCustomerByUserID(user.getId());
        return ResponseEntity.ok(new CartCreateResponseModel(cartService.getCustomerCart(customer.getId()).getId()));
    }

    @GetMapping("/")
    public void sendError(){
        throw new CartIdNotProvidedException();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable UUID id){
        UserCheckController.CHECK_USER_LOGIN();
        User user = userService.getCurrentUser();
        Customer customer = customerService.getCustomerByUserID(user.getId());
        if (cartService.getCustomerCart(customer.getId()).getId().equals(id)) {
            return ResponseEntity.ok(cartItemService.getCartItems(id));
        }else {
            throw new InvalidCartIdException();
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<CartItem> createCartItem(@PathVariable UUID id , @RequestBody AddCartItemRequestModel requestModel){
        UserCheckController.CHECK_USER_LOGIN();
        User user = userService.getCurrentUser();
        Customer customer = customerService.getCustomerByUserID(user.getId());
        Cart cart = cartService.getCustomerCart(customer.getId());
        if (cart.getId().equals(id)) {
            CartItem cartItem = new CartItem(requestModel.getProduct() , requestModel.getQuantity() , cart);
            cartItemService.addCartItem(cartItem);
            return ResponseEntity.ok(cartItem);
        }else {
            throw new InvalidCartIdException();
        }

    }

}
