package com.team600.moalarm.alarm.service.impl;

import com.google.gson.JsonObject;
import com.team600.moalarm.alarm.data.code.ChannelCode;
import com.team600.moalarm.alarm.exception.HistorySendFailedException;
import com.team600.moalarm.alarm.service.HistoryService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class HistoryServiceImpl implements HistoryService {

    @Value("${url.history}")
    private String HISTORY_URL;

    @Override
    public void postHistory(long memberId, long requestId, String receiver, ChannelCode code, String success) {
        OkHttpClient client = new OkHttpClient();
        JsonObject createHistory = new JsonObject();
        createHistory.addProperty("alarmRequestId", requestId);
        createHistory.addProperty("type", code.toString());
        createHistory.addProperty("receiver", receiver);
        createHistory.addProperty("success", success);
        RequestBody requestBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), createHistory.toString());

        Request request = new Request.Builder()
                .url(HISTORY_URL)
                .addHeader("Member-Id", String.valueOf(memberId))
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new HistorySendFailedException();
        }
    }
}
