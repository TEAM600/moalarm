package com.team600.moalarm.alarm.common.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {

    @Value("${mail.smtp.socketFactory.port}")
    private int socketPort;
    @Value("${mail.smtp.auth}")
    private boolean auth;
    @Value("${mail.smtp.starttls.enable}")
    private boolean starttls;
    @Value("${mail.smtp.starttls.required}")
    private boolean starttlsRequired;
    @Value("${mail.smtp.socketFactory.fallback}")
    private boolean fallback;
    @Value("${mail.smtp.socketFactory.class}")
    private String socketFactoryClass;

    @Value("${mail.smtp.ssl.checkServerIdentity}")
    private boolean checkServerIdentity;

    @Bean(name = "mail")
    Properties getMailProperties() {
        Properties pt = new Properties();
        pt.put("mail.smtp.socketFactory.port", socketPort);
        pt.put("mail.smtp.auth", auth);
        pt.put("mail.smtp.starttls.enable", starttls);
        pt.put("mail.smtp.starttls.required", starttlsRequired);
        pt.put("mail.smtp.socketFactory.fallback", fallback);
        pt.put("mail.smtp.ssl.checkServerIdentity", checkServerIdentity);
        pt.put("mail.smtp.socketFactory.class", socketFactoryClass);
        return pt;
    }
}
