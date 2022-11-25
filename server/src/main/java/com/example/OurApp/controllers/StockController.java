package com.example.OurApp.controllers;

import com.example.OurApp.models.Stock;
import com.example.OurApp.services.StockService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //контролює наше звернення (як користувача) до застосунку (http запроси)
@RequiredArgsConstructor
public class StockController {
    private StockService stockService;

    @GetMapping(path ="/")
    public String stocks (@RequestParam(name = "stock_name", required = false) String stock, Model model) {
//        if(stock == null) model.addAttribute("stocks",stockService.listStocks());
        model.addAttribute("stocks",stockService.listStocks(stock));
        return "stocks";
    }

    @PostMapping(path ="/stocks/create")
    public String createStock (Stock stock) {
        stockService.saveStock(stock);
        return "redirect:/stocks";
    }

    @PostMapping(path ="/stocks/delete/{id}")
    public String deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return "redirect:/stocks";
    }
}
