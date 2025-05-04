package com.kotprotiv.vpn_manager.service;

import com.kotprotiv.vpn_manager.model.HistoryElement;
import com.kotprotiv.vpn_manager.model.RestartResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Slf4j
@Component
@RequiredArgsConstructor
public class SshService {

    private final CommandExecutor commandExecutor;
    private final HistoryService historyService;

    public RestartResult executeCommand(String command, ZoneId zone) {
        HistoryElement historyElement = historyService.start(zone);
        try {
            String result = commandExecutor.execute(command);
            RestartResult restartResult = new RestartResult(true, result);
            historyService.finishSuccessfully(historyElement, zone);
            log.info("Success: {}", restartResult);
            return restartResult;
        } catch (Exception e) {
            historyService.finishWithError(historyElement, zone);
            String message = "Error executing command: " + e.getMessage();
            log.error(message);
            return new RestartResult(false, message);
        }
    }
}