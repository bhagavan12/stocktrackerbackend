package com.klef.stocktracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stocks")
//@CrossOrigin("http://localhost:3000/")
//@CrossOrigin("https://stocktrackerapp.vercel.app/")
@CrossOrigin(origins = {"http://localhost:3000", "https://stocktrackerapp.vercel.app"})
public class StockController {

    @Autowired
    private StockServiceInterface stockService;

    // Add a stock to the user's portfolio
    @PostMapping("/add/{userId}")
    public ResponseEntity<StockEntity> addStock(@PathVariable("userId") Long userId, @RequestBody StockEntity stock) {
        try {
        	StockEntity newStock = stockService.addStockToPortfolio(userId, stock);
        	System.out.println("newStock="+newStock);
            return new ResponseEntity<>(newStock, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Update stock details for a user (e.g., update quantity or buy price)
    @PutMapping("/update/{userId}/{stockId}")
    public ResponseEntity<StockEntity> updateStock(@PathVariable("userId") Long userId, @PathVariable("stockId") Long stockId, @RequestBody StockEntity stockDetails) {
        Optional<StockEntity> updatedStock = stockService.updateStockInPortfolio(userId, stockId, stockDetails);
        return updatedStock.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete a stock from the user's portfolio
    @DeleteMapping("/delete/{userId}/{stockId}")
    public ResponseEntity<String> deleteStock(@PathVariable("userId") Long userId, @PathVariable("stockId") Long stockId) {
        boolean deleted = stockService.deleteStockFromPortfolio(userId, stockId);
        return deleted ? new ResponseEntity<>("Stock ID " + stockId + " deleted successfully", HttpStatus.OK)
                : new ResponseEntity<>("Stock not found", HttpStatus.NOT_FOUND);
    }

    // Fetch all stocks in a user's portfolio
    @GetMapping("/portfolio/{userId}")
    public ResponseEntity<List<StockEntity>> getUserPortfolio(@PathVariable("userId") Long userId) {
        List<StockEntity> portfolio = stockService.getStocksByUserId(userId);
        return portfolio.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(portfolio, HttpStatus.OK);
    }
}
