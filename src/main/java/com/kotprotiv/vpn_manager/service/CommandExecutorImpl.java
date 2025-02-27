package com.kotprotiv.vpn_manager.service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.kotprotiv.vpn_manager.model.RemoteServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandExecutorImpl implements CommandExecutor {

    private final RemoteServer remoteServer;

    @Override
    public String execute(String command) throws JSchException, IOException {
        log.info("Running {} on {}", command, remoteServer);
        StringBuilder output = new StringBuilder();
        JSch jsch = new JSch();
        Session session = jsch.getSession(remoteServer.getUsername(), remoteServer.getUrl(), 22);
        session.setPassword(remoteServer.getPassword());
        session.setConfig("StrictHostKeyChecking", "no"); // Skip host key checking
        session.connect();

        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand(command);
        channel.setInputStream(null);
        channel.setErrStream(System.err);

        BufferedReader reader = new BufferedReader(new InputStreamReader(channel.getInputStream()));
        channel.connect();

        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        channel.disconnect();
        session.disconnect();

        return output.toString();
    }
}
