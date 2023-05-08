package com.team600.moalarm.history.service;

import com.team600.moalarm.channel.data.code.ChannelCode;
import com.team600.moalarm.history.dto.response.HistoryChartDataDto;
import com.team600.moalarm.history.dto.response.HistoryChartResponse;
import com.team600.moalarm.history.dto.response.HistoryResponse;
import com.team600.moalarm.history.entity.History;
import com.team600.moalarm.history.repository.HistoryRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    @Transactional(readOnly = true)
    public List<HistoryResponse> getHistory(long memberId) {
        List<History> historyList = historyRepository.findAllByMemberId(memberId);

        return historyList.stream()
                .map(history -> HistoryResponse.builder()
                        .dateTime(history.getCreatedAt())
                        .type(history.getType().name())
                        .receiver(history.getReceiver())
                        .success(history.getSuccess())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public HistoryChartResponse getHistoryChart(long memberId, int period) {
        LocalDate today = LocalDate.now();
        LocalDate start = today.minusDays(period - 1L);

        List<HistoryChartDataDto> chartDataset = historyRepository.getHistoryChartDateset(
                memberId, start, today);

        List<LocalDate> labels = new ArrayList<>();
        while (!start.isAfter(today)) {
            labels.add(start);
            start = start.plusDays(1);
        }

        Map<String, List<Integer>> dataset = new HashMap<>();

        for (ChannelCode channelCode : ChannelCode.values()) {
            dataset.put(channelCode.name(), new ArrayList<>(Collections.nCopies(period, 0)));
        }

        for (HistoryChartDataDto chartData : chartDataset) {
            LocalDate date = LocalDate.parse(chartData.getDate().toString());
            int daysBetween = (int) ChronoUnit.DAYS.between(date, today);
            int index = period - daysBetween - 1;
            dataset.get(chartData.getType().name()).set(index, (int) chartData.getCount());
        }

        return HistoryChartResponse.builder()
                .labels(labels)
                .dataset(dataset)
                .build();
    }

    @Transactional
    public void createHistory(long memberId, ChannelCode type, String receiver, String success) {
        History history = History.builder()
                .memberId(memberId)
                .type(type)
                .receiver(receiver)
                .success(success)
                .build();

        historyRepository.save(history);
    }
}
