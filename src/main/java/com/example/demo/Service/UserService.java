package com.example.demo.Service;

import com.example.demo.Model.Message;
import com.example.demo.Model.User;
import com.example.demo.Repository.MessageRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public boolean registerUser(User user){

        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);

        if(userRepository.existsById(user.getId())){
            return true;
        }
        return false;
    }

    public User getUserWithEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<Message> getAllUserMessage(String username) {
        User user = userRepository.findByUsername(username);
        return messageRepository.findByUserId(user.getId());
    }

    public String verify(User user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        if(authentication.isAuthenticated()) return jwtService.generateToken(user.getEmail());
        return "";
    }
    public boolean isUserExists(String username) {
        return userRepository.existsByUsername(username);
    }
}
