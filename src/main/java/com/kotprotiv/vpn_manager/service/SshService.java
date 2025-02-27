package com.kotprotiv.vpn_manager.service;
import com.jcraft.jsch.*;
import com.kotprotiv.vpn_manager.model.RestartResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SshService {

    private final CommandExecutor commandExecutor;

    public RestartResult executeCommand(String command) {
        try {
            String result = commandExecutor.execute(command);
            RestartResult restartResult = new RestartResult(true, result);
            log.info("Success: {}", restartResult);
            return restartResult;
        } catch (Exception e) {
            String message = "Error executing command: " + e.getMessage();
            log.error(message);
            return new RestartResult(false, message);
        }
    }
}