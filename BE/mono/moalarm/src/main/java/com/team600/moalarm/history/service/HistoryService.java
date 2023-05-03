package com.team600.moalarm.history.service;

import com.team600.moalarm.channel.data.code.ChannelCode;
import com.team600.moalarm.history.dto.response.HistoryChartDatasetDto;
import com.team600.moalarm.history.dto.response.HistoryChartResponse;
import com.team600.moalarm.history.dto.response.HistoryResponse;
import com.team600.moalarm.history.entity.History;
import com.team600.moalarm.history.repository.HistoryRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

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

    public void createHistory(long memberId, ChannelCode type, String receiver, String success) {
        History history = History.builder()
                .memberId(memberId)
                .type(type)
                .receiver(receiver)
                .success(success)
                .build();

        historyRepository.save(history);
    }

    public HistoryChartResponse getHistoryChart(long memberId) {
        LocalDate now = LocalDate.now();
        LocalDate start = now.minusDays(6);

        List<HistoryChartDatasetDto> chartDateset = historyRepository.getHistoryChartDateset(
                memberId, start, now);

        List<LocalDate> labels = new ArrayList<>();
        while (!start.isAfter(now)) {
            labels.add(start);
            start = start.plusDays(1);
        }

        Map<String, List<Integer>> dataset = new HashMap<>();

        for (ChannelCode channelCode : ChannelCode.values()) {
            log.info("channelCode: {}", channelCode);
            dataset.put(channelCode.name(), new ArrayList<>());
        }

        for (HistoryChartDatasetDto chartData : chartDateset) {

        }

        log.info("labels: {}", labels);
        log.info("dataset: {}", chartDateset);
        return HistoryChartResponse.builder()
                .labels(labels)
                .dataset(dataset)
                .build();
    }
}
