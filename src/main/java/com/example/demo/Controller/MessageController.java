package com.example.demo.Controller;

import com.example.demo.Model.Message;
import com.example.demo.Response.MessageResponse;
import com.example.demo.Response.Response;
import com.example.demo.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
@CrossOrigin
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

    @GetMapping("/message/{slug}")
    public MessageResponse getMessage(@PathVariable String slug){
        Message message = messageService.getMessage(slug);

        if (message.getExpiration() != null && message.getExpiration().isBefore(LocalDate.now())) {
            return new MessageResponse(HttpStatus.GONE, "Message expired", (Message) null);
        }

        if(message == null) new MessageResponse(HttpStatus.NOT_FOUND,"Message not found",message);
        return new MessageResponse(HttpStatus.OK,"Message fetched successfully",message);
    }

    @DeleteMapping("/message/delete/{id}")
    public Response deleteMessage(@PathVariable Long id) {
        Message message = messageService.getMessageById(id);

        System.out.println(message.getId());

        if (message == null) {
            return new Response(HttpStatus.NOT_FOUND, "Message not found");
        }

        messageService.deleteMessage(id);
        return new Response(HttpStatus.OK, "Message deleted successfully");
    }

}
