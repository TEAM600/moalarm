package com.team600.moalarm.channel.service;

import com.team600.moalarm.channel.data.code.ChannelCode;
import com.team600.moalarm.channel.data.dto.ChannelKeyDto;
import com.team600.moalarm.channel.data.dto.response.ChannelPossessionResponse;
import java.util.List;
import java.util.Map;

public interface ChannelService {

    List<ChannelPossessionResponse> getPossessions(String memberId);

    Map<ChannelCode, ChannelKeyDto> getChannelKeyList(String moalarmKey);
}
