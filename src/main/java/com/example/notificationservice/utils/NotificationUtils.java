package com.example.notificationservice.utils;

import com.example.notificationservice.dtos.EmailNotification;
import com.example.notificationservice.exceptions.SendEmailFailedException;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class NotificationUtils {
    public void sendEmailNotification(EmailNotification emailNotification, Session session) {
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(emailNotification.getFromEmail(), "shop.ecommerce"));

            msg.setReplyTo(InternetAddress.parse(emailNotification.getFromEmail(), false));

            msg.setSubject(emailNotification.getSubject(), "UTF-8");

            msg.setText(emailNotification.getBody(), "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailNotification.getToEmail(), false));
            Transport.send(msg);

            System.out.println("Email Sent Successfully!!!");
        }
        catch (Exception e) {
            throw new SendEmailFailedException(e.getMessage());
        }
    }
}
