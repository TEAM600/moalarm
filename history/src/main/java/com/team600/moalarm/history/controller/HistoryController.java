package com.team600.moalarm.history.controller;

import com.team600.moalarm.history.common.annotation.CurrentMemberId;
import com.team600.moalarm.history.data.dto.request.AlarmRequestCreateRequest;
import com.team600.moalarm.history.data.dto.request.HistoryCreateRequest;
import com.team600.moalarm.history.data.dto.response.AlarmRequestCreateResponse;
import com.team600.moalarm.history.data.dto.response.HistoryChartResponse;
import com.team600.moalarm.history.data.dto.response.HistoryDetailResponse;
import com.team600.moalarm.history.data.dto.response.HistoryResponse;
import com.team600.moalarm.history.service.HistoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping
    public ResponseEntity<List<HistoryResponse>> getHistory(@CurrentMemberId long memberId) {
        log.info("GET /history {}", memberId);
        List<HistoryResponse> body = historyService.getHistory(memberId);
        return ResponseEntity.ok()
                .body(body);
    }

    @GetMapping("/chart")
    public ResponseEntity<HistoryChartResponse> getHistoryChart(@CurrentMemberId long memberId,
            @RequestParam int period) {
        log.info("GET /history/chart?period={}", period);
        HistoryChartResponse body = historyService.getHistoryChart(memberId, period);
        return ResponseEntity.ok()
                .body(body);
    }

    @PostMapping("/alarmRequest")
    public ResponseEntity<AlarmRequestCreateResponse> createAlarmRequest(@CurrentMemberId long memberId, @RequestBody AlarmRequestCreateRequest request) {
        log.info("POST /history/alarmRequest");
        long requestId = historyService.createAlarmRequest(memberId, request);
        return ResponseEntity.ok()
                .body(AlarmRequestCreateResponse.builder().alarmRequestId(requestId).build());
    }

    @PostMapping
    public ResponseEntity<Void> createHistory(@CurrentMemberId long memberId, @RequestBody HistoryCreateRequest request) {
        log.info("POST /history");
        historyService.createHistory(memberId, request);
        return ResponseEntity.ok()
                .build();
    }

}
