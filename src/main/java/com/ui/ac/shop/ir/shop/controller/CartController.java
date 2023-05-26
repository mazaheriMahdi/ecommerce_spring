package com.ui.ac.shop.ir.shop.controller;


import com.ui.ac.shop.ir.shop.Exception.CartIdNotProvidedException;
import com.ui.ac.shop.ir.shop.Exception.InvalidCartIdException;
import com.ui.ac.shop.ir.shop.Service.*;
import com.ui.ac.shop.ir.shop.model.Cart.Cart;
import com.ui.ac.shop.ir.shop.model.Cart.CartItem;
import com.ui.ac.shop.ir.shop.model.Product.Product;
import com.ui.ac.shop.ir.shop.model.RequestModels.AddCartItemRequestModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.CartCreateResponseModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.CartResponseModel;
import com.ui.ac.shop.ir.shop.model.User.Customer;
import com.ui.ac.shop.ir.shop.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {
    CartService cartService;
    CartItemService cartItemService;
    UserService userService;
    CustomerService customerService;

    ProductService  productService;

    @Autowired
    public CartController(CartService cartService, CartItemService cartItemService, UserService userService, CustomerService customerService, ProductService productService) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
        this.userService = userService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<CartCreateResponseModel> createCart(@RequestAttribute("user") User user) {

        Customer customer = customerService.getCustomerByUserID(user.getId());
        return ResponseEntity.ok(new CartCreateResponseModel(cartService.getCustomerCart(customer.getId()).getId()));
    }

    @GetMapping("/")
    public void sendError() {
        throw new CartIdNotProvidedException();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponseModel> getCartItems(@PathVariable UUID id , @RequestAttribute("user") User user) {
        Customer customer = customerService.getCustomerByUserID(user.getId());
        Cart cart = cartService.getCustomerCart(customer.getId());
        if (cart.getId().equals(id)) {
            CartResponseModel cartResponseModel = new CartResponseModel(cart.getPlace_date() , cartItemService.getCartItemsResponseModel(id));
            return new ResponseEntity<>(cartResponseModel , HttpStatus.OK);
        } else {
            throw new InvalidCartIdException();
        }
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<CartResponseModel> createCartItem(
            @PathVariable UUID id,
            @RequestBody AddCartItemRequestModel requestModel ,
            @RequestAttribute("user") User user
    ) {
        Customer customer = customerService.getCustomerByUserID(user.getId());
        Cart cart = cartService.getCustomerCart(customer.getId());
        if (cart.getId().equals(id)) {
            Product product = productService.getProductById(requestModel.getProductId());
            CartItem cartItem = new CartItem(product, requestModel.getQuantity(), cart);
            cartItemService.addCartItem(cartItem);
            return ResponseEntity.ok(new CartResponseModel(cart.getPlace_date() , cartItemService.getCartItemsResponseModel(id)));
        } else {
            throw new InvalidCartIdException();
        }

    }


}
