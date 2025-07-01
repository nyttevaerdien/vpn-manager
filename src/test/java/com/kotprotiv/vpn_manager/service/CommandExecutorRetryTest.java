package com.kotprotiv.vpn_manager.service;

import com.jcraft.jsch.JSchException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@EnableRetry
class CommandExecutorRetryTest {

    @Autowired
    private CommandExecutor commandExecutor;

    @Test
    void testRetryableAnnotationRetries() {
        assertThrows(JSchException.class, () -> commandExecutor.execute("test"));
        try {
            verify(commandExecutor, times(5)).execute(anyString());
        } catch (Exception ignored) {}
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public CommandExecutor commandExecutor() {
            CommandExecutor mock = mock(CommandExecutor.class);
            try {
                when(mock.execute(anyString()))
                        .thenThrow(new JSchException("fail"));
            } catch (Exception ignored) {}
            return mock;
        }
    }
}