package com.ui.ac.shop.ir.shop.controller;


import com.ui.ac.shop.ir.shop.Service.PaymentService;
import com.ui.ac.shop.ir.shop.model.RequestModels.PaymentRequestModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.MessageResponseModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.PaymentResponseModel;
import com.ui.ac.shop.ir.shop.model.User.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<MessageResponseModel> createPayment(@RequestAttribute("customer") Customer customer, @RequestBody PaymentRequestModel request) {
        paymentService.createPayment(customer, request);
        return ResponseEntity.ok(new MessageResponseModel("Payment created successfully"));
    }

    @PostMapping("/{paymentId}/accept")
    public ResponseEntity<MessageResponseModel> acceptPayment(@PathVariable Long paymentId) {
        paymentService.acceptPayment(paymentId);
        return ResponseEntity.ok(new MessageResponseModel("Payment accepted successfully"));
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseModel>> getPayments(@RequestAttribute("customerId") Long customerId) {
        return ResponseEntity.ok(paymentService.getPaymentsByCustomer(customerId));
    }
}
