package com.kotprotiv.vpn_manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "history")
public class HistoryElement {

    @Id
    @GeneratedValue
    private UUID  id;
    private String username;
    private String ip;
    private ZonedDateTime started;
    private ZonedDateTime finished;
    private Status status;


    public enum Status {
        SUCCESS,
        IN_PROGRESS,
        FAILED;
    }
}
