package com.example.notificationservice.consumers;

import com.example.notificationservice.dtos.Notification;

public interface NotificationConsumer {
    void sendWelcomeNotification(String notification);
}
