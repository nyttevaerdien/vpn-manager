package com.kotprotiv.vpn_manager.controller;

import com.kotprotiv.vpn_manager.model.RestartResult;
import com.kotprotiv.vpn_manager.service.SshService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vpn")
@RequiredArgsConstructor
public class VpnController {

//    private static final String RESTART_COMMAND = "docker restart amnezia-xray amnezia-awg";
    private static final String RESTART_COMMAND = "echo \"success\"";

    private final SshService sshService;

    @PostMapping("/restart")
    public ResponseEntity<RestartResult> vpnRestart() {
        var result = sshService.executeCommand(RESTART_COMMAND);
        return ResponseEntity.ok().body(result);
    }
}
