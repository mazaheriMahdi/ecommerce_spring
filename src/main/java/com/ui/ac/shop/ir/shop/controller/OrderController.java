package com.ui.ac.shop.ir.shop.controller;


import com.ui.ac.shop.ir.shop.Service.OrderService;
import com.ui.ac.shop.ir.shop.Service.UserService;
import com.ui.ac.shop.ir.shop.model.Order.Order;
import com.ui.ac.shop.ir.shop.model.User.Customer;
import com.ui.ac.shop.ir.shop.model.ResponseModels.OrderItemResponseModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.OrderResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    OrderService orderService;
    UserService userService;



    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseModel>> getAllCustomerOrder(@RequestAttribute Customer customer
    ) {

        return new ResponseEntity<>(orderService.getAllCustomerOrderResponseModel(customer.getId()), HttpStatus.OK);
    }

    @GetMapping("/accepted")
    public ResponseEntity<List<OrderResponseModel>> getAllAcceptedOrders(@RequestAttribute(name = "customer") Customer customer){
        return new ResponseEntity<>(orderService.getAllAcceptedOrders(customer), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponseModel> getOrderItem(@PathVariable Long id , @RequestAttribute("customer") Customer customer){
        return  new ResponseEntity<>(orderService.getOrderItemResponse(id , customer.getId()) , HttpStatus.OK);
    }

    @PostMapping("/{cartId}")
    public ResponseEntity<UUID> createOrder(@PathVariable UUID cartId, @RequestAttribute("customer") Customer customer){
        orderService.createOrder(cartId , customer);
        return new ResponseEntity<>(cartId, HttpStatus.OK);
    }


}
