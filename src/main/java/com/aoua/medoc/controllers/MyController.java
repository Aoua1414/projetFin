//package com.aoua.medoc.controllers;
//
//import com.aoua.medoc.MyHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//@RestController
//public class MyController {
//
//  /*  @Autowired
//    private MyHandler myHandler;
//
//    @PostMapping("/send-message")
//    public void sendMessage(@RequestBody String message) throws IOException {
//        myHandler.pushMessage(message);
//    }*/
//
//    @Autowired
//    private MyHandler myHandler;
//
//    @MessageMapping("/send-message")
//    @SendTo("/topic/messages")
//    public String sendMessage(String message) throws Exception {
//        myHandler.pushMessage(message);
//        return message;
//    }
//}
//
