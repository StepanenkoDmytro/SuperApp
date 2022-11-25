package com.example.OurApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "stocks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stock_id")
    private Long id;

    @Column(name = "stock_name")
    private String stock;

    @Column(name = "stock_exchange")
    private String stock_exchange;

    @Column(name = "stock_price")
    private int price;

    @Column(name = "stock_desc")
    private String stock_desc;

}
