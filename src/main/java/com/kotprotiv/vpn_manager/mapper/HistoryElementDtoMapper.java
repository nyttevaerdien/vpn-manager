package com.kotprotiv.vpn_manager.mapper;

import com.kotprotiv.vpn_manager.model.HistoryElement;
import com.kotprotiv.vpn_manager.model.HistoryElementDto;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class HistoryElementDtoMapper implements Mapper<HistoryElement, HistoryElementDto> {

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public HistoryElementDto map(HistoryElement source) {
        HistoryElementDto dto = new HistoryElementDto();
        dto.setUsername(source.getUsername());
        dto.setStarted(Optional.ofNullable(source.getStarted()).map(a -> a.format(formatter)).orElse(null));
        dto.setFinished(Optional.ofNullable(source.getFinished()).map(a -> a.format(formatter)).orElse(null));
        dto.setStatus(String.valueOf(source.getStatus()));
        return dto;
    }
}
