/*
 * @ {#} EmailService.java   1.0     11/15/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   11/15/2024
 * @version:    1.0
 */
@Service
public class EmailService {
    private JavaMailSender mailSender;

    // Constructor injection dùng để inject JavaMailSender vào EmailService class
    public void sendInvitationEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("duongnguyenqn1323@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}

