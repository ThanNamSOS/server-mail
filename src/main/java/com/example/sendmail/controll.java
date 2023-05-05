package com.example.sendmail;

import com.example.sendmail.service.EmailBody;
import com.example.sendmail.service.EmailRequest;
import com.example.sendmail.service.EmailService;
import com.example.sendmail.service.EmailUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/admin/v1")
public class controll {
    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/send")
    public ResponseEntity<?> get() {
        CompletableFuture.runAsync(() -> {
            try {
                System.out.println("start send email");
                emailService.sendEmail(new EmailRequest(new String[] { "namtvph13393@fpt.edu.vn" }, null, null,
                        EmailUtil.getNewOrderEmailSubject("Test Send Email"), EmailUtil.getNewOrderEmailContent(),
                        true));
                System.out.println("stop send email");
            } catch (UnsupportedEncodingException e) {
                System.out.println("UnsupportedEncodingException");
                e.printStackTrace();
            } catch (MessagingException e) {
                System.out.println("MessagingException");
                e.printStackTrace();
            }
        });
        return ResponseEntity.ok("OK");
    }


    @PostMapping(value = "/smtp-mail-api/api/smtp/mail")
    public ResponseEntity<?> get2(@RequestBody EmailBody emailBody) {
//        final String fromEmail = "thannam27062001@gmail.com"; //requires valid gmail id
//        final String password = "piwxqtsinrbyffza"; // correct password for gmail id
        final String fromEmail = "thannam27062001@gmail.com"; //requires valid gmail id
        final String password = "piwxqtsinrbyffza"; // correct password for gmail id
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        EmailUtil.sendEmail(session,emailBody);

        return ResponseEntity.ok("OK");
    }

    @PostMapping(value = "/smtp-mail-api/api/smtp/mail-vsi")
    public ResponseEntity<?> get3(@RequestBody EmailBody emailBody) {
        final String fromEmail = "than.van.nam@vsi-international.com";
        final String password = "0971265513Aa"; // correct password for gmail id
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.vsi-international.com"); //SMTP Host
        props.put("spring.mail.protocol", "mail.vsi-international.com"); //TLS Port
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        System.out.println("Authenticator");
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        System.out.println("session");
        Session session = Session.getInstance(props, auth);

        EmailUtil.sendEmail(session,emailBody);

        return ResponseEntity.ok("OK");
    }
    @PostMapping(value = "/smtp-mail-api/api/smtp/mail-vsii")
    public ResponseEntity<?> get4(@RequestBody EmailBody emailBody) {
        try {
            System.out.println("Call...");
            String SMTP_HOST = "smtp.vsi-international.com";
            String SMTP_USER = "than.van.nam";
            String SMTP_PASSWORD = "0971265513Aa";
            String SMTP_PORT = "587";

            final Properties props = new Properties();
            props.put("mail.smtp.host", SMTP_HOST);
            props.put("mail.smtp.port", SMTP_PORT);
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.ssl.enable", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.tls", "true");
            props.put("mail.smtp.ssl.checkserveridentity", "true");

            final javax.mail.Authenticator auth = new javax.mail.Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(SMTP_USER, SMTP_PASSWORD);
                }
            };

            Session session = Session.getInstance(props, auth);
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("than.van.nam@vsi-international.com", "Than van nam"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress("namtvph13393@fpt.edu.vn" ));
            msg.setSubject("SUBJECT");
            msg.setText("THE MESSAGE");
            msg.saveChanges();
            System.out.println("SEND....");
            Transport.send(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("OK");
    }

}
