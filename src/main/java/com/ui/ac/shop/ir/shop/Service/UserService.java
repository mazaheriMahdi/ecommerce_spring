package com.ui.ac.shop.ir.shop.Service;


import com.ui.ac.shop.ir.shop.Exception.EntityNotFoundException;
import com.ui.ac.shop.ir.shop.Exception.InvalidTokenException;
import com.ui.ac.shop.ir.shop.Exception.TakenEmailException;
import com.ui.ac.shop.ir.shop.Repository.UserRepository;
import com.ui.ac.shop.ir.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()){
            return user.get();
        }throw new EntityNotFoundException("User","email" , email );
    }

    public User getByToken(UUID token){
        Optional<User> user = userRepository.findByUuid(token);
        if (user.isPresent()){
            return user.get();
        }throw new InvalidTokenException();
    }

    public Boolean checkUserExistence(UUID token){
        return userRepository.findByUuid(token).isPresent();
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User addUser(User user){
        if (userRepository.findByEmail(user.getEmail()).isEmpty()){
            userRepository.save(user);
            return userRepository.findByEmail(user.getEmail()).get();
        }else {
            throw new TakenEmailException(user.getEmail());
        }
    }
}
