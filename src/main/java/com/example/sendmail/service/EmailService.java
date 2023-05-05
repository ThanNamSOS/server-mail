package com.example.sendmail.service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {
    void sendEmail(EmailRequest emailRequest) throws MessagingException, UnsupportedEncodingException;
}
