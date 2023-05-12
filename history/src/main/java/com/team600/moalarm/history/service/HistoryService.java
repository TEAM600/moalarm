package com.team600.moalarm.history.service;

import com.team600.moalarm.history.common.code.ChannelCode;
import com.team600.moalarm.history.data.dto.request.HistoryCreateRequest;
import com.team600.moalarm.history.data.dto.response.HistoryChartDataDto;
import com.team600.moalarm.history.data.dto.response.HistoryChartResponse;
import com.team600.moalarm.history.data.dto.response.HistoryResponse;
import com.team600.moalarm.history.data.entity.History;
import com.team600.moalarm.history.data.repository.HistoryRepository;
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
    public void createHistory(long memberId, HistoryCreateRequest request) {
        log.info("request: {}", request);
        History history = History.builder()
                .memberId(memberId)
                .type(request.getType())
                .receiver(request.getReceiver())
                .success(request.getSuccess())
                .build();

        historyRepository.save(history);
    }
}