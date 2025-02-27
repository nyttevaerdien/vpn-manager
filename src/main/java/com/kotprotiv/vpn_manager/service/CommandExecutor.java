package com.kotprotiv.vpn_manager.service;

import com.jcraft.jsch.JSchException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.io.IOException;

public interface CommandExecutor {

    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 1000))
    String execute(String command) throws JSchException, IOException;
}
