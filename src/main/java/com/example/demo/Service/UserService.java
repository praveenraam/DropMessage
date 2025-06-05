package com.example.demo.Service;

import com.example.demo.Model.Message;
import com.example.demo.Model.User;
import com.example.demo.Repository.MessageRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    public boolean registerUser(User user){
        userRepository.save(user);
        return true;
    }

    public List<Message> getAllUserMessage(String username) {
        User user = userRepository.findByUsername(username);
        return messageRepository.findByUserId(user.getId());
    }

    public boolean isUserExists(String username) {
        return userRepository.existsByUsername(username);
    }
}
