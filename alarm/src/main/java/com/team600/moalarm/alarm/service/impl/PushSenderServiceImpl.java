package com.team600.moalarm.alarm.service.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.team600.moalarm.alarm.data.code.ChannelCode;
import com.team600.moalarm.alarm.data.dto.response.ChannelKeyDto;
import com.team600.moalarm.alarm.data.dto.request.SendAlarmRequest;
import com.team600.moalarm.alarm.data.dto.request.SendPushRequest;
import com.team600.moalarm.alarm.exception.PushSendFailedException;
import com.team600.moalarm.alarm.service.HistoryService;
import com.team600.moalarm.alarm.service.SenderService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PushSenderServiceImpl implements SenderService {

    private final HistoryService historyService;

    @Override
    public void send(long memberId, long requestId, SendAlarmRequest requirementDto, ChannelKeyDto channelKeyDto) {
        SendPushRequest sendPushRequest = requirementDto.getPush();
        if (sendPushRequest == null) {
            return;
        }

        try {
            init(channelKeyDto.getExtraValue());

            List<String> receivers = sendPushRequest.getTo();
            String title = sendPushRequest.getTitle();
            String content = sendPushRequest.getContent();
            String img = sendPushRequest.getImg();

            for (String receiver : receivers) {
                Message message = createMessage(receiver, title, content, img);
                sendPushAsync(memberId, requestId, receiver, message);
            }
        } catch (IOException e) {
            throw new PushSendFailedException();
        }
    }

    private void init(String key) throws IOException {
        InputStream refreshToken = new ByteArrayInputStream(key.getBytes());

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .build();

        FirebaseApp.initializeApp(options);
    }

    private Message createMessage(String token, String title, String body,
            String image) {
        return Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .setImage(image)
                        .build())
                .setToken(token)
                .build();
    }

    CompletableFuture<Void> sendPushAsync(long memberId, long requestId, String receiver, Message message) {
        return CompletableFuture.runAsync(() -> {
            try {
                FirebaseMessaging.getInstance().send(message);
                historyService.postHistory(memberId, requestId, receiver, ChannelCode.PUSH,"Y");
            } catch (Exception e) {
                historyService.postHistory(memberId, requestId, receiver, ChannelCode.PUSH,"N");
            }
        });
    }
}
