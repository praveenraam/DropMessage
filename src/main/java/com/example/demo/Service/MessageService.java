package com.example.demo.Service;

import com.example.demo.Model.Message;
import com.example.demo.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    public boolean createMessage(Message message){
        messageRepository.save(message);
        return true;
    }

}
