package com.klef.stocktracker;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
    List<StockEntity> findByUserId(long userId);
}
