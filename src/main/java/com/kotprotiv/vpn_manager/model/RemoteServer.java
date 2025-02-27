package com.kotprotiv.vpn_manager.model;

import lombok.Data;

@Data
public class RemoteServer {
    private String url;
    private String username;
    private String password;
}
