package com.kotprotiv.vpn_manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class HistoryElementDto {

    private String username;
    private String started;
    private String finished;
    private String status;

}
