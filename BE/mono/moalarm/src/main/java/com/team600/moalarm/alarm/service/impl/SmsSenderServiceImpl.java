package com.team600.moalarm.alarm.service.impl;

import com.team600.moalarm.alarm.dto.request.SendAlarmRequest;
import com.team600.moalarm.alarm.dto.request.SendSmsRequest;
import com.team600.moalarm.alarm.service.SenderService;
import com.team600.moalarm.channel.data.dto.ChannelKeyDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmsSenderServiceImpl implements SenderService {
    public void send(SendAlarmRequest requirementDto, ChannelKeyDto channelKeyDto) {
//        DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("API 키 입력", "API 시크릿 키 입력", "https://api.coolsms.co.kr");
        //TODO: DB에서 갖고올 예정
        SendSmsRequest sendSmsRequest = requirementDto.getSms();
        String apiKey = channelKeyDto.getApiKey();
        String apiSecrect = channelKeyDto.getSecret();
        String phone = channelKeyDto.getPhoneNumber();
        DefaultMessageService messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecrect, "https://api.coolsms.co.kr");

        try {
            List<String> receivers = sendSmsRequest.getTo();
            String content = sendSmsRequest.getContent();
            for (String r:receivers) {
                setSms(r, content, messageService, phone);
            }
        } catch (NurigoMessageNotReceivedException exception) {
            // 발송에 실패한 메시지 목록을 확인할 수 있습니다!
            System.out.println(exception.getFailedMessageList());
            System.out.println(exception.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    void setSms(String receiver, String content, DefaultMessageService messageService, String phone)
            throws NurigoMessageNotReceivedException, NurigoEmptyResponseException, NurigoUnknownException {
        Message message = new Message();
        //TODO: DB에서 갖고올 예정
        message.setFrom(phone);
        message.setTo(receiver);
        message.setText(content);

        sendMessage(message, messageService);
    }

    @Async
    void sendMessage(Message message, DefaultMessageService messageService)
            throws NurigoMessageNotReceivedException, NurigoEmptyResponseException, NurigoUnknownException {
        messageService.send(message);
    }
}
