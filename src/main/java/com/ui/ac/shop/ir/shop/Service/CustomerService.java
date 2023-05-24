package com.ui.ac.shop.ir.shop.Service;

import com.ui.ac.shop.ir.shop.Exception.EntityNotFoundException;
import com.ui.ac.shop.ir.shop.Repository.CustomerRepository;
import com.ui.ac.shop.ir.shop.Repository.UserRepository;
import com.ui.ac.shop.ir.shop.model.Customer;
import com.ui.ac.shop.ir.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    CustomerRepository customerRepository;
    UserRepository userRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    public Customer getCustomerByUserID(Long id) {
        Optional<Customer> customer = customerRepository.findCustomerByUserId(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new EntityNotFoundException("User", id);

    }

    public void createCustomer(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            customerRepository.save(new Customer(user.get()));
        }else throw new EntityNotFoundException("User", userId);
    }

}
