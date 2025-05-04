package com.kotprotiv.vpn_manager.repo;

import com.kotprotiv.vpn_manager.model.HistoryElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepo extends JpaRepository<HistoryElement, Long> {

    List<HistoryElement> findTop10ByOrderByStartedDesc();
}
