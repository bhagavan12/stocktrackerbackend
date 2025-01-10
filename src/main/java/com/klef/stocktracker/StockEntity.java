package com.klef.stocktracker;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

import jakarta.persistence.*;
@Entity
@Table(name="stockholding")
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private Long user;

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
//	public Long getUser() {
//		return user;
//	}
//	public void setUser(Long user) {
//		this.user = user;
//	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	private String stockName;
    private String ticker;
    private int quantity;
    private BigDecimal buyPrice;
    private Long userId; 

}
