package com.kotprotiv.vpn_manager.model;

import lombok.Data;

@Data
public class RestartResult {

    private final boolean isSuccessful;
    private final String message;
}
