package com.example.demo.Response;

import com.example.demo.Model.Message;
import org.springframework.http.HttpStatus;

import java.util.List;

public class MessageResponse extends Response{

    private List<Message> messageList;
    public MessageResponse(HttpStatus status, String message, List<Message> messageList) {
        super(status, message);
        this.messageList = messageList;
    }
}
