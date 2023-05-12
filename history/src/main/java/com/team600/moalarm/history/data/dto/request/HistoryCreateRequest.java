package com.team600.moalarm.history.data.dto.request;

import com.team600.moalarm.history.common.code.ChannelCode;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class HistoryCreateRequest {

    @Enumerated(EnumType.ORDINAL)
    private ChannelCode type;
    private String receiver;
    private String success;

}
