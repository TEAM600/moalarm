package com.team600.moalarm.member.channel.service;


import com.team600.moalarm.member.channel.data.dto.request.ChannelCreateRequest;

public interface ChannelSaveService {

    void saveChannel(ChannelCreateRequest requestDto, long memberId);
}
