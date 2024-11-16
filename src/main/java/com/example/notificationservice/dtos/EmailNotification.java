package com.example.notificationservice.dtos;

import lombok.Data;

@Data
public class EmailNotification extends Notification {
    private String fromEmail;
    private String toEmail;
    private String subject;
    private String body;
}
