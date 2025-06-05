package com.example.demo.Controller;

import com.example.demo.Model.Message;
import com.example.demo.Model.User;
import com.example.demo.Response.MessageResponse;
import com.example.demo.Response.Response;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Response registerUser(@RequestBody User user){
        boolean result = userService.registerUser(user);
        if(result){
            return new Response(HttpStatus.ACCEPTED,"Account created Successfully");
        }
        return new Response(HttpStatus.NOT_ACCEPTABLE,"Account not created");
    }

//    @PostMapping("/login")

    @GetMapping("/user/{username}")
    public Response allUserMessage(@PathVariable String username){
        if(userService.isUserExists(username)) return new MessageResponse(HttpStatus.NOT_FOUND,"User not found",null);

        List<Message> listOfMessage = userService.getAllUserMessage(username);
        if(listOfMessage.isEmpty()) return new MessageResponse(HttpStatus.OK,"No messages",listOfMessage);

        return new MessageResponse(HttpStatus.OK,"Messages were fetched",listOfMessage);
    }



}
