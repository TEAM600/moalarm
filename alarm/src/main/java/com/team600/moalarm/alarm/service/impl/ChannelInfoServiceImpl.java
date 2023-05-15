package com.team600.moalarm.alarm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team600.moalarm.alarm.data.code.ChannelCode;
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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ChannelInfoServiceImpl implements ChannelInfoService {

    @Value("${url.member}")
    private static String MEMBER_URL;
    private final Map<String, SenderService> senderService;
    @Override
    public void sendAlarm(String moalarmKey, SendAlarmRequest alarmRequest) {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        Request request = new Request.Builder()
                .url(MEMBER_URL)
                .addHeader("Authorization", moalarmKey)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            String responseBody = response.body().string();
            ChannelsSecretResponse channelsSecretResponse = objectMapper.readValue(responseBody, ChannelsSecretResponse.class);
            long memberId = channelsSecretResponse.getMemberId();
            List<ChannelKeyDto> channelKeyDtos = channelsSecretResponse.getChannelKeys();

            channelKeyDtos.forEach(channelKeyDto -> {
                ChannelCode type = channelKeyDto.getType();
                senderService.get(type.getValue() + "SenderServiceImpl")
                        .send(memberId, alarmRequest, channelKeyDto);
            });
        } catch (IOException e) {
            throw new MoalarmKeySendFailedException();
        }
    }
}
