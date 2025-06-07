package com.example.demo.Response;

import com.example.demo.Model.User;
import org.springframework.http.HttpStatus;

public class LoginResponse extends Response{

    private User user;
    private String token;

    public LoginResponse(HttpStatus status, String message) {
        super(status, message);
    }

    public LoginResponse(HttpStatus status, String message, User user, String token) {
        super(status, message);
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
