package com.example.demo.Controller;

import com.example.demo.Model.Message;
import com.example.demo.Response.MessageResponse;
import com.example.demo.Response.Response;
import com.example.demo.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/create")
    public Response createMessage(@RequestBody Message message){
        boolean isMessageCreated = messageService.createMessage(message);

        if(isMessageCreated) return new Response(HttpStatus.ACCEPTED,"Message created successfully");
        return new Response(HttpStatus.NOT_ACCEPTABLE,"Message not created");
    }

}
