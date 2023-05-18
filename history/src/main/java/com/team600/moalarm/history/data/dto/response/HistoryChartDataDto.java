package com.team600.moalarm.history.data.dto.response;

import com.team600.moalarm.history.common.code.ChannelCode;
import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryChartDataDto {

    @Enumerated(EnumType.ORDINAL)
    private ChannelCode type;
    private Date date;
    private long count;
}
