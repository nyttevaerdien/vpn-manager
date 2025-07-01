package com.kotprotiv.vpn_manager.service;

import com.jcraft.jsch.JSchException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.io.IOException;

public interface CommandExecutor {

    @Retryable( maxAttemptsExpression = "${retry.app.maxAttempts}",
            backoff = @Backoff(delayExpression = "${retry.app.backOffDelay}",
                    multiplierExpression = "${retry.app.multiplier}"))
    String execute(String command) throws JSchException, IOException;
}
