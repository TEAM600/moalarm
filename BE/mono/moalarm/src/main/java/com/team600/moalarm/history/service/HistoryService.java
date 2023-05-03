package com.team600.moalarm.history.service;

import com.team600.moalarm.channel.data.code.ChannelCode;
import com.team600.moalarm.history.dto.response.HistoryResponse;
import com.team600.moalarm.history.entity.History;
import com.team600.moalarm.history.repository.HistoryRepository;
import java.util.List;
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

}
