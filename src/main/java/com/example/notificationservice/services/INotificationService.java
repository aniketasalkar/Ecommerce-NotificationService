package com.example.notificationservice.services;

import com.example.notificationservice.dtos.EmailNotification;

public interface INotificationService {
    void sendEmailNotification(EmailNotification emailNotification);
}
