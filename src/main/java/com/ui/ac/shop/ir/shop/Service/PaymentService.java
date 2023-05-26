package com.ui.ac.shop.ir.shop.Service;


import com.ui.ac.shop.ir.shop.Exception.EntityNotFoundException;
import com.ui.ac.shop.ir.shop.Repository.PaymentRepository;
import com.ui.ac.shop.ir.shop.Service.CustomerService;
import com.ui.ac.shop.ir.shop.model.Enums.Status;
import com.ui.ac.shop.ir.shop.model.Payment.Payment;
import com.ui.ac.shop.ir.shop.model.RequestModels.PaymentRequestModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.PaymentResponseModel;
import com.ui.ac.shop.ir.shop.model.User.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    PaymentRepository paymentRepository;
    CustomerService customerService;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, CustomerService customerService) {
        this.paymentRepository = paymentRepository;
        this.customerService = customerService;
    }

    public List<PaymentResponseModel> getPaymentsByCustomer(Long customerId) {
        List<PaymentResponseModel> paymentResponseModels = new ArrayList<>();
        Optional<Payment> payments = paymentRepository.findAllByCustomerId(customerId);
        if (payments.isPresent()) {
            for (Payment payment : payments.stream().toList()) {
                paymentResponseModels.add(
                        new PaymentResponseModel(
                                payment.getStatus(),
                                payment.getAmount(),
                                payment.getCardNumber(),
                                payment.getLocalDate()
                        )
                );
            }
            return paymentResponseModels;
        }
        throw new EntityNotFoundException("Payment", "Customer", customerId.toString());
    }



    public void acceptPayment(Long paymentId) {
        paymentRepository.findById(paymentId).ifPresent(payment -> {
            payment.setStatus(Status.ACCEPTED);
            paymentRepository.save(payment);
            Customer customer = payment.getCustomer();
            customer.setCredit(customer.getCredit() + payment.getAmount());
            customerService.updateCustomer(customer);
        });
    }

    public void rejectPayment(Long paymentId) {
        paymentRepository.findById(paymentId).ifPresent(payment -> {
            payment.setStatus(Status.REJECTED);
            paymentRepository.save(payment);
        });
    }

    public void createPayment(Customer customer, PaymentRequestModel request) {
        Payment payment  = new Payment(
                request.getCardNumber(),
                request.getCvv2(),
                request.getAmount(),
                customer
        );
        paymentRepository.save(payment);
    }
}
