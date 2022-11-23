package com.example.OurApp.controllers;

import com.example.OurApp.models.Stock;
import com.example.OurApp.services.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller //контролює наше звернення (як користувача) до застосунку (http запроси)
@RequiredArgsConstructor
public class StockController {
    private StockService stockService;

    @GetMapping("/")
    public String stocks (String stock, Model model) {
        model.addAttribute("stock",stockService.listStocks(stock));
        return "stocks";
    }

    @PostMapping("/stocks/create")
    public String createStock (Stock stock) {
        stockService.saveStock(stock);
        return "redirect:/";
    }

    @PostMapping("stock/delete/{id}")
    public String deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return "redirect:/";
    }
}
