package com.team600.moalarm.alarm.service.impl;

import com.team600.moalarm.alarm.data.code.ChannelCode;
import com.team600.moalarm.alarm.data.dto.response.ChannelKeyDto;
import com.team600.moalarm.alarm.data.dto.request.SendAlarmRequest;
import com.team600.moalarm.alarm.data.dto.request.SendSmsRequest;
import com.team600.moalarm.alarm.service.HistoryService;
import com.team600.moalarm.alarm.service.SenderService;
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

    private final HistoryService historyService;

    public void send(long memberId, long requestId, SendAlarmRequest requirementDto, ChannelKeyDto channelKeyDto) {

        SendSmsRequest sendSmsRequest = requirementDto.getSms();
        if (sendSmsRequest == null) {
            return;
        }

        String apiKey = channelKeyDto.getApiKey();
        String apiSecrect = channelKeyDto.getSecret();
        String phone = channelKeyDto.getExtraValue();
        DefaultMessageService messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecrect,
                "https://api.coolsms.co.kr");

        try {
            List<String> receivers = sendSmsRequest.getTo();
            String content = sendSmsRequest.getContent();
            for (String r : receivers) {
                sendSms(memberId, requestId, r, content, messageService, phone);
            }
        } catch (NurigoMessageNotReceivedException exception) {
            // 발송에 실패한 메시지 목록을 확인할 수 있습니다!
            System.out.println(exception.getFailedMessageList());
            System.out.println(exception.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    void sendSms(long memberId, long requestId, String receiver, String content,
            DefaultMessageService messageService, String phone)
            throws NurigoMessageNotReceivedException, NurigoEmptyResponseException, NurigoUnknownException {
        Message message = new Message();
        //TODO: DB에서 갖고올 예정
        message.setFrom(phone);
        message.setTo(receiver);
        message.setText(content);

        sendMessage(memberId, requestId, receiver, message, messageService);
    }

    @Async
    void sendMessage(long memberId, long requestId, String receiver, Message message,
            DefaultMessageService messageService) {
        try {
            messageService.send(message);
            historyService.postHistory(memberId, requestId, receiver, ChannelCode.SMS,"Y");
        } catch (NurigoMessageNotReceivedException | NurigoEmptyResponseException |
                 NurigoUnknownException e) {
            historyService.postHistory(memberId, requestId, receiver, ChannelCode.SMS,"N");
        }
    }
}
