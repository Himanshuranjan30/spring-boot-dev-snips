package com.springboot.demo.utils;

import com.springboot.demo.models.SignupEvent;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.event.S3EventNotification;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageReceiver {

    @SqsListener(value = "testQueue", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS )
    public void receiveStringMessage(final String message,
                                     @Header("SenderId") String senderId) {
        log.info("message received {} {}",senderId,message);
    }

    @SqsListener(value = "testObjectQueue", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS )
    public void receiveObjectMessage(final SignupEvent message,
                                     @Header("SenderId") String senderId) {
        log.info("object message received {} {}",senderId,message);
    }

    @SqsListener(value = "testS3Queue", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receiveS3Event(S3EventNotification s3EventNotificationRecord) {
        S3EventNotification.S3Entity s3Entity = s3EventNotificationRecord.getRecords().get(0).getS3();
        String objectKey = s3Entity.getObject().getKey();
        log.info("s3 event::objectKey:: {}",objectKey);
    }
}
