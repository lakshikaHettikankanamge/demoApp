package com.example.demo8.controller;

import com.example.demo8.model.EmailCfg;
import com.example.demo8.model.Feedback;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/activationemail")
@AllArgsConstructor
@Slf4j
public class EmailController {
    private EmailCfg emailCfg;

    
    @PostMapping
    public void sendfeedback(@RequestBody Feedback feedback
    , BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("email not valid");
        }

        //create a mailSender
        JavaMailSenderImpl mailSend = new JavaMailSenderImpl();
        mailSend.setHost(this.emailCfg.getHost());
        mailSend.setPort(this.emailCfg.getPort());
        mailSend.setUsername(this.emailCfg.getUsername());
        mailSend.setPassword(this.emailCfg.getPassword());

        //create email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(feedback.getMail());
        mailMessage.setTo("nini@gmail.com");
        mailMessage.setSubject("feedback from" +feedback.getName());
        mailMessage.setText(feedback.getFeedback());

        //send mail
        mailSend.send(mailMessage);
    }


}
