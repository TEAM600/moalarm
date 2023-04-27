package com.team600.moalarm.channel.data.code.converter;

import com.team600.moalarm.channel.data.code.ChannelCode;
import org.springframework.core.convert.converter.Converter;

public class ChannelCodeConverter implements Converter<String, ChannelCode> {

    @Override
    public ChannelCode convert(String codeStr) {
        return ChannelCode.of(codeStr);
    }
}
