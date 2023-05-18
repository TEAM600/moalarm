package com.team600.moalarm.history.data.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HistoryChartResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM.dd")
    private List<LocalDate> labels;

    private Map<String, List<Integer>> dataset;

}
