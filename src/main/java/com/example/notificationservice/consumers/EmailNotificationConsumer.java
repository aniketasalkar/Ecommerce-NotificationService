package com.example.notificationservice.consumers;

import com.example.notificationservice.dtos.EmailNotification;
import com.example.notificationservice.dtos.Notification;
import com.example.notificationservice.services.INotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class EmailNotificationConsumer implements NotificationConsumer {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    INotificationService notificationService;

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationConsumer.class);

    @KafkaListener(
            topics = {"welcome-email", "payment-email", "confirm-order-email", "payment-failure-email"},
            groupId = "emailNotification")
    @Override
    public void sendWelcomeNotification(String notification) {
        EmailNotification emailNotification;
//        EmailNotification emailNotification = objectMapper.convertValue(notification, EmailNotification.class);
        try {
            emailNotification = objectMapper.readValue(notification, EmailNotification.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        logger.info("Received kafka Event for : {}", emailNotification.getToEmail());
        notificationService.sendEmailNotification(emailNotification);
    }

}
