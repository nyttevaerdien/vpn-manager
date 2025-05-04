package com.kotprotiv.vpn_manager.controller;

import com.kotprotiv.vpn_manager.mapper.Mapper;
import com.kotprotiv.vpn_manager.model.Headers;
import com.kotprotiv.vpn_manager.model.HistoryElement;
import com.kotprotiv.vpn_manager.model.HistoryElementDto;
import com.kotprotiv.vpn_manager.model.RestartResult;
import com.kotprotiv.vpn_manager.service.HistoryService;
import com.kotprotiv.vpn_manager.service.SshService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/vpn")
@RequiredArgsConstructor
public class VpnController {

    private static final String RESTART_COMMAND = "docker restart amnezia-xray amnezia-awg";

    private final SshService sshService;
    private final HistoryService historyService;
    private final Mapper<HistoryElement, HistoryElementDto> historyElementDtoMapper;

    @PostMapping("/restart")
    public ResponseEntity<RestartResult> vpnRestart(@RequestHeader(Headers.TIMEZONE) String zone) {
        var result = sshService.executeCommand(RESTART_COMMAND, ZoneId.of(zone == null ? "UTC" : zone));
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/history")
    public ResponseEntity<List<HistoryElementDto>> history() {
        return ResponseEntity.ok().body(historyService.findLast10().stream().map(historyElementDtoMapper::map).toList());
    }

}
