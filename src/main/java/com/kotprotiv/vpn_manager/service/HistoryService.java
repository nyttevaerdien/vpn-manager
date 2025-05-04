package com.kotprotiv.vpn_manager.service;
import com.kotprotiv.vpn_manager.model.HistoryElement;
import com.kotprotiv.vpn_manager.repo.HistoryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepo historyRepo;

    public List<HistoryElement> findLast10() {
        return historyRepo.findTop10ByOrderByStartedDesc();
    }

    public HistoryElement start(ZoneId zone) {
        var historyElement = new HistoryElement();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        historyElement.setUsername(auth.getName());
//        historyElement.setIp();
        historyElement.setStarted(LocalDateTime.now().atZone(zone));
        historyElement.setStatus(HistoryElement.Status.IN_PROGRESS);
        return historyRepo.save(historyElement);
    }

    public void finishSuccessfully(HistoryElement historyElement, ZoneId zone) {
        historyElement.setFinished(LocalDateTime.now().atZone(zone));
        historyElement.setStatus(HistoryElement.Status.SUCCESS);
        historyRepo.save(historyElement);
    }

    public void finishWithError(HistoryElement historyElement, ZoneId zone) {
        historyElement.setFinished(LocalDateTime.now().atZone(zone));
        historyElement.setStatus(HistoryElement.Status.FAILED);
        historyRepo.save(historyElement);
    }
}