package com.example.OurApp.repositories;

import com.example.OurApp.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByStock(String stock_name);
}
