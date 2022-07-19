package com.finalproject.secondhand.service.user;

import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Component
public interface EmailService {
    void sendMail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException;

    String validatePasswordResetToken(String token);

}
