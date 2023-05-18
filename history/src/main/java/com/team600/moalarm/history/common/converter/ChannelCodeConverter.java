package com.team600.moalarm.history.common.converter;

import com.team600.moalarm.history.common.code.ChannelCode;
import org.springframework.core.convert.converter.Converter;

public class ChannelCodeConverter implements Converter<String, ChannelCode> {

    @Override
    public ChannelCode convert(String codeStr) {
        return ChannelCode.of(codeStr);
    }
}
