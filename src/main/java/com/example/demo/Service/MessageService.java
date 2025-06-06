package com.example.demo.Service;

import com.example.demo.Model.Message;
import com.example.demo.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    public boolean createMessage(Message message){
        String slug = generateSlug(message.getTitle());

        message.setSlug(slug);
        messageRepository.save(message);
        return true;
    }

    private String generateSlug(String title) {
        String baseSlug = title.trim().toLowerCase().replaceAll("[^a-z0-9]+", "-");
        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8); // or use timestamp
        return baseSlug + "-" + uniqueSuffix;
    }

    public Message getMessage(String slug){
        return messageRepository.findBySlug(slug);
    }

    public Message getMessageById(Long id){
        return messageRepository.findById(id).orElse(null);
    }


    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

}
