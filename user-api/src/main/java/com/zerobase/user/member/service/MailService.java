package com.zerobase.user.member.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.zerobase.user.exception.CustomException;
import com.zerobase.user.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {
    
    @Value("${spring.mail.username}")
    private String fromAddress;
    @Value("${server.host}")
    private String serverHost;

    private final JavaMailSender mailSender;
    private final ResourceLoader resourceLoader;

    private void sendMail(String address, String subject, String message) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setSubject(subject);
            helper.setText(message, true);
            helper.setFrom(new InternetAddress(fromAddress, "from", "UTF-8"));
            helper.setTo(new InternetAddress(address, "to", "UTF-8"));
            mailSender.send(mimeMessage);

        } catch (Exception ex) {
            throw new CustomException(ErrorCode.MAIL_SEND_FAIL);
        }
    }

    public void sendSignUpVerify(String email, String userName, String uuid) {
        ClassPathResource resource = (ClassPathResource) resourceLoader
                .getResource("classpath:static/mailTemplate/SignUpMailVerify.html");
        if (resource == null) {
            throw new CustomException(ErrorCode.CANNOT_FIND_MAIL_TEMPLATE);
        }

        try {
            File file = resource.getFile();
            FileReader reader = new FileReader(file, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line = "";
            String html = "";
            while ((line = bufferedReader.readLine()) != null) {
                html += line;
            }

            bufferedReader.close();
            reader.close();

            html = html.replace("${username}", userName);
            html = html.replace("${validateUrl}", serverHost + "/api/auth/sign-up-verify?verifycode=" + uuid);

            this.sendMail(email, "회원 인증 메일", html);
        } catch (IOException ex) {
            throw new CustomException(ErrorCode.CANNOT_FIND_MAIL_TEMPLATE);
        }



    }
}