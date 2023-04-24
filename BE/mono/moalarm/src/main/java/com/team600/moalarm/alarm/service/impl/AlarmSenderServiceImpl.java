package com.team600.moalarm.alarm.service.impl;

import com.team600.moalarm.alarm.dto.request.AlarmRequirementDto;
import com.team600.moalarm.alarm.dto.request.MailRequirementDto;
import com.team600.moalarm.alarm.dto.request.SmsRequirementDto;
import com.team600.moalarm.alarm.service.AlarmSenderService;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlarmSenderServiceImpl implements AlarmSenderService {

    @Override
    public void sendAlarms(AlarmRequirementDto requirementDto) {
        sendEmail(requirementDto.getMail());
        sendSms(requirementDto.getSms());
    }
    private JavaMailSender javaMailService(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setUsername("db에서 갖고온 이메일");
        javaMailSender.setPassword("db에서 갖고온 pw");
        javaMailSender.setPort(465);
        javaMailSender.setJavaMailProperties(getMailProperties());
        javaMailSender.setDefaultEncoding("UTF-8");
        return javaMailSender;
    }

    private Properties getMailProperties()
    {
        Properties pt = new Properties();
        pt.put("mail.smtp.socketFactory.port", 465);
        pt.put("mail.smtp.auth", true);
        pt.put("mail.smtp.starttls.enable", true);
        pt.put("mail.smtp.starttls.required", true);
        pt.put("mail.smtp.socketFactory.fallback",false);
        pt.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return pt;
    }

    private void sendEmail(MailRequirementDto requirementDto) {
        JavaMailSender emailSender = javaMailService();
        try {
            List<String> receivers = requirementDto.getTo();
            List<CompletableFuture<Void>> futures = new ArrayList<>();
            int idx = 0;
            for (String r:receivers) {
                String email = r;
                MimeMessage message = createMessage(r, emailSender, requirementDto);
                futures.add(sendEmailAsync(r, message, emailSender, idx++));
            }
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    CompletableFuture<Void> sendEmailAsync(String recipient, MimeMessage message,
            JavaMailSender javaMailSender, int idx) {
        return CompletableFuture.runAsync(() -> {
            try {
                log.info("start : {}", idx);
                javaMailSender.send(message);
                log.info("end : {}", idx);
            } catch (Exception e) {
                // Handle exception
                throw new RuntimeException(e);
            }
        });
    }
    private MimeMessage createMessage(String to, JavaMailSender emailSender,
            MailRequirementDto requirementDto) throws MessagingException, UnsupportedEncodingException {

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

    private void sendSms(SmsRequirementDto requirementDto) {
//        DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("API 키 입력", "API 시크릿 키 입력", "https://api.coolsms.co.kr");
        DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("db에서 갖고온 api키", "db에서 갖고온 시크릿 키", "https://api.coolsms.co.kr");
        //TODO: DB에서 갖고올 예정
        String apiKey = "";
        String apiSecrect = "";

        try {
            List<String> receivers = requirementDto.getTo();
            String content = requirementDto.getContent();
            for (String r:receivers) {
                makeSms(r, content, messageService);
            }
        } catch (NurigoMessageNotReceivedException exception) {
            // 발송에 실패한 메시지 목록을 확인할 수 있습니다!
            System.out.println(exception.getFailedMessageList());
            System.out.println(exception.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Async
    void makeSms(String receiver, String content, DefaultMessageService messageService)
            throws NurigoMessageNotReceivedException, NurigoEmptyResponseException, NurigoUnknownException {
        Message message = new Message();
        //TODO: DB에서 갖고올 예정
        message.setFrom("db에서 갖고온 전화번호");
        message.setTo(receiver);
        message.setText(content);

        messageService.send(message);
    }
}
