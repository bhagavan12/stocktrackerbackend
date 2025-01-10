package com.klef.stocktracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService implements StockServiceInterface {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private UserRepository userRepository; // To validate if the user exists

    // Add a stock to the user's portfolio
    @Override
    public StockEntity addStockToPortfolio(Long userId, StockEntity stock) {
        // Validate user existence
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            stock.setUserId(user.get().getId()); // Associate stock with user
            return stockRepository.save(stock);
        }
//    	if (userRepository.existsById(userId)) {
//            stock.setUserId(userId); // Set user_id directly
//            return stockRepository.save(stock);
//        }
        throw new RuntimeException("User not found");
    }

    // Update stock details for the user
    @Override
    public Optional<StockEntity> updateStockInPortfolio(Long userId, Long stockId, StockEntity stockDetails) {
        // Validate user existence
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Optional<StockEntity> existingStock = stockRepository.findById(stockId);
            if (existingStock.isPresent() && existingStock.get().getUserId().equals(userId)) {
                StockEntity stock = existingStock.get();
                stock.setQuantity(stockDetails.getQuantity());
                stock.setBuyPrice(stockDetails.getBuyPrice());
                return Optional.of(stockRepository.save(stock));
            }
        }
//    	Optional<StockEntity> existingStock = stockRepository.findById(stockId);
//        if (existingStock.isPresent() && existingStock.get().getUserId().equals(userId)) {
//            StockEntity stock = existingStock.get();
//            stock.setQuantity(stockDetails.getQuantity());
//            stock.setBuyPrice(stockDetails.getBuyPrice());
//            return Optional.of(stockRepository.save(stock));
//        }
        return Optional.empty();
    }

    // Delete a stock from the user's portfolio
    @Override
    public boolean deleteStockFromPortfolio(Long userId, Long stockId) {
        // Validate user existence
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Optional<StockEntity> stock = stockRepository.findById(stockId);
            if (stock.isPresent() && stock.get().getUserId().equals(userId)) {
                stockRepository.deleteById(stockId);
                return true;
            }
        }
        return false;
    }

    // Get all stocks of a user
    @Override
    public List<StockEntity> getStocksByUserId(Long userId) {
        return stockRepository.findByUserId(userId);
    }
}
