package com.team600.moalarm.history.service;

import com.team600.moalarm.history.common.code.ChannelCode;
import com.team600.moalarm.history.data.dto.request.AlarmRequestCreateRequest;
import com.team600.moalarm.history.data.dto.request.HistoryCreateRequest;
import com.team600.moalarm.history.data.dto.response.HistoryChartDataDto;
import com.team600.moalarm.history.data.dto.response.HistoryChartResponse;
import com.team600.moalarm.history.data.dto.response.HistoryDetailResponse;
import com.team600.moalarm.history.data.dto.response.HistoryResponse;
import com.team600.moalarm.history.data.entity.AlarmRequest;
import com.team600.moalarm.history.data.entity.History;
import com.team600.moalarm.history.data.repository.AlarmRequestRepository;
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

    private final AlarmRequestRepository alarmRequestRepository;
    private final HistoryRepository historyRepository;

    @Transactional
    public List<HistoryResponse> getHistory(long memberId) {
        List<AlarmRequest> alarmRequestList = alarmRequestRepository.findAllByMemberId(memberId);
        List<HistoryResponse> responseList = new ArrayList<>();

        for (AlarmRequest alarmRequest : alarmRequestList) {
            int doneCnt = alarmRequest.getAlarmCnt();
            if (alarmRequest.getDoneYn().equals("N")) {
                doneCnt = historyRepository.countByAlarmRequestId(alarmRequest.getId());

                if (doneCnt == alarmRequest.getAlarmCnt()) {
                    alarmRequest.setDone();
                }
            }

            responseList.add(HistoryResponse.builder()
                            .dateTime(alarmRequest.getCreatedAt())
                            .alarmRequestId(alarmRequest.getId())
                            .alarmCnt(alarmRequest.getAlarmCnt())
                            .doneCnt(doneCnt).build());
        }

        return responseList;
    }

    @Transactional(readOnly = true)
    public List<HistoryDetailResponse> getHistoryByRequestId(long memberId, long requestId) {
        List<History> historyList = historyRepository.findAllByMemberIdAndAlarmRequestId(memberId, requestId);

        return historyList.stream()
                .map(history -> HistoryDetailResponse.builder()
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
    public long createAlarmRequest(long memberId, AlarmRequestCreateRequest request) {
        log.info("request: {}", request);
        AlarmRequest alarmRequest = AlarmRequest.builder()
                .memberId(memberId)
                .alarmCnt(request.getAlarmCnt())
                .doneYn("N")
                .build();

        long requestId = alarmRequestRepository.save(alarmRequest).getId();
        return requestId;
    }

    @Transactional
    public void createHistory(long memberId, HistoryCreateRequest request) {
        log.info("request: {}", request);
        History history = History.builder()
                .memberId(memberId)
                .alarmRequestId(request.getAlarmRequestId())
                .type(request.getType())
                .receiver(request.getReceiver())
                .success(request.getSuccess())
                .build();

        historyRepository.save(history);
    }
}
