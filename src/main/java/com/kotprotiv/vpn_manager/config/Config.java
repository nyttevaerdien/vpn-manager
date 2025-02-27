package com.kotprotiv.vpn_manager.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kotprotiv.vpn_manager.model.RemoteServer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    @ConfigurationProperties("remote-server")
    public RemoteServer remoteServer() {
        return new RemoteServer();
    }
}
