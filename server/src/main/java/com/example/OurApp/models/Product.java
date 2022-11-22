package com.example.OurApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table (name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor//емулює таблицю з БД
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "product_id")
    private Integer id;

    @Column (name = "title")
    private String title;

    @Column (name = "desc", columnDefinition = "text") //міняємо тип з varchar в текст
    private String desc;

    @Column (name = "price")
    private int price;

    @Column (name = "stock_exchange")
    private String stock_exchange;

    @Column (name = "author")
    private String author;
}
