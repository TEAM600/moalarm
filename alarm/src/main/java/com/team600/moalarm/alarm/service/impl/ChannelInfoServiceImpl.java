package com.team600.moalarm.alarm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.team600.moalarm.alarm.data.code.ChannelCode;
import com.team600.moalarm.alarm.data.dto.request.SendMailRequest;
import com.team600.moalarm.alarm.data.dto.request.SendPushRequest;
import com.team600.moalarm.alarm.data.dto.request.SendSmsRequest;
import com.team600.moalarm.alarm.data.dto.response.AlarmRequestCreateResponse;
import com.team600.moalarm.alarm.data.dto.response.ChannelKeyDto;
import com.team600.moalarm.alarm.data.dto.response.ChannelsSecretResponse;
import com.team600.moalarm.alarm.data.dto.request.SendAlarmRequest;
import com.team600.moalarm.alarm.exception.MoalarmKeySendFailedException;
import com.team600.moalarm.alarm.service.ChannelInfoService;
import com.team600.moalarm.alarm.service.SenderService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ChannelInfoServiceImpl implements ChannelInfoService {

    @Value("${url.member}")
    private String MEMBER_URL;
    @Value("${url.alarmRequest}")
    private String ALARM_REQUEST_URL;
    private final Map<String, SenderService> senderService;
    @Override
    public void sendAlarm(String moalarmKey, SendAlarmRequest alarmRequest) {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        Request memberRequeset = new Request.Builder()
                .url(MEMBER_URL)
                .addHeader("Authorization", moalarmKey)
                .build();

        Response response = null;
        try {
            // memberId, channelKey 얻어오기
            response = client.newCall(memberRequeset).execute();
            String responseBody = response.body().string();
            ChannelsSecretResponse channelsSecretResponse = objectMapper.readValue(responseBody, ChannelsSecretResponse.class);
            long memberId = channelsSecretResponse.getMemberId();
            List<ChannelKeyDto> channelKeyDtos = channelsSecretResponse.getChannelKeys();

            // AlarmRequest 테이블에 추가 요청
            int alarmCnt = 0;

            SendMailRequest sendMailRequest = alarmRequest.getMail();
            if (sendMailRequest != null) alarmCnt += sendMailRequest.getTo().size();

            SendSmsRequest sendSmsRequest = alarmRequest.getSms();
            if (sendSmsRequest != null) alarmCnt += sendSmsRequest.getTo().size();

            SendPushRequest sendPushRequest = alarmRequest.getPush();
            if (sendPushRequest != null) alarmCnt += sendPushRequest.getTo().size();

            JsonObject createHistory = new JsonObject();
            createHistory.addProperty("alarmCnt", alarmCnt);
            RequestBody requestBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), createHistory.toString());

            Request alarmRequestCreateRequest = new Request.Builder()
                    .url(ALARM_REQUEST_URL)
                    .addHeader("Member-Id", String.valueOf(memberId))
                    .post(requestBody)
                    .build();

            Response alarmResponse = client.newCall(alarmRequestCreateRequest).execute();
            String alarmResponseBody = alarmResponse.body().string();
            AlarmRequestCreateResponse alarmRequestCreateResponse = objectMapper.readValue(alarmResponseBody, AlarmRequestCreateResponse.class);
            long requestId = alarmRequestCreateResponse.getAlarmRequestId();

            // 알림 send
            channelKeyDtos.forEach(channelKeyDto -> {
                ChannelCode type = channelKeyDto.getType();
                senderService.get(type.getValue() + "SenderServiceImpl")
                        .send(memberId, requestId, alarmRequest, channelKeyDto);
            });
        } catch (IOException e) {
            throw new MoalarmKeySendFailedException();
        }
    }
}
