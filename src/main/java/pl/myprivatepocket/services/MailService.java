package pl.myprivatepocket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

        @Autowired
        public JavaMailSender emailSender;

        public void sendSimpleMessage(String to, String text) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("myprivatepocket@wp.pl");
            message.setTo(to);
            message.setSubject("Invitation to an article - MyPrivatePocket");
            message.setText(text);
            emailSender.send(message);
        }
    }
