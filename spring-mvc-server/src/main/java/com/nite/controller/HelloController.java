package com.nite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class HelloController {

    private final SimpMessagingTemplate template;

    @Autowired
    HelloController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/send/message")
    public void onReceivedMesage(String message) throws InterruptedException {
        System.out.println("\n\n----->>> Recieved request :: " + message);
        this.template.convertAndSend("/nite", new SimpleDateFormat("HH:mm:ss").format(new Date()) + "- " + message);
        Thread.sleep(2000);
        this.template.convertAndSend("/nite", new SimpleDateFormat("HH:mm:ss").format(new Date()) + "- " + "Nitesh 2nd");
    }
}
