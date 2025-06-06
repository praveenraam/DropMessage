package com.example.demo.Response;

import com.example.demo.Model.Message;
import org.springframework.http.HttpStatus;

import java.util.List;

public class MessageResponse extends Response{

    private List<Message> messageList;
    private Message messageObj;


    public MessageResponse(HttpStatus status, String message, List<Message> messageList) {
        super(status, message);
        this.messageList = messageList;
    }

    public MessageResponse(HttpStatus status,String message,Message messageObj){
        super(status,message);
        this.messageObj = messageObj;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public Message getMessageObj() {
        return messageObj;
    }

    public void setMessageObj(Message messageObj) {
        this.messageObj = messageObj;
    }
}
