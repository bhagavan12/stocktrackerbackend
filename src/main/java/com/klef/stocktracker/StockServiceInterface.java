package com.klef.stocktracker;

import java.util.List;
import java.util.Optional;

public interface StockServiceInterface {

	StockEntity addStockToPortfolio(Long userId, StockEntity stock);

    Optional<StockEntity> updateStockInPortfolio(Long userId, Long stockId, StockEntity stockDetails);

    boolean deleteStockFromPortfolio(Long userId, Long stockId);

    List<StockEntity> getStocksByUserId(Long userId);
}
