package com.ui.ac.shop.ir.shop.Service;

import com.ui.ac.shop.ir.shop.Exception.EntityNotFoundException;
import com.ui.ac.shop.ir.shop.Repository.CustomerRepository;
import com.ui.ac.shop.ir.shop.Repository.UserRepository;
import com.ui.ac.shop.ir.shop.model.User.Customer;
import com.ui.ac.shop.ir.shop.model.User.User;
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

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }
    public void createCustomer(Long userId , String image_url) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Customer customer = new Customer(user.get());
            customer.setAvatarUrl(image_url);
            customerRepository.save(customer);
        }else throw new EntityNotFoundException("User", userId);
    }

}
