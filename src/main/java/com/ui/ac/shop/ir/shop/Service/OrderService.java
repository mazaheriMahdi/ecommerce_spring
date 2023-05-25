package com.ui.ac.shop.ir.shop.Service;


import com.ui.ac.shop.ir.shop.Exception.NoOrderForGivenCustomer;
import com.ui.ac.shop.ir.shop.Repository.OrderRepository;
import com.ui.ac.shop.ir.shop.model.Order;
import com.ui.ac.shop.ir.shop.model.ResponseModels.OrderResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(Order order ){
        orderRepository.save(order);
    }
    public void updateOrder(Order order){
        orderRepository.save(order);
    }

    public List<Order> getAllCustomerOrder(Long id){
        Optional<Order> orders =  orderRepository.findByCustomerId(id);
        if (orders.isPresent()){
            return orders.stream().toList();
        }
        throw new NoOrderForGivenCustomer();
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<OrderResponseModel> getAllCustomerOrderResponseModel(Long id){
        Optional<Order> orders =  orderRepository.findByCustomerId(id);
        if (orders.isPresent()){
            List<OrderResponseModel> orderResponseModels = new ArrayList<>();
            for (Order order : orders.stream().toList()) {
                orderResponseModels.add(new OrderResponseModel(order.getId(), order.getCustomer() , order.getPlacedAt()));
            }
            return orderResponseModels;
        }
        throw new NoOrderForGivenCustomer();
    }

}
