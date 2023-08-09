package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.test.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ArrayList<User> getAll(){
        return userRepository.getAll();
    }

    public User addUser(User user){
        return userRepository.save(user);
    }
    public boolean authenticate(String login, String password) {
        User user = userRepository.findByLogin(login);
        return user != null && user.getPassword().equals(password);
    }
}
