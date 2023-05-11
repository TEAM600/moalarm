package com.team600.moalarm.alarm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team600.moalarm.alarm.data.code.ChannelCode;
import com.team600.moalarm.alarm.data.dto.request.ChannelKeyDto;
import com.team600.moalarm.alarm.data.dto.request.ChannelsSecretRequest;
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
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ChannelInfoServiceImpl implements ChannelInfoService {

    private final Map<String, SenderService> senderService;
    @Override
    public void getChannelInfo(String moalarmKey, SendAlarmRequest alarmRequest) {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        Request request = new Request.Builder()
                .url("http://localhost:8080/api/v2/channels/secret")
                .addHeader("Authorization", moalarmKey)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            String responseBody = response.body().string();
            ChannelsSecretRequest channelsSecretRequest = objectMapper.readValue(responseBody, ChannelsSecretRequest.class);
            long memberId = channelsSecretRequest.getMemberId();
            List<ChannelKeyDto> channelKeyDtos = channelsSecretRequest.getChannelKeys();

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
