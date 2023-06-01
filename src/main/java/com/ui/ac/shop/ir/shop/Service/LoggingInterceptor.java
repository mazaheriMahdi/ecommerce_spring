package com.ui.ac.shop.ir.shop.Service;

import com.ui.ac.shop.ir.shop.Exception.NoTokenProvidedException;
import com.ui.ac.shop.ir.shop.Repository.CustomerRepository;
import com.ui.ac.shop.ir.shop.Repository.UserRepository;
import com.ui.ac.shop.ir.shop.model.User.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;
import java.util.UUID;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    UserRepository userRepository;

    CustomerRepository customerRepository;

    @Autowired
    public LoggingInterceptor(UserRepository userRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null) {

//            if(!(request.getRequestURI().contains("/auth") || request.getRequestURI().startsWith("/product"))){
//                throw new NoTokenProvidedException();
//            }

            if (!request.getRequestURI().contains("/auth") && !request.getRequestURI().equals("/product")) {

                throw new NoTokenProvidedException();
            }


        } else {
            Optional<User> user = userRepository.findByUuid(UUID.fromString(authHeader));
            user.ifPresent(value -> {
                request.setAttribute("user", value);
                request.setAttribute("customer", customerRepository.findCustomerByUserId(value.getId()).get());
            });
        }
        // other header logging code
        return true;
    }

    // other methods of HandlerInterceptor interface
}