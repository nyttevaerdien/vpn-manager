package com.kotprotiv.vpn_manager.controller;

import com.kotprotiv.vpn_manager.model.Headers;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String ip = request.getRemoteAddr();
        String uri = httpRequest.getRequestURI();
        String zone = httpRequest.getHeader(Headers.TIMEZONE);

        log.info("Incoming request from IP: {}, Endpoint: {}, TimeZone: {}", ip, uri, zone);
        chain.doFilter(request, response);
    }

}
