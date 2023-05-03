package com.team600.moalarm.history.controller;

import com.team600.moalarm.channel.data.code.ChannelCode;
import com.team600.moalarm.common.annotation.CurrentMemberId;
import com.team600.moalarm.history.dto.response.HistoryChartResponse;
import com.team600.moalarm.history.dto.response.HistoryResponse;
import com.team600.moalarm.history.service.HistoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping
    public ResponseEntity<List<HistoryResponse>> getHistory(@CurrentMemberId long memberId) {
        log.info("GET /history");
        List<HistoryResponse> body = historyService.getHistory(memberId);
        return ResponseEntity.ok()
                .body(body);
    }

    @GetMapping("/chart")
    public ResponseEntity<?> getHistoryChart(@CurrentMemberId long memberId) {
        log.info("GET /history/chart");
        HistoryChartResponse body = historyService.getHistoryChart(memberId);
        return ResponseEntity.ok()
                .body(body);
    }

    @PostMapping
    public ResponseEntity<Void> createHistory(@CurrentMemberId long memberId) {
        log.info("POST /history");
        ChannelCode channelCode = ChannelCode.SMS;
        historyService.createHistory(memberId, channelCode, "010-1234-5678", "Y");
        return ResponseEntity.ok()
                .build();
    }

}
