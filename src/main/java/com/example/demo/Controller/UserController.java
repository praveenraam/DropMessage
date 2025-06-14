package com.example.demo.Controller;

import com.example.demo.Model.Message;
import com.example.demo.Model.User;
import com.example.demo.Response.LoginResponse;
import com.example.demo.Response.MessageResponse;
import com.example.demo.Response.Response;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Response registerUser(@RequestBody User user){
        boolean isUserCreated = userService.registerUser(user);
        if(isUserCreated){
            return new Response(HttpStatus.ACCEPTED,"Account created Successfully");
        }
        return new Response(HttpStatus.NOT_ACCEPTABLE,"Account not created");
    }

//    @PostMapping("/login")
    @PostMapping("/login")
    public LoginResponse login(@RequestBody User user){
            String token = userService.verify(user);
            if( token.equals("")) return (LoginResponse) new Response(HttpStatus.BAD_REQUEST,"Invalid Credentials");

            User LoggedUser = userService.getUserWithEmail(user.getEmail());
            return new LoginResponse(HttpStatus.ACCEPTED,"Successfully Logged In",LoggedUser,token);
    }

    @GetMapping("/u/{username}")
    public Response UsersAllMessage(@PathVariable String username){
        if(!userService.isUserExists(username)) return new MessageResponse(HttpStatus.NOT_FOUND, "User not found", new ArrayList<>());

        List<Message> listOfMessage = userService.getAllUserMessage(username);
        if(listOfMessage.isEmpty()) return new MessageResponse(HttpStatus.OK,"No messages",listOfMessage);

        return new MessageResponse(HttpStatus.OK,"Messages were fetched",listOfMessage);
    }

    @GetMapping("/api/isValidJWT")
    public Response isJWTValid(){
        return new Response (HttpStatus.OK,"isValid");
    }

}
