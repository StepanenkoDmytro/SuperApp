package com.ourstocks.jwtappdemo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity //тепер наш клас є моделю, та відповідає за якусь табличку в БД
@Table(name = "posts") //якщо не задати назву таблиці таким чином, то назва таблиці буде вибрана автоматично
@Data //анотація ломбоку, яка за мене пише гетери, сеттери, перевизначає hash-code та equals, визначає toString
public class Post extends BaseEntity {

    @Column(name = "post_title")
    private String title;

    @Column(name = "post_full_text")
    private String full_text;

    @Column(name = "post_small_text")
    private String small_text;

    @Column(name = "post_views")
    private int views;
}
