package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Model.UserPrinciple;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        if(user == null){
            user = userRepository.findByUsername(email);
        }

        if(user == null ) throw new UsernameNotFoundException("User not found");
        return new UserPrinciple(user);
    }
}
