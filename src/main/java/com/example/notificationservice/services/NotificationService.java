package com.example.notificationservice.services;

import com.example.notificationservice.dtos.EmailNotification;
import com.example.notificationservice.dtos.NotificationType;
import com.example.notificationservice.utils.NotificationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import java.util.Properties;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    NotificationUtils notificationUtils;

    @Value("${email.password}")
    private String emailPassword;

    @Override
    public void sendEmailNotification(EmailNotification emailNotification) {
        emailNotification.setType(NotificationType.EMAIL);

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailNotification.getFromEmail(), emailPassword);
            }
        };
        Session session = Session.getInstance(props, auth);
        notificationUtils.sendEmailNotification(emailNotification, session);
    }
}
