package com.ui.ac.shop.ir.shop.controller;


import com.ui.ac.shop.ir.shop.Exception.CartIdNotProvidedException;
import com.ui.ac.shop.ir.shop.Exception.InvalidCartIdException;
import com.ui.ac.shop.ir.shop.Service.CartItemService;
import com.ui.ac.shop.ir.shop.Service.CartService;
import com.ui.ac.shop.ir.shop.Service.CustomerService;
import com.ui.ac.shop.ir.shop.Service.UserService;
import com.ui.ac.shop.ir.shop.Service.product.ProductService;
import com.ui.ac.shop.ir.shop.model.Cart.Cart;
import com.ui.ac.shop.ir.shop.model.Cart.CartItem;
import com.ui.ac.shop.ir.shop.model.Product.Product;
import com.ui.ac.shop.ir.shop.model.RequestModels.AddCartItemRequestModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.CartCreateResponseModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.CartResponseModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.MessageResponseModel;
import com.ui.ac.shop.ir.shop.model.User.Customer;
import com.ui.ac.shop.ir.shop.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {
    CartService cartService;
    CartItemService cartItemService;
    UserService userService;
    CustomerService customerService;

    ProductService productService;

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


    @GetMapping("/{id}/items/count")
    public ResponseEntity<Map<String, Integer>> getCartItemCount(@PathVariable UUID id, @RequestAttribute("user") User user) {
        Customer customer = customerService.getCustomerByUserID(user.getId());
        Cart cart = cartService.getCustomerCart(customer.getId());
        if (cart.getId().equals(id)) {
            return ResponseEntity.ok(Map.of("count", cartService.getCartItemCount(cart)));
        } else {
            throw new InvalidCartIdException();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<CartResponseModel> getCartItems(@PathVariable UUID id, @RequestAttribute("user") User user) {
        Customer customer = customerService.getCustomerByUserID(user.getId());
        Cart cart = cartService.getCustomerCart(customer.getId());
        if (cart.getId().equals(id)) {
            CartResponseModel cartResponseModel = new CartResponseModel(
                    cart.getPlace_date(),
                    cart.getDiscount() == null ? "" : cart.getDiscount().getCode(),
                    cartItemService.getCartItemsResponseModel(id)
            );
            return new ResponseEntity<>(cartResponseModel, HttpStatus.OK);
        } else {
            throw new InvalidCartIdException();
        }
    }

    @DeleteMapping("/{id}/items/{productId}")
    public ResponseEntity<MessageResponseModel> deleteCartItem(@PathVariable UUID id, @RequestAttribute("customer") Customer customer, @RequestAttribute("user") User user, @PathVariable Long productId) {
        Cart cart = cartService.getCustomerCart(customer.getId());
        if (cart.getId().equals(id)) {
            cartItemService.deleteCartItem(id, productId);
            return ResponseEntity.ok(new MessageResponseModel("cart item deleted successfully"));
        } else {
            throw new InvalidCartIdException();
        }
    }


    @PostMapping("/{id}/setDiscountCode")
    public ResponseEntity<MessageResponseModel> setDiscountCode(@PathVariable UUID id, @RequestAttribute(name = "customer") Customer customer, @RequestAttribute(name = "user") User user, @RequestBody Map<String, String> discountCode) {
        Cart cart = cartService.getCustomerCart(customer.getId());
        if (cart.getId().equals(id)) {
            Optional<List<CartItem>> cartItemList = cartItemService.getCartItemList(cart.getId());
            if (cartItemList.isPresent()) {
                cartService.setDiscountCode(cart, user , cartItemList.get(), discountCode.get("discountCode"));
                return ResponseEntity.ok(new MessageResponseModel("discount code set successfully"));
            } else {
                return new ResponseEntity<>(new MessageResponseModel("cart is empty"), HttpStatus.NOT_FOUND);
            }
        } else {
            throw new InvalidCartIdException();
        }
    }


    @PostMapping("/{id}/items")
    public ResponseEntity<MessageResponseModel> createCartItem(
            @PathVariable UUID id,
            @RequestBody AddCartItemRequestModel requestModel,
            @RequestAttribute("user") User user
    ) {
        Customer customer = customerService.getCustomerByUserID(user.getId());
        Cart cart = cartService.getCustomerCart(customer.getId());
        if (cart.getId().equals(id)) {
            Product product = productService.getProductById(requestModel.getProductId());
            CartItem cartItem = new CartItem(product, requestModel.getQuantity(), cart);
            cartItemService.addCartItem(cartItem);
            return new ResponseEntity<>(new MessageResponseModel("cart item added successfully"), HttpStatus.CREATED);
        } else {
            throw new InvalidCartIdException();
        }

    }


}
