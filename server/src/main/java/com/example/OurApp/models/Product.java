package com.example.OurApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table (name = "stocks")
@Data
@AllArgsConstructor
@NoArgsConstructor//емулює таблицю з БД
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "stock_id")
    private Integer id;

    @Column (name = "stock_name")
    private String name;

    @Column (name = "stock_desc") //міняємо тип з varchar в текст
    private String desc;

    @Column (name = "stock_price")
    private int price;

    @Column (name = "stock_exchange")
    private String stock_exchange;

    @Column (name = "stock_user")
    private String author;
}
