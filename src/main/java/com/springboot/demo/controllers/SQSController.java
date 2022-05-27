package com.springboot.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sqs")
public class SQSController {

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String urlEndPoint;

    Logger logger = LoggerFactory.getLogger(SQSController.class);

    @GetMapping("/send/{message}")
    public void sendMessage(@PathVariable String message){
        logger.info("url{}",urlEndPoint);
        queueMessagingTemplate.send(urlEndPoint, MessageBuilder.withPayload(message).build());
    }

    @SqsListener("demoQueue")
    public void logMessage(String payload){
         logger.info(payload);
    }
}
