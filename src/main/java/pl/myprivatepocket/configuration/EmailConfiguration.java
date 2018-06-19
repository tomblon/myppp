package pl.myprivatepocket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfiguration {
    @Bean
    @Primary
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.wp.pl");
        mailSender.setPort(465);

        mailSender.setUsername("myprivatepocket@wp.pl");
        mailSender.setPassword("invalidControllerConfigurationEngine");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtps.ssl.checkserveridentity", "true");
        props.put("mail.smtps.ssl.trust", "*");
        props.put("mail.debug", "true");

        return mailSender;
    }
}