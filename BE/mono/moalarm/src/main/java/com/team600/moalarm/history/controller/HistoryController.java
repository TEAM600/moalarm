package com.team600.moalarm.history.controller;

import com.team600.moalarm.common.annotation.CurrentMemberId;
import com.team600.moalarm.history.dto.response.HistoryResponse;
import com.team600.moalarm.history.service.HistoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
        List<HistoryResponse> body = historyService.getHistory(memberId);
        return null;
    }

}
