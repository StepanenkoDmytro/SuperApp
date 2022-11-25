package com.example.OurApp.services;

import com.example.OurApp.models.Stock;
import com.example.OurApp.repositories.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public List<Stock> listStocks(String stock) {
        if (stock != null) {
            stockRepository.findByStock(stock);
        }
        return stockRepository.findAll();
    }
//    public List<Stock> listStocks(){
//        List<Stock> forNull = new ArrayList<>();
//        forNull.add(new Stock(1L,"dda","dadad",321,"dsa"));
//        return forNull;
//    }

    public void saveStock(Stock stock) {
        log.info("Saving new {}", stock);
        stockRepository.save(stock);
    }

    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

    public Stock getStockById(Long id) {
        return stockRepository.findById(id).orElse(null);
    }
}
