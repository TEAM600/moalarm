package com.team600.moalarm.alarm.service.impl;

import com.team600.moalarm.alarm.dto.request.SendAlarmRequest;
import com.team600.moalarm.alarm.dto.request.SendMailRequest;
import com.team600.moalarm.alarm.service.SenderService;
import com.team600.moalarm.channel.data.dto.ChannelKeyDto;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailSenderServiceImpl implements SenderService {

    private static int socketPort = 465;
    private static boolean auth = true;
    private static boolean starttls = true;
    private static boolean startlls_required = true;
    private static boolean fallback = false;

    @Override
    public void send(SendAlarmRequest requirementDto, ChannelKeyDto channelKeyDto) {
        SendMailRequest sendMailRequest = requirementDto.getMail();
        JavaMailSender emailSender = setMailService(channelKeyDto);

        try {
            List<String> receivers = sendMailRequest.getTo();
            for (String r:receivers) {
                MimeMessage message = createMessage(r, emailSender, sendMailRequest);
                sendEmailAsync(message, emailSender);
            }
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    private JavaMailSender setMailService(ChannelKeyDto channelKeyDto){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setUsername(channelKeyDto.getApiKey());
        javaMailSender.setPassword(channelKeyDto.getSecret());
        javaMailSender.setPort(465);
        javaMailSender.setJavaMailProperties(getMailProperties());
        javaMailSender.setDefaultEncoding("UTF-8");
        return javaMailSender;
    }

    private Properties getMailProperties()
    {
        Properties pt = new Properties();
        pt.put("mail.smtp.socketFactory.port", socketPort);
        pt.put("mail.smtp.auth", auth);
        pt.put("mail.smtp.starttls.enable", starttls);
        pt.put("mail.smtp.starttls.required", startlls_required);
        pt.put("mail.smtp.socketFactory.fallback",fallback);
        pt.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return pt;
    }

    CompletableFuture<Void> sendEmailAsync(MimeMessage message,
            JavaMailSender javaMailSender) {
        return CompletableFuture.runAsync(() -> {
            try {
                javaMailSender.send(message);
            } catch (Exception e) {
                //TODO: Handle exception
                throw new RuntimeException(e);
            }
        });
    }
    private MimeMessage createMessage(String to, JavaMailSender emailSender,
            SendMailRequest requirementDto) throws MessagingException, UnsupportedEncodingException {

        MimeMessage  message = emailSender.createMimeMessage();

        message.addRecipients(RecipientType.TO, to);//보내는 대상
        message.setSubject(requirementDto.getTitle());//제목

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= requirementDto.getContent();
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("leewonyoung2323@gmail.com","LEEWONYOUNG"));//보내는 사람

        return message;
    }
}
