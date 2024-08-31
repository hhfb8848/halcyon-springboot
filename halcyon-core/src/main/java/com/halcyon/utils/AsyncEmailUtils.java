package com.halcyon.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.halcyon.constant.Constants.PLATFORM_NAME;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-08-03 15:46
 * @description: 如邮件发送失败，将异步代码删除再调试
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AsyncEmailUtils {


    private final JavaMailSender mailSender;

    private final Configuration freemarkerConfig;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Async
    public CompletableFuture<Void> sendEmailAsync(String toEmail, String subject, String code) {
        return CompletableFuture.runAsync(() -> {
            try {
                Template template = freemarkerConfig.getTemplate("mail.ftl");

                Map<String, Object> model = new HashMap<>();
                model.put("title", subject);
                model.put("verificationCode", code);
                model.put("platformName", PLATFORM_NAME);
                String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setFrom(fromEmail);
                helper.setTo(toEmail);
                helper.setSubject(subject);
                helper.setText(content, true);
                mailSender.send(mimeMessage);
                log.info("向邮箱{}发送验证码：{}", toEmail, code);
            } catch (IOException | TemplateException | MessagingException e) {
                log.error("验证码发送失败");
                e.printStackTrace();
            }
        });
    }
}
