package com.team600.moalarm.alarm.service;

import com.team600.moalarm.alarm.data.code.ChannelCode;

public interface HistoryService {
    public void postHistory(long memberId, String receiver, ChannelCode code, String success);
}
